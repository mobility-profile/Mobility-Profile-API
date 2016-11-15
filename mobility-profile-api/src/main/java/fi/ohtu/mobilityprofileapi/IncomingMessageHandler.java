package fi.ohtu.mobilityprofileapi;

import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used for processing incoming messages from Mobility Profile. Processed
 * messages are forwarded to the registered {@link MessageListener}.
 */
public class IncomingMessageHandler {
    private MessageListener messageListener;

    /**
     * Sets the response listener that will be used for handling incoming requests.
     *
     * @param messageListener Listener for incoming requests
     */
    public void setMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    /**
     * Handles a message that was sent by Mobility Profile.
     *
     * @param msg Message to handle
     */
    public void handleMessage(Message msg) {
        if (messageListener == null) {
            return;
        }

        Log.d(RemoteConnectionHandler.TAG, "Remote Service replied with code " + msg.what);

        switch (msg.what) {
            case MessageCode.RESPOND_SUGGESTIONS:
                processSuggestions(msg);
                break;
            case MessageCode.RESPOND_TRANSPORT_PREFERENCES:
                messageListener.onTransportPreferencesResponse(msg.getData().getString("" + msg.what));
                break;
            case MessageCode.ERROR_UNKNOWN_CODE:
                messageListener.onUnknownRequest();
                break;
            default:
                messageListener.onUnknownResponse(msg.what);
        }
    }

    /**
     * Processes a suggestions response sent by Mobility Profile.
     *
     * @param msg Message containing the suggestions
     */
    private void processSuggestions(Message msg) {
        String destinations = msg.getData().getString("" + msg.what);
        assert destinations != null : "Invalid response from Mobility Profile";

        if (TextUtils.isEmpty(destinations)) {
            messageListener.onNoSuggestions();
        } else {
            messageListener.onSuggestionsResponse(destinations);
            messageListener.onSuggestionsResponse(convertToSuggestions(destinations));
        }
    }

    /**
     * Converts a geoJSON string to list of {@link Place} objects. Returns null if the given
     * string can't be converted.
     *
     * @param geoJSON Suggestions as a geoJSON string
     * @return List of converted suggestions
     */
    private List<Place> convertToSuggestions(String geoJSON) {
        try {
            List<Place> places = new ArrayList<>();

            JSONArray array = new JSONArray(geoJSON);
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);

                JSONArray coordinates = jsonObject.getJSONObject("geometry").getJSONArray("coordinates");
                float longitude = (float) coordinates.getDouble(0);
                float latitude = (float) coordinates.getDouble(1);

                String address = jsonObject.getJSONObject("properties").getString("label");

                places.add(new Place(address, longitude, latitude));
            }

            return places;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
