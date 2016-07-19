package fi.ohtu.connectiontest;

import android.webkit.JavascriptInterface;

import org.json.JSONArray;

import java.util.ArrayList;

import fi.ohtu.connectiontest.remoteconnection.MessageCreator;

public class WebAppInterface {
    private MessageHandler messageHandler;
    private MessageCreator messageCreator;

    public WebAppInterface(MessageHandler messageHandler, MessageCreator messageCreator) {
        this.messageHandler = messageHandler;
        this.messageCreator = messageCreator;
    }

    @JavascriptInterface
    public String getMostProbableDestination() {
        return messageHandler.getMostProbableDestination();
    }

    @JavascriptInterface
    public JSONArray getListOfMostProbableDestinations() {
        return convertListOfDestinationsToJson();
    }

    @JavascriptInterface
    public String getStartLocation() {
        return messageHandler.getStartLocation();
    }

    @JavascriptInterface
    public void sendUsedRoute(String startLocation, String destination) {
        messageCreator.sendUsedRoute(startLocation, destination);
    }

    private JSONArray convertListOfDestinationsToJson() {
        ArrayList<String> destinations = messageHandler.getListOfMostProbableDestinations();
        JSONArray jsonDestinations = new JSONArray();
        try {
            for (String destination : destinations) {
                jsonDestinations.put(destination);
            }
        } catch (Exception e) {
        }
        return jsonDestinations;
    }
}
