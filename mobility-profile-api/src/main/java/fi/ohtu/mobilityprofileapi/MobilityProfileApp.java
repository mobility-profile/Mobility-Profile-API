package fi.ohtu.mobilityprofileapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Abstract class that sets up the connection to the mobility profile.
 */
public abstract class MobilityProfileApp extends AppCompatActivity {
    private RemoteConnectionHandler remoteConnectionHandler;
    private IncomingMessageHandler incomingMessageHandler;
    protected MessageCreator mobilityProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.incomingMessageHandler = new IncomingMessageHandler();
        this.remoteConnectionHandler = new RemoteConnectionHandler(this, incomingMessageHandler);
        this.mobilityProfile = new MessageCreator(remoteConnectionHandler);
    }

    @Override
    protected void onStart() {
        super.onStart();

        remoteConnectionHandler.startConnection();
    }

    @Override
    protected void onStop() {
        super.onStop();

        remoteConnectionHandler.stopConnection();
    }

    /**
     * Sets the response listener that will be used for handling incoming requests. This method
     * should be called from the activity's onCreate() method.
     *
     * @param messageListener Listener for incoming requests
     */
    public void setMessageListener(MessageListener messageListener) {
        incomingMessageHandler.setMessageListener(messageListener);
        remoteConnectionHandler.setMessageListener(messageListener);
    }
}