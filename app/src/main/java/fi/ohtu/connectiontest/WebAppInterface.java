package fi.ohtu.connectiontest;

import android.webkit.JavascriptInterface;

import fi.ohtu.mobilityprofileapi.MobilityProfileInterface;
import fi.ohtu.mobilityprofileapi.Place;

public class WebAppInterface {
    private MessageHandler messageHandler;
    private MobilityProfileInterface mobilityProfileInterface;

    private float prevStartLon, prevStartLat, prevEndLon, prevEndLat;
    private long lastSearchTime = 0;

    public WebAppInterface(MessageHandler messageHandler, MobilityProfileInterface mobilityProfileInterface) {
        this.messageHandler = messageHandler;
        this.mobilityProfileInterface = mobilityProfileInterface;
    }

    @JavascriptInterface
    public String getListOfMostProbableDestinations() {
        return messageHandler.getMostProbableDestinations();
    }

    @JavascriptInterface
    public void sendSearchedRoute(float startLon, float startLat, float endLon, float endLat ) {
        if ((startLon == 0 && startLat == 0) || (endLon == 0 && endLat == 0)) {
            // One of the coordinates was at (0, 0), which is basically equal to null
            return;
        }

        if (startLon == prevStartLon && startLat == prevStartLat && endLon == prevEndLon
                && endLat == prevEndLat && System.currentTimeMillis() - lastSearchTime < 1000) {
            // Digitransit spams the same search multiple times so just ignore it if it was the same
            // as previous and within one second.
            return;
        }

        prevStartLon = startLon;
        prevStartLat = startLat;
        prevEndLon = endLon;
        prevEndLat = endLat;
        lastSearchTime = System.currentTimeMillis();

        Place startLocation = new Place(startLon, startLat);
        Place endLocation = new Place(endLon, endLat);
        mobilityProfileInterface.sendSearchedRoute(startLocation, endLocation);
    }

    @JavascriptInterface
    public String getTransportModePreferences() {
        return messageHandler.getListOfPreferredTransportModes();
    }
}
