package fi.ohtu.mobilityprofileapi;

import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.cocoahero.android.geojson.GeoJSON;
import com.cocoahero.android.geojson.GeoJSONObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used for processing incoming messages from the mobility profile. Processed
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
        assert messageListener != null : "MessageListener is not set! You should do that in the activity's onCreate() method.";

        Log.d(RemoteConnectionHandler.TAG, "Remote Service replied with code " + msg.what);

        switch (msg.what) {
            case ResponseCode.RESPOND_MOST_LIKELY_SUGGESTIONS:
                processSuggestions(msg);
                break;
            case ResponseCode.RESPOND_TRANSPORT_PREFERENCES:
                messageListener.onTransportPreferencesResponse(msg.getData().getString(""+msg.what));
                break;
            case ResponseCode.ERROR_UNKNOWN_CODE:
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
        String destinations = msg.getData().getString(""+msg.what);
        assert destinations != null : "Invalid response from Mobility Profile";

        if (TextUtils.isEmpty(destinations)) {
            messageListener.onNoSuggestions();
        } else {
            messageListener.onSuggestionsResponse(destinations);
            messageListener.onSuggestionsResponse(convertToSuggestions(destinations));
        }
    }

    /**
     * Converts a geoJSON string to list of {@link Suggestion} objects. Returns null if the given
     * string can't be converted.
     *
     * @param geoJSON Suggestions as a geoJSON string
     * @return List of converted suggestions
     */
    private List<Suggestion> convertToSuggestions(String geoJSON) {
        try {
            List<Suggestion> suggestions = new ArrayList<>();

            JSONArray array = new JSONArray(geoJSON);
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);

                JSONArray coordinates = jsonObject.getJSONObject("geometry").getJSONArray("coordinates");
                double longitude = (double) coordinates.get(0);
                double latitude = (double) coordinates.get(1);

                String address = jsonObject.getJSONObject("properties").getString("label");

                suggestions.add(new Suggestion(address, longitude, latitude));
            }

            return suggestions;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
