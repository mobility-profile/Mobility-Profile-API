package fi.ohtu.connectiontest.remoteconnection;

import android.content.Context;
import android.os.Message;
import android.widget.Toast;

import static fi.ohtu.connectiontest.remoteconnection.ResponseCode.*;

/**
 * This class is used for processing incoming messages from the mobility profile. Processed
 * messages are forwarded to the registered MessageListener.
 */
public class IncomingRequestHandler {
    private Context context;
    private MessageListener messageListener;

    /**
     * Creates the IncomingRequestHandler.
     *
     * @param context Context used for toasts
     */
    public IncomingRequestHandler(Context context) {
        this.context = context;
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

        if (context != null) {
            Toast.makeText(context.getApplicationContext(), "Remote Service replied (" + msg.what + ")", Toast.LENGTH_SHORT).show();
        }

        switch (msg.what) {
            case RESPOND_MOST_LIKELY_DESTINATION:
                messageListener.onGetMostLikelyDestination(msg.getData().getString(""+msg.what));
                break;
            case ERROR_UNKNOWN_CODE:
                messageListener.onUnknownCode();
                break;
            default:

        }
    }
}
