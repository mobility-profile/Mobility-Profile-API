package fi.ohtu.mobilityprofileapi;

import java.util.List;

/**
 * This class is just for convenience so you don't have to override all methods from
 * {@link MessageListener}.
 */
public abstract class AbstractMessageListener implements MessageListener {
    @Override
    public void onConnect() {
    }

    @Override
    public void onDisconnect() {
    }

    @Override
    public void onSuggestionsResponse(String destination) {
    }

    @Override
    public void onSuggestionsResponse(List<String> destinations) {
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
}
