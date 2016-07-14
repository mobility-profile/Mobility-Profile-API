package fi.ohtu.connectiontest;

import android.webkit.JavascriptInterface;

public class WebAppInterface {
    private MessageHandler messageHandler;

    public WebAppInterface(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @JavascriptInterface
    public String getMostProbableDestination() {
        return messageHandler.getMostProbableDestination();
    }

    @JavascriptInterface
    public String getStartLocation() {
        return messageHandler.getStartLocation();
    }

    @JavascriptInterface
    public void sendUsedSearchDestination(String destination) {
        //reittiopas kutsuu tätä kun tekee haun
        System.out.println("JEEEEE");
    }
}
