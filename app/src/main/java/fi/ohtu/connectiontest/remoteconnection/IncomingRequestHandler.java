package fi.ohtu.connectiontest.remoteconnection;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import static fi.ohtu.connectiontest.remoteconnection.ResponseCode.*;

/**
 * This class is used for processing incoming messages from the mobility profile.
 */
public class IncomingRequestHandler {
    private Context context;
    private ResponseListener responseListener;

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
     * @param responseListener Listener for incoming requests
     */
    public void setResponseListener(ResponseListener responseListener) {
        this.responseListener = responseListener;
    }

    public void handleMessage(Message msg) {
        assert responseListener != null : "ResponseListener is not set! You should do that in the activity's onCreate() method.";

        if (context != null) {
            Toast.makeText(context.getApplicationContext(), "Remote Service replied (" + msg.what + ")", Toast.LENGTH_SHORT).show();
        }

        switch (msg.what) {
            case RESPOND_MOST_LIKELY_DESTINATION:
                responseListener.onGetMostLikelyDestination(msg.getData().getString(""+msg.what));
                break;
            case ERROR_UNKNOWN_CODE:
                responseListener.onUnknownCode();
                break;
            default:

        }
    }
}
