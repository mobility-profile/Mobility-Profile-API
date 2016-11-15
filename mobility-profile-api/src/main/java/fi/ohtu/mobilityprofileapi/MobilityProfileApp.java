package fi.ohtu.mobilityprofileapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Abstract class that sets up the connection to Mobility Profile.
 */
public abstract class MobilityProfileApp extends AppCompatActivity {
    private RemoteConnectionHandler remoteConnectionHandler;
    private IncomingMessageHandler incomingMessageHandler;
    protected MobilityProfileInterface mobilityProfileInterface;

    private MessageListener messageListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.incomingMessageHandler = new IncomingMessageHandler();
        this.remoteConnectionHandler = new RemoteConnectionHandler(this, incomingMessageHandler);
        this.mobilityProfileInterface = new MobilityProfileInterface(remoteConnectionHandler);
    }

    @Override
    protected void onStart() {
        super.onStart();

        boolean connected = remoteConnectionHandler.connectToService();

        if (!connected && messageListener != null) {
            messageListener.onNotAvailable();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        remoteConnectionHandler.disconnectFromService();
    }

    /**
     * Sets the response listener that will be used for handling incoming responses. This method
     * should be called from the activity's {@link MobilityProfileApp#onCreate(Bundle)} method.
     *
     * @param messageListener Listener for incoming requests
     */
    public final void setMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;

        incomingMessageHandler.setMessageListener(messageListener);
        remoteConnectionHandler.setMessageListener(messageListener);
    }
}
