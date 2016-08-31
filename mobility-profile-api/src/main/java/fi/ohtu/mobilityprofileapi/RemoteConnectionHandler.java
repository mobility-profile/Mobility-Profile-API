package fi.ohtu.mobilityprofileapi;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

/**
 * RemoteConnectionHandler is responsible for handling the communication with Mobility Profile.
 * When the activity is started, {@link RemoteConnectionHandler#connectToService()} should be called
 * to create the connection with Mobility Profile. When the activity is stopped,
 * {@link #disconnectFromService()} should be called in order to save resources.
 * <p/>
 * Messages received from Mobility Profile are forwarded to {@link IncomingMessageHandler}.
 * <p/>
 * {@link #sendRequest(Message)} is used by {@link MobilityProfileInterface} to send requests to
 * Mobility Profile.
 */
public class RemoteConnectionHandler extends Handler implements ServiceConnection {
    public static final String TAG = "Remote connection";

    private Context context;
    private IncomingMessageHandler incomingMessageHandler;
    private MessageListener messageListener;

    private Messenger incomingMessenger;
    private Messenger outgoingMessenger;

    private boolean isBound = false;

    /**
     * Creates the RemoteConnectionHandler.
     *
     * @param context                Context used for binding the service
     * @param incomingMessageHandler Handler for handling incoming messages
     */
    public RemoteConnectionHandler(Context context, IncomingMessageHandler incomingMessageHandler) {
        this.context = context;
        this.incomingMessageHandler = incomingMessageHandler;

        this.incomingMessenger = new Messenger(this);
    }

    /**
     * Connects to Mobility Profile.
     *
     * @return True if connected successfully, false otherwise
     */
    public boolean connectToService() {
        Intent intent = new Intent();
        intent.setClassName("fi.ohtu.mobilityprofile", "fi.ohtu.mobilityprofile.remoteconnection.RemoteService");

        try {
            return context.bindService(intent, this, Context.BIND_AUTO_CREATE);
        } catch (SecurityException ex) {
            Log.e(TAG, "Permission to use Mobility Profile has not been granted");
            return false;
        }
    }

    /**
     * Stops the connection to Mobility Profile.
     */
    public void disconnectFromService() {
        if (isBound) {
            context.unbindService(this);
        }
    }

    /**
     * Tells if we are connected to Mobility Profile
     *
     * @return True if connected, false otherwise
     */
    public boolean isBound() {
        return isBound;
    }

    /**
     * Sends a request to Mobility Profile.
     *
     * @param message Message to be sent
     */
    public void sendRequest(Message message) {
        if (isBound()) {
            assert outgoingMessenger != null : "OutgoingMessenger should not be null when bound to the service!";

            try {
                // Set the ReplyTo messenger for processing the invocation response.
                message.replyTo = incomingMessenger;

                // Make the invocation.
                outgoingMessenger.send(message);
            } catch (RemoteException rme) {
                Log.e(TAG, "Invocation failed");
            }
        } else {
            Log.d(TAG, "Service is not bound");
        }
    }

    /**
     * Sets the response listener that will be used for handling incoming requests.
     *
     * @param messageListener Listener for incoming requests
     */
    public void setMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        outgoingMessenger = new Messenger(service);
        isBound = true;

        assert messageListener != null : "MessageListener is not set! You should do that in the activity's onCreate() method.";
        messageListener.onConnect();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        outgoingMessenger = null;
        isBound = false;

        assert messageListener != null : "MessageListener is not set! You should do that in the activity's onCreate() method.";
        messageListener.onDisconnect();
    }

    @Override
    public void handleMessage(Message msg) {
        incomingMessageHandler.handleMessage(msg);
    }
}
