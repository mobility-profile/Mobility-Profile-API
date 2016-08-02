package fi.ohtu.mobilityprofileapi;

import android.os.Message;
import android.util.Log;

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
                messageListener.onTransportPreferencesResponse(msg.getData().getStringArrayList(""+msg.what));
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
        List<String> destinations = msg.getData().getStringArrayList(""+msg.what);
        assert destinations != null : "Invalid response from Mobility Profile";

        if (destinations.isEmpty()) {
            messageListener.onNoSuggestions();
        }
        else {
            messageListener.onSuggestionsResponse(destinations.get(0));
            messageListener.onSuggestionsResponse(destinations);
        }
    }
}
