package fi.ohtu.connectiontest;

import fi.ohtu.connectiontest.remoteconnection.MessageCreator;
import fi.ohtu.connectiontest.remoteconnection.MessageListener;

/**
 * This class is just for demonstrating how you could use the mobility profile.
 */
public class MessageHandler implements MessageListener {
    private UIHandler uiHandler;
    private MessageCreator mobilityProfile;

    public MessageHandler(UIHandler uiHandler, MessageCreator messageCreator) {
        this.uiHandler = uiHandler;
        this.mobilityProfile = messageCreator;
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
        //uiHandler.showPopup(destination);
        uiHandler.updateDestination(destination);
    }

    @Override
    public void onUnknownCode() {

    }
}
