package fi.ohtu.connectiontest.remoteconnection;

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
     * This method is called when the mobility profile sends us the next destination
     * the user is most likely going.
     *
     * @param destination Most likely next destination
     */
    void onGetMostLikelyDestination(String destination);

    /**
     * This method is called when the mobility profile sends us the list of next destinations
     * the user is most likely going.
     * @param destination List of most likely next destinations
     */
    void onGetListOfMostLikelyDestinations(ArrayList<String> destination);


    /**
     * This method is called when we sent an unknown code to the mobility profile.
     */
    void onUnknownCode();
}
