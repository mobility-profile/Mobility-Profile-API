package fi.ohtu.mobilityprofileapi;

import java.util.ArrayList;

/**
 * Interface for listening to incoming requests from the mobility profile.
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
     * @param destination List of most likely next destinations
     */
    void onSuggestionsResponse(ArrayList<String> destination);

    /**
     * This method is called when Mobility Profile responds to our request. The first suggestion
     * will be given as a parameter.
     *
     * @param destination Most likely next destination
     */
    void onSuggestionsResponse(String destination);

    /**
     * This method is called if Mobility Profile sends us an empty list of suggestions.
     */
    void onNoSuggestions();

    /**
     * This method is called when we send an unknown code to Mobility Profile.
     */
    void onUnknownCode();
}
