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
    private String nextDestination = "NO SUGGESTION";
    private List<String> nextDestinations;

    public MessageHandler(MessageCreator messageCreator) {
        this.mobilityProfile = messageCreator;
        this.nextDestinations = new ArrayList<>();
    }

    @Override
    public void onConnect() {
        mobilityProfile.requestIntraCitySuggestions();
    }

    @Override
    public void onDisconnect() {
    }

    @Override
    public void onSuggestionsResponse(String suggestion) {
        nextDestination = suggestion;
    }

    @Override
    public void onSuggestionsResponse(List<String> suggestions) {
        nextDestinations = suggestions;
        if (!nextDestinations.isEmpty()) nextDestination = nextDestinations.get(0);
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
     * Returns the most probable destination Mobility Profile has suggested to us.
     *
     * @return Most probable destination
     */
    public String getMostProbableDestination() {
        return nextDestination;
    }

    /**
     * Returns a list of the most probable destinations Mobility Profile has suggested to us.
     *
     * @return List of the most probable destinations
     */
    public List<String> getListOfMostProbableDestinations() {
        return nextDestinations;
    }

}
