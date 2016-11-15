package fi.ohtu.mobilityprofileapi;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

/**
 * MobilityProfileInterface is responsible for sending requests and info messages to
 * MobilityProfile. This class does not actually send the requests, but it creates them and then
 * forwards them to {@link RemoteConnectionHandler} to be sent to Mobility Profile.
 * <p/>
 * {@link MobilityProfileApp#setMessageListener(MessageListener)} should be called before sending
 * any messages in order to make sure we have a registered listener for the response.
 * <p/>
 * {@link MobilityProfileInterface#setMode(int)} should also be called before sending messages so
 * that we know the type of requests we want to make.
 */
public class MobilityProfileInterface {
    /**
     * This mode should be used if your application's main purpose is to give route suggestions for
     * moving inside a city or a metropolis.
     */
    public static final int MODE_INTRACITY = 300;
    /**
     * This mode should be used if your application's main purpose is to give route suggestions for
     * moving between cities.
     */
    public static final int MODE_INTERCITY = 301;

    private static final String MODE_ID = "mode";

    private RemoteConnectionHandler remoteConnectionHandler;
    private int mode;

    /**
     * Creates the MobilityProfileInterface.
     *
     * @param remoteConnectionHandler Connection handler for the service
     */
    public MobilityProfileInterface(RemoteConnectionHandler remoteConnectionHandler) {
        this.remoteConnectionHandler = remoteConnectionHandler;
    }

    /**
     * Tells if Mobility Profile is available to use. This will be false even if Mobility Profile is
     * installed on the device, if connecting to Mobility Profile fails.
     *
     * @return True if available, false otherwise
     */
    public final boolean isAvailable() {
        return remoteConnectionHandler.isBound();
    }

    /**
     * Set the suggestions mode. This method should be called from the activity's
     * {@link MobilityProfileApp#onCreate(Bundle)} method. Available modes are
     * {@link MobilityProfileInterface#MODE_INTRACITY} and
     * {@link MobilityProfileInterface#MODE_INTERCITY}.
     *
     * @param mode Suggestions mode
     * @throws IllegalArgumentException If the given mode is not
     *                                  {@link MobilityProfileInterface#MODE_INTRACITY} or
     *                                  {@link MobilityProfileInterface#MODE_INTERCITY}
     */
    public void setMode(int mode) {
        if (mode != MODE_INTRACITY && mode != MODE_INTERCITY) {
            throw new IllegalArgumentException("Mode should be either intracity or intercity");
        }

        this.mode = mode;
    }

    /**
     * Request a list of most likely next destinations.
     *
     * @throws RuntimeException If mode is not set to
     *                          {@link MobilityProfileInterface#MODE_INTRACITY} or
     *                          {@link MobilityProfileInterface#MODE_INTERCITY}
     */
    public void requestSuggestions() {
        if (mode != MODE_INTRACITY && mode != MODE_INTERCITY) {
            throw new RuntimeException("Mode should be either intracity or intercity");
        }

        Bundle bundle = new Bundle();
        bundle.putInt(MODE_ID, mode);

        sendMessage(MessageCode.REQUEST_SUGGESTIONS, bundle);
    }

    /**
     * Requests a list of transport mode preferences.
     */
    public void requestTransportModePreferences() {
        sendMessage(MessageCode.REQUEST_TRANSPORT_PREFERENCES);
    }

    /**
     * Sends the searched route to Mobility Profile so it can improve its suggestions.
     *
     * @param startLocation Starting location
     * @param destination   Destination
     * @throws RuntimeException If mode is not set to
     *                          {@link MobilityProfileInterface#MODE_INTRACITY} or
     *                          {@link MobilityProfileInterface#MODE_INTERCITY}
     */
    public void sendSearchedRoute(Place startLocation, Place destination) {
        if (mode != MODE_INTRACITY && mode != MODE_INTERCITY) {
            throw new RuntimeException("Mode should be either intracity or intercity");
        }

        if (startLocation == null || destination == null) {
            // This is not a valid route search since we need to know both the starting location
            // and the destination.
            Log.d("MPInterface", "Start location or destination was null");
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putFloat("startLon", startLocation.getLongitude());
        bundle.putFloat("startLat", startLocation.getLatitude());
        bundle.putFloat("endLon", destination.getLongitude());
        bundle.putFloat("endLat", destination.getLatitude());
        bundle.putInt(MODE_ID, mode);

        sendMessage(MessageCode.SEND_SEARCHED_ROUTE, bundle);
    }

    /**
     * Sends a message to Mobility Profile with the given code.
     *
     * @param messageCode Message code
     */
    private void sendMessage(int messageCode) {
        Bundle bundle = new Bundle();
        sendMessage(messageCode, bundle);
    }

    /**
     * Sends a message to Mobility Profile with the given code and data.
     *
     * @param messageCode Message code
     * @param bundle      Additional data
     */
    private void sendMessage(int messageCode, Bundle bundle) {
        Message message = Message.obtain(null, messageCode);
        message.setData(bundle);

        remoteConnectionHandler.sendRequest(message);
    }
}
