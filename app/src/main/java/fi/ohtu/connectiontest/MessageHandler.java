package fi.ohtu.connectiontest;

import java.util.List;

import fi.ohtu.mobilityprofileapi.MobilityProfileInterface;
import fi.ohtu.mobilityprofileapi.MessageListener;
import fi.ohtu.mobilityprofileapi.Place;

/**
 * This class is just for demonstrating how you could use the mobility profile.
 */
public class MessageHandler implements MessageListener {
    private MobilityProfileInterface mobilityProfile;
    private String nextDestinations = "NO SUGGESTION";
    private String preferredTransportModes;

    public MessageHandler(MobilityProfileInterface mobilityProfileInterface) {
        this.mobilityProfile = mobilityProfileInterface;
    }

    @Override
    public void onConnect() {
        mobilityProfile.requestSuggestions();
        mobilityProfile.requestTransportModePreferences();
    }

    @Override
    public void onDisconnect() {
    }

    @Override
    public void onSuggestionsResponse(String suggestions) {
        nextDestinations = suggestions;
    }

    @Override
    public void onSuggestionsResponse(List<Place> places) {
    }

    @Override
    public void onTransportPreferencesResponse(String preferences) {
        preferredTransportModes = preferences;
    }

    @Override
    public void onNoSuggestions() {
    }

    @Override
    public void onUnknownRequest() {
    }

    @Override
    public void onUnknownResponse(int code) {
    }

    @Override
    public void onNotAvailable() {
    }

    /**
     * Returns a list of the most probable destinations Mobility Profile has suggested to us.
     *
     * @return List of the most probable destinations
     */
    public String getMostProbableDestinations() {
        return nextDestinations;
    }

    /**
     * Returns a list of the preferred transport modes.
     *
     * @return List of the preferred transport modes
     */
    public String getListOfPreferredTransportModes() {
        return preferredTransportModes;
    }

}
