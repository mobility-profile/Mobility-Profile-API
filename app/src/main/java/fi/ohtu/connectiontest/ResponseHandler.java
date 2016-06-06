package fi.ohtu.connectiontest;

import fi.ohtu.connectiontest.remoteconnection.RequestCreator;
import fi.ohtu.connectiontest.remoteconnection.ResponseListener;

/**
 * This class is just for demonstrating how you could use the mobility profile.
 */
public class ResponseHandler implements ResponseListener {
    private UIHandler uiHandler;
    private RequestCreator mobilityProfile;

    public ResponseHandler(UIHandler uiHandler, RequestCreator requestCreator) {
        this.uiHandler = uiHandler;
        this.mobilityProfile = requestCreator;
    }

    @Override
    public void onConnect() {
        mobilityProfile.requestMostLikelyDestination();
    }

    @Override
    public void onDisconnect() {

    }

    @Override
    public void onGetMostLikelyDestination(String destination) {
        uiHandler.showPopup(destination);
        // uiHandler.updateDestination(destination);
        
    }

    @Override
    public void onUnknownCode() {

    }
}
