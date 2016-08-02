package fi.ohtu.mobilityprofileapi;

import android.os.Bundle;
import android.os.Message;

/**
 * MessageCreator is responsible for creating requests to the mobility profile. This class does not
 * send the requests, it only creates them and then forwards them to {@link RemoteConnectionHandler}
 * to be sent to Mobility Profile.
 * <p>
 * {@link MobilityProfileApp#setMessageListener(MessageListener)} should be called before creating
 * any requests in order to make sure we have a registered listener for the response.
 */
public class MessageCreator {
    private RemoteConnectionHandler remoteConnectionHandler;

    /**
     * Creates the MessageCreator.
     *
     * @param remoteConnectionHandler Connection handler for the service
     */
    public MessageCreator(RemoteConnectionHandler remoteConnectionHandler) {
        this.remoteConnectionHandler = remoteConnectionHandler;
    }

    /**
     * Request a list if most likely next destinations when moving inside a city.
     */
    public void requestIntraCitySuggestions() {
        sendMessage(ResponseCode.REQUEST_INTRA_CITY_SUGGESTIONS);
    }

    /**
     * Request a list of most likely next destinations when moving between cities.
     */
    public void requestInterCitySuggestions() {
        sendMessage(ResponseCode.REQUEST_INTER_CITY_SUGGESTIONS);
    }

    /**
     * Sends the searched route to Mobility Profile so it can improve its suggestions.
     *
     * @param startLocation Starting location
     * @param destination Destination
     */
    public void sendSearchedRoute(String startLocation, String destination) {
        if (startLocation.equals("") || destination.equals("")) {
            // This is not a valid route search since we need to know both the starting location
            // and the destination.
            return;
        }

        sendMessage(ResponseCode.SEND_SEARCHED_ROUTE, startLocation + "|" + destination);
    }

    /**
     * Sends a message to Mobility Profile with the given code.
     *
     * @param messageCode Message code
     */
    private void sendMessage(int messageCode) {
        sendMessage(messageCode, "");
    }

    /**
     * Sends a message to Mobility Profile with the given code and information.
     *
     * @param messageCode Message code
     * @param info Additional information
     */
    private void sendMessage(int messageCode, String info) {
        Bundle bundle = new Bundle();
        bundle.putString(""+messageCode, info);
        Message message = Message.obtain(null, messageCode);
        message.setData(bundle);

        remoteConnectionHandler.sendRequest(message);
    }
}
