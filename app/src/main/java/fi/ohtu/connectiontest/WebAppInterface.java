package fi.ohtu.connectiontest;

import android.webkit.JavascriptInterface;

import org.json.JSONArray;

import java.util.ArrayList;

import fi.ohtu.mobilityprofileapi.MessageCreator;

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
    public String getListOfMostProbableDestinations() {
        return convertListOfDestinationsToJson();
    }

    @JavascriptInterface
    public void sendSearchedRoute(String startLocation, String destination) {
        messageCreator.sendSearchedRoute(startLocation, destination);
    }

    private String convertListOfDestinationsToJson() {
        ArrayList<String> destinations = messageHandler.getListOfMostProbableDestinations();
        String jsonDestinations = "";
        try {

            for (int i = 0; i < destinations.size() ; i++) {
                if (i == destinations.size()-1) {
                    jsonDestinations += destinations.get(i);
                } else {
                    jsonDestinations += (destinations.get(i) + "!");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonDestinations;
    }
}
