package fi.ohtu.connectiontest.remoteconnection;

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
    public void onUnknownCode() {
    }
}
