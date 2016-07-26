package fi.ohtu.mobilityprofileapi;

import android.os.Message;
import android.util.Log;

import java.util.ArrayList;

/**
 * This class is used for processing incoming messages from the mobility profile. Processed
 * messages are forwarded to the registered MessageListener.
 */
public class IncomingMessageHandler {
    private MessageListener messageListener;

    /**
     * Creates the IncomingMessageHandler.
     */
    public IncomingMessageHandler() {
    }

    /**
     * Sets the response listener that will be used for handling incoming requests.
     *
     * @param messageListener Listener for incoming requests
     */
    public void setMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    public void handleMessage(Message msg) {
        assert messageListener != null : "MessageListener is not set! You should do that in the activity's onCreate() method.";

        Log.d("Remote service", "Remote Service replied (" + msg.what + ")");

        switch (msg.what) {
            case ResponseCode.RESPOND_MOST_LIKELY_DESTINATION:
                ArrayList<String> destinations = msg.getData().getStringArrayList(""+msg.what);
                assert destinations != null : "Invalid response from Mobility Profile";

                if (destinations.isEmpty()) {
                    messageListener.onNoSuggestions();
                }
                else {
                    messageListener.onGetMostLikelyDestination(destinations.get(0));
                    messageListener.onGetListOfMostLikelyDestinations(destinations);
                }

                break;
            case ResponseCode.ERROR_UNKNOWN_CODE:
                messageListener.onUnknownCode();
                break;
            default:

        }
    }
}
