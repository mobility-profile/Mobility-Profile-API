package fi.ohtu.connectiontest.remoteconnection;

import android.content.Context;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import static fi.ohtu.connectiontest.remoteconnection.ResponseCode.*;

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
            case RESPOND_MOST_LIKELY_DESTINATION:
                //messageListener.onGetMostLikelyDestination(msg.getData().getString(""+msg.what));
                System.out.println(msg.getData().getStringArrayList(""+msg.what));
                messageListener.onGetListOfMostLikelyDestinations(msg.getData().getStringArrayList(""+msg.what));
                break;
            case ERROR_UNKNOWN_CODE:
                messageListener.onUnknownCode();
                break;
            default:

        }
    }
}
