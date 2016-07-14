package fi.ohtu.connectiontest;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class WebAppInterface {
    private Context context;
    private MessageHandler messageHandler;

    public WebAppInterface(Context context, MessageHandler messageHandler) {
        this.context = context;
        this.messageHandler = messageHandler;
    }

    @JavascriptInterface
    public String getMostProbableDestination() {
        return messageHandler.getMostProbableDestination();
    }

    @JavascriptInterface
    public String getStartLocation() {
        return messageHandler.getStartLocation();
    }

    @JavascriptInterface
    public void sendUsedSearchDestination(String destination) {
        //reittiopas kutsuu tätä kun tekee haun
        Toast.makeText(context, destination, Toast.LENGTH_SHORT).show();
        System.out.println("JEEEEE");
    }
}