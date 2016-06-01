package fi.ohtu.connectiontest.remoteconnection;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Messenger;

/**
 * This class is used for communicating with the Mobility Profile.
 */
public class RemoteConnectionHandler implements ServiceConnection {
    private Context context;
    private Messenger requestCreatorMessenger;

    private boolean isBound;

    /**
     * Creates the RemoteConnectionHandler.
     *
     * @param context Context used for binding the service
     */
    public RemoteConnectionHandler(Context context) {
        this.context = context;
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
     * Returns the messenger used for sending requests to the mobility profile.
     *
     * @return Messenger for sending requests
     */
    public Messenger getRequestCreatorMessenger() {
        return requestCreatorMessenger;
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        requestCreatorMessenger = new Messenger(service);
        isBound = true;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        requestCreatorMessenger = null;
        isBound = false;
    }
}
