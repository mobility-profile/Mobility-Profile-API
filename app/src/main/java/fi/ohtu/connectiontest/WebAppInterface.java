package fi.ohtu.connectiontest;

import android.webkit.JavascriptInterface;

import fi.ohtu.mobilityprofileapi.MessageCreator;

public class WebAppInterface {
    private MessageHandler messageHandler;
    private MessageCreator messageCreator;

    public WebAppInterface(MessageHandler messageHandler, MessageCreator messageCreator) {
        this.messageHandler = messageHandler;
        this.messageCreator = messageCreator;
    }

    @JavascriptInterface
    public String getListOfMostProbableDestinations() {
        return messageHandler.getMostProbableDestinations();
    }

    @JavascriptInterface
    public void sendSearchedRoute(String startLocation, String destination) {
        messageCreator.sendSearchedRoute(startLocation, destination);
    }

    @JavascriptInterface
    public String getTransportModePreferences() {
        return messageHandler.getListOfPreferredTransportModes();
    }
}
