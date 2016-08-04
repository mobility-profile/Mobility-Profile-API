package fi.ohtu.connectiontest;

import android.webkit.JavascriptInterface;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

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
        return convertListOfDestinations();
    }

    @JavascriptInterface
    public void sendSearchedRoute(String startLocation, String destination) {
        messageCreator.sendSearchedRoute(startLocation, destination);
    }

    @JavascriptInterface
    public String getTransportModePreferences() {
        return convertListOfTransportModes();
    }

    private String convertListOfDestinations() {
        JSONObject object = new JSONObject();
        JSONArray destinationsArray = new JSONArray();
        List<String> destinations = messageHandler.getListOfMostProbableDestinations();

        try {
            for (int i = 0; i < destinations.size() ; i++) {
                destinationsArray.put(destinations.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            object.put("destinations", destinationsArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object.toString();
    }

    private String convertListOfTransportModes() {
        JSONObject object = new JSONObject();
        JSONArray modesArray = new JSONArray();
        List<String> modes = messageHandler.getListOfPreferredTransportModes();
        try {
            for (int i = 0; i < modes.size() ; i++) {
                modesArray.put(modes.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            object.put("transport_modes", modesArray);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return object.toString();
    }
}
