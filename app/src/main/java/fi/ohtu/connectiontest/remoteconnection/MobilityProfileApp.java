package fi.ohtu.connectiontest.remoteconnection;

import android.os.Bundle;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;

/**
 * Abstract class that sets up the connection to the mobility profile.
 */
public abstract class MobilityProfileApp extends AppCompatActivity {
    private RemoteConnectionHandler remoteConnectionHandler;
    private IncomingRequestHandler incomingRequestHandler;
    private RequestCreator requestCreator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.remoteConnectionHandler = new RemoteConnectionHandler(this);
        this.incomingRequestHandler = new IncomingRequestHandler(this);
        this.requestCreator = new RequestCreator(this, remoteConnectionHandler, new Messenger(incomingRequestHandler));
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
     * should be called on the activity's onCreate() method.
     *
     * @param responseListener Listener for incoming requests
     */
    public void setResponseListener(ResponseListener responseListener) {
        incomingRequestHandler.setResponseListener(responseListener);
    }

    /**
     * Returns the request creator that can be used for sending requests to the mobility profile.
     *
     * @return Request creator
     */
    public RequestCreator getRequestCreator() {
        return requestCreator;
    }
}
