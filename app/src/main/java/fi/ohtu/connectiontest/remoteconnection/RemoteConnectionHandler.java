package fi.ohtu.connectiontest.remoteconnection;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.widget.Toast;

/**
 * This class is used for communicating with the Mobility Profile.
 */
public class RemoteConnectionHandler extends Handler implements ServiceConnection {
    private Context context;
    private IncomingRequestHandler incomingRequestHandler;
    private ResponseListener responseListener;

    private Messenger requestCreatorMessenger;
    private Messenger incomingRequestMessenger;
    private boolean isBound;

    /**
     * Creates the RemoteConnectionHandler.
     *
     * @param context Context used for binding the service
     */
    public RemoteConnectionHandler(Context context, IncomingRequestHandler incomingRequestHandler) {
        this.context = context;
        this.incomingRequestHandler = incomingRequestHandler;
        this.responseListener = responseListener;

        this.incomingRequestMessenger = new Messenger(this);
    }

    /**
     * Connects to the remote service.
     */
    public void startConnection() {
        Intent intent = new Intent();
        intent.setClassName("fi.ohtu.mobilityprofile", "fi.ohtu.mobilityprofile.RemoteService");

        context.bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    /**
     * Stops the connection to the remote service.
     */
    public void stopConnection() {
        if (isBound) {
            context.unbindService(this);
            isBound = false;
        }
    }

    /**
     * Tells if we are connected to the remote service.
     *
     * @return True if connected, false otherwise
     */
    public boolean isBound() {
        return isBound;
    }

    /**
     * Sends a request to the mobility profile.
     *
     * @param message Message to be sent
     */
    public void sendRequest(Message message) {
        if (isBound()) {
            assert requestCreatorMessenger != null : "RequestCreatorMessenger should not be null when bound to the service!";

            try {
                // Set the ReplyTo messenger for processing the invocation response.
                message.replyTo = incomingRequestMessenger;

                // Make the invocation.
                requestCreatorMessenger.send(message);
            } catch (RemoteException rme) {
                if (context != null) {
                    Toast.makeText(context, "Invocation failed!", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            if (context != null) {
                Toast.makeText(context, "Service is not bound!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Sets the response listener that will be used for handling incoming requests.
     *
     * @param responseListener Listener for incoming requests
     */
    public void setResponseListener(ResponseListener responseListener) {
        this.responseListener = responseListener;
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        requestCreatorMessenger = new Messenger(service);
        isBound = true;

        assert responseListener != null : "ResponseListener is not set! You should do that in the activity's onCreate() method.";
        responseListener.onConnect();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        requestCreatorMessenger = null;
        isBound = false;

        assert responseListener != null : "ResponseListener is not set! You should do that in the activity's onCreate() method.";
        responseListener.onDisconnect();
    }

    @Override
    public void handleMessage(Message msg) {
        incomingRequestHandler.handleMessage(msg);
    }
}
