package fi.ohtu.connectiontest;

import java.util.ArrayList;
import java.util.List;

import fi.ohtu.mobilityprofileapi.MessageCreator;
import fi.ohtu.mobilityprofileapi.MessageListener;

/**
 * This class is just for demonstrating how you could use the mobility profile.
 */
public class MessageHandler implements MessageListener {
    private MessageCreator mobilityProfile;
    private String nextDestinations;
    private List<String> preferredTransportModes;

    public MessageHandler(MessageCreator messageCreator) {
        this.mobilityProfile = messageCreator;
        this.preferredTransportModes = new ArrayList<>();
    }

    @Override
    public void onConnect() {
        mobilityProfile.requestIntraCitySuggestions();
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
    public void onTransportPreferencesResponse(List<String> preferences) {
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
    public List<String> getListOfPreferredTransportModes() {
        return preferredTransportModes;
    }

}
