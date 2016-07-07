package fi.ohtu.connectiontest;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class WebAppInterface {
    private Context context;
    private MessageHandler messageHandler;

    WebAppInterface(Context context, MessageHandler messageHandler) {
        this.context = context;
        this.messageHandler = messageHandler;
    }

    @JavascriptInterface
    public String getMostProbableDestination() {
        return messageHandler.getMostProbableDestination();
    }

    public void update(String toast) {
        System.out.println("JEE");
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
    }
}