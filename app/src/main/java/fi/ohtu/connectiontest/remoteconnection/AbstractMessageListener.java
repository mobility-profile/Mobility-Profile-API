package fi.ohtu.connectiontest.remoteconnection;

import java.util.ArrayList;

/**
 * This class is just for convenience so you don't have to override all methods from
 * MessageListener.
 */
public abstract class AbstractMessageListener implements MessageListener {
    @Override
    public void onConnect() {
    }

    @Override
    public void onDisconnect() {
    }

    @Override
    public void onGetMostLikelyDestination(String destination) {
    }

    @Override
    public void onGetListOfMostLikelyDestinations(ArrayList<String> destinations) {
    }

    @Override
    public void onGetStartLocation(String location) {
    }

    @Override
    public void onUnknownCode() {
    }
}
