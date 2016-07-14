package fi.ohtu.connectiontest;

import fi.ohtu.connectiontest.remoteconnection.MessageCreator;
import fi.ohtu.connectiontest.remoteconnection.MessageListener;

/**
 * This class is just for demonstrating how you could use the mobility profile.
 */
public class MessageHandler implements MessageListener {
    private MessageCreator mobilityProfile;
    private String nextDestination = "NO SUGGESTION";
    private String startLocation = "NO SUGGESTION";

    public MessageHandler(MessageCreator messageCreator) {
        this.mobilityProfile = messageCreator;
    }

    @Override
    public void onConnect() {
        mobilityProfile.requestMostLikelyDestination();
        mobilityProfile.requestStartLocation();
    }

    @Override
    public void onDisconnect() {}

    @Override
    public void onGetMostLikelyDestination(String destination) {
        nextDestination = destination;
    }

    @Override
    public void onGetStartLocation(String location) {
        startLocation = location;
    }

    @Override
    public void onUnknownCode() {}

    /**
     * Returns the most probable destination Mobility Profile has suggested to us.
     *
     * @return Most probable destination
     */
    public String getMostProbableDestination() {
        return nextDestination;
    }

    /**
     * Returns the start location Mobility Profile has given to us.
     *
     * @return Start location
     */
    public String getStartLocation() {
        return startLocation;
    }
}
