package fi.ohtu.connectiontest;

import android.content.Context;
import android.webkit.JavascriptInterface;

public class WebAppInterface {
    private Context context;
    private MessageHandler messageHandler;

    WebAppInterface(Context context, MessageHandler messageHandler) {
        this.context = context;
        this.messageHandler = messageHandler;
    }

    @JavascriptInterface
    public void update() {
        System.out.println("AAA");
    }

    @JavascriptInterface
    public String getMostProbableDestination() {
        return messageHandler.getMostProbableDestination();
    }
}