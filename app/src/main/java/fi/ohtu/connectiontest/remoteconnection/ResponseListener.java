package fi.ohtu.connectiontest.remoteconnection;

/**
 * Interface for listening to incoming requests from the mobility profile.
 */
public interface ResponseListener {
    /**
     * This method is called when we get connected to the mobility profile.
     */
    void onConnect();

    /**
     * This method is called when we get disconnected from the mobility profile.
     */
    void onDisconnect();

    /**
     * This method is called when the mobility profile sends us the next destination
     * the user is most likely going.
     *
     * @param destination Most likely next destination
     */
    void onGetMostLikelyDestination(String destination);

    /**
     * This method is called when we sent an unknown code to the mobility profile.
     */
    void onUnknownCode();
}
