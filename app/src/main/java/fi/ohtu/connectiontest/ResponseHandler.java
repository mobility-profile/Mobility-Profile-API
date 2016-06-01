package fi.ohtu.connectiontest;

import fi.ohtu.connectiontest.remoteconnection.ResponseListener;

/**
 * This class is just for demonstrating how you could use the mobility profile.
 */
public class ResponseHandler implements ResponseListener {
    private UIHandler uiHandler;

    public ResponseHandler(UIHandler uiHandler) {
        this.uiHandler = uiHandler;
    }

    @Override
    public void onGetMostLikelyDestination(String destination) {
        uiHandler.updateDestination(destination);
    }

    @Override
    public void onUnknownCode() {

    }
}
