package fi.ohtu.connectiontest;

import android.webkit.JavascriptInterface;

import java.util.ArrayList;

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
    public ArrayList<String> getListOfMostProbableDestinations() {
        return messageHandler.getListOfMostProbableDestionations();
    }

    @JavascriptInterface
    public void sendUsedSearchDestination(String destination) {
        //reittiopas kutsuu tätä kun tekee haun
        System.out.println("JEEEEE");
    }
}
