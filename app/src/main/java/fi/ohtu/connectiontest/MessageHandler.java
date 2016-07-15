package fi.ohtu.connectiontest;

import java.util.ArrayList;

import fi.ohtu.connectiontest.remoteconnection.MessageCreator;
import fi.ohtu.connectiontest.remoteconnection.MessageListener;

/**
 * This class is just for demonstrating how you could use the mobility profile.
 */
public class MessageHandler implements MessageListener {
    private MessageCreator mobilityProfile;
    private String nextDestination = "NO SUGGESTION";
    private ArrayList<String> nextDestinations;

    public MessageHandler(MessageCreator messageCreator) {
        this.mobilityProfile = messageCreator;
        this.nextDestinations = new ArrayList<>();
    }

    @Override
    public void onConnect() {
        mobilityProfile.requestMostLikelyDestination();
    }

    @Override
    public void onDisconnect() {

    }

    @Override
    public void onGetMostLikelyDestination(String destination) {
        nextDestination = destination;
    }

    @Override
    public void onGetListOfMostLikelyDestinations(ArrayList<String> destinations) {
        nextDestinations = destinations;
    }

    @Override
    public void onUnknownCode() {

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
     * @return List of the most probable destinations
     */
    public ArrayList<String> getListOfMostProbableDestinations() {
        return nextDestinations;
    }
}
