package fi.ohtu.mobilityprofileapi;

import com.cocoahero.android.geojson.GeoJSON;
import com.cocoahero.android.geojson.GeoJSONObject;

import org.json.JSONArray;
import org.json.JSONObject;

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
     * @param suggestions List of the most likely next destinations
     */
    void onSuggestionsResponse(ArrayList<String> suggestions);

    /**
     * This method is called when Mobility Profile responds to our request. The first suggestion
     * will be given as a parameter.
     *
     * @param suggestion The most likely next destination
     */
    void onSuggestionsResponse(String suggestion);

    /**
     * This method is called when Mobility Profile responds to our request. List of suggestions in
     * JSON form will be given as a parameter.
     * @param suggestions
     */
    void onSuggestionsResponse(JSONArray suggestions);

    /**
     * This method is called if Mobility Profile sends us an empty list of suggestions.
     */
    void onNoSuggestions();

    /**
     * This method is called when we send an unknown code to Mobility Profile.
     */
    void onUnknownCode();
}
