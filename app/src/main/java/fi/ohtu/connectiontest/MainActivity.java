package fi.ohtu.connectiontest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import fi.ohtu.connectiontest.remoteconnection.MessageListener;
import fi.ohtu.connectiontest.remoteconnection.MobilityProfileApp;

public class MainActivity extends MobilityProfileApp {
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setMessageListener(new MessageListener() {
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
        });

        MessageHandler messageHandler = new MessageHandler(mobilityProfile);
        setMessageListener(messageHandler);

        webview = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webview.setWebViewClient(new WebViewClient());

        webview.addJavascriptInterface(new WebAppInterface(this), "MobilityProfile");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }
}
