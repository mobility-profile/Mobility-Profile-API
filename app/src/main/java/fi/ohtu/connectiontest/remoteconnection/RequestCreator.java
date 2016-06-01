package fi.ohtu.connectiontest.remoteconnection;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.widget.Toast;

import static fi.ohtu.connectiontest.remoteconnection.ResponseCode.*;

/**
 * This class is used for sending requests to the mobility profile.
 */
public class RequestCreator {
    private Context context;
    private RemoteConnectionHandler remoteConnectionHandler;
    private Messenger incomingRequestMessenger;

    /**
     * Creates the RequestCreator.
     *
     * @param context Context used for toasts
     * @param remoteConnectionHandler Connection handler for the service
     * @param incomingRequestMessenger Messenger used for receiving messages from the mobility profile
     */
    public RequestCreator(Context context, RemoteConnectionHandler remoteConnectionHandler, Messenger incomingRequestMessenger) {
        this.context = context;
        this.remoteConnectionHandler = remoteConnectionHandler;
        this.incomingRequestMessenger = incomingRequestMessenger;
    }

    /**
     * Request the most likely next destination from the mobility profile.
     */
    public void requestMostLikelyDestination() {
        makeRequest(REQUEST_MOST_LIKELY_DESTINATION);
    }

    private void makeRequest(int requestCode) {
        makeRequest(requestCode, "");
    }

    private void makeRequest(int requestCode, String info) {
        if (remoteConnectionHandler.isBound()) {
            Messenger requestCreatorMessenger = remoteConnectionHandler.getRequestCreatorMessenger();
            assert requestCreatorMessenger != null : "RequestCreatorMessenger should not be null when bound to the service!";

            // Setup the message for invocation.
            Bundle bundle = new Bundle();
            bundle.putString(""+requestCode, info);
            Message message = Message.obtain(null, requestCode);
            message.setData(bundle);

            try {
                // Set the ReplyTo messenger for processing the invocation response.
                message.replyTo = incomingRequestMessenger;

                // Make the invocation.
                requestCreatorMessenger.send(message);
            } catch (RemoteException rme) {
                Toast.makeText(context, "Invocation failed!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "Service is not bound!", Toast.LENGTH_SHORT).show();
        }
    }
}
