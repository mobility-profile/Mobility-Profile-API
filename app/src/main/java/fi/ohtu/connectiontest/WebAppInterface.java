package fi.ohtu.connectiontest;

import android.webkit.JavascriptInterface;

import org.json.JSONArray;

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
    public JSONArray getListOfMostProbableDestinations() {
        return convertListOfDestinationsToJson();
    }

    @JavascriptInterface
    public void sendUsedSearchDestination(String destination) {
        //reittiopas kutsuu tätä kun tekee haun
        System.out.println("JEEEEE");
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
