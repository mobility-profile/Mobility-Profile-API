package fi.ohtu.mobilityprofileapi;

import java.util.List;

/**
 * Interface for listening to connection and disconnection events and incoming responses from
 * Mobility Profile.
 */
public interface MessageListener {
    /**
     * This method is called when we get connected to Mobility Profile.
     */
    void onConnect();

    /**
     * This method is called when we get disconnected from Mobility Profile.
     */
    void onDisconnect();

    /**
     * This method is called when Mobility Profile responds to our request. List of suggestions
     * will be given as a GEOJSON-string.
     *
     * @param geojson List of the most likely next destinations
     */
    void onSuggestionsResponse(String geojson);

    /**
     * This method is called when Mobility Profile responds to our request. List of places
     * will be given as a list of Place-objects.
     *
     * @param places List of the most likely next destinations
     */
    void onSuggestionsResponse(List<Place> places);

    /**
     * This method is called if Mobility Profile sends us an empty list of suggestions.
     */
    void onNoSuggestions();

    /**
     * This method is called when Mobility Profile responds to our request to get information about
     * transport mode preferences. List of transport mode preferences will be given as a parameter.
     *
     * @param preferences List of transport mode preferences
     */
    void onTransportPreferencesResponse(String preferences);

    /**
     * This method is called when we send an unknown code to Mobility Profile.
     */
    void onUnknownRequest();

    /**
     * This method is called when Mobility Profile sends us an unknown code.
     *
     * @param code Response code
     */
    void onUnknownResponse(int code);

    /**
     * This method is called if connecting to Mobility Profile failed.
     */
    void onNotAvailable();
}
