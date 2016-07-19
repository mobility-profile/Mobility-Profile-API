package fi.ohtu.connectiontest.remoteconnection;

import android.os.Bundle;
import android.os.Message;

import static fi.ohtu.connectiontest.remoteconnection.ResponseCode.*;

/**
 * MessageCreator is responsible for creating requests to the mobility profile. This class does not
 * send the requests, it only creates them and then forwards them to RemoteConnectionHandler to be
 * sent to the mobility profile.
 *
 * SetResponseListener() from MobilityProfileApp should be called before creating any requests in
 * order to make sure we have a registered listener for the response.
 */
public class MessageCreator {
    private RemoteConnectionHandler remoteConnectionHandler;

    /**
     * Creates the MessageCreator.
     *
     * @param remoteConnectionHandler Connection handler for the service
     */
    public MessageCreator(RemoteConnectionHandler remoteConnectionHandler) {
        this.remoteConnectionHandler = remoteConnectionHandler;
    }

    /**
     * Request the most likely next destination from the mobility profile.
     */
    public void requestMostLikelyDestination() {
        makeRequest(REQUEST_MOST_LIKELY_DESTINATION);
    }
    
    public void sendUsedRoute(String startLocation, String destination){
        makeRequest(SEND_USED_ROUTE, startLocation + " " + destination);
    }

    private void makeRequest(int requestCode) {
        makeRequest(requestCode, "");
    }

    private void makeRequest(int requestCode, String info) {
        // Setup the message for invocation.
        Bundle bundle = new Bundle();
        bundle.putString(""+requestCode, info);
        Message message = Message.obtain(null, requestCode);
        message.setData(bundle);

        remoteConnectionHandler.sendRequest(message);
    }
}
