package fi.ohtu.mobilityprofileapi;

import java.util.List;

/**
 * Interface for listening to connection and disconnection events and incoming responses from
 * Mobility Profile.
 */
public interface MessageListener {
    /**
     * This method is called when we get connected to the mobility profile.
     */
    void onConnect();

    /**
     * This method is called when we get disconnected from the mobility profile.
     */
    void onDisconnect();

    /**
     * This method is called when Mobility Profile responds to our request. List of suggestions
     * will be given as a parameter.
     *
     * @param suggestions List of the most likely next destinations
     */
    void onSuggestionsResponse(List<String> suggestions);

    /**
     * This method is called when Mobility Profile responds to our request. The first suggestion
     * will be given as a parameter.
     *
     * @param suggestion The most likely next destination
     */
    void onSuggestionsResponse(String suggestion);

    /**
     * This method is called when Mobility Profile responds to our request. List of transport mode preferences
     * will be given as a parameter.
     *
     * @param preferences List of the transport mode preferences
     */
    void onTransportPreferencesResponse(List<String> preferences);

    /**
     * This method is called if Mobility Profile sends us an empty list of suggestions.
     */
    void onNoSuggestions();

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
}
