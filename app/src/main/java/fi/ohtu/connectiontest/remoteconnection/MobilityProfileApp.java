package fi.ohtu.connectiontest.remoteconnection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Abstract class that sets up the connection to the mobility profile.
 */
public abstract class MobilityProfileApp extends AppCompatActivity {
    private RemoteConnectionHandler remoteConnectionHandler;
    private IncomingRequestHandler incomingRequestHandler;
    protected RequestCreator mobilityProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.incomingRequestHandler = new IncomingRequestHandler(this);
        this.remoteConnectionHandler = new RemoteConnectionHandler(this, incomingRequestHandler);
        this.mobilityProfile = new RequestCreator(remoteConnectionHandler);
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
     * @param responseListener Listener for incoming requests
     */
    public void setResponseListener(ResponseListener responseListener) {
        incomingRequestHandler.setResponseListener(responseListener);
        remoteConnectionHandler.setResponseListener(responseListener);
    }
}
