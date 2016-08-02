package fi.ohtu.connectiontest;

import android.webkit.JavascriptInterface;

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
        List<String> destinations = messageHandler.getListOfMostProbableDestinations();
        String dests = "";
        try {

            for (int i = 0; i < destinations.size() ; i++) {
                if (i == destinations.size()-1) {
                    dests += destinations.get(i);
                } else {
                    dests += (destinations.get(i) + "!");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dests;
    }

    private String convertListOfTransportModes() {
        List<String> modes = messageHandler.getListOfPreferredTransportModes();
        String mods = "";
        try {

            for (int i = 0; i < modes.size() ; i++) {
                if (i == modes.size()-1) {
                    mods += modes.get(i);
                } else {
                    mods += (modes.get(i) + "!");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mods;
    }
}
