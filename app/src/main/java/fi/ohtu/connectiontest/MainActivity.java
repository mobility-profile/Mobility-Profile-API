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

        MessageHandler messageHandler = new MessageHandler(mobilityProfile);
        setMessageListener(messageHandler);

        webview = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webview.setWebViewClient(new MyWebViewClient());

        webview.addJavascriptInterface(new WebAppInterface(this, messageHandler), "MobilityProfile");

        /*
        DEVELOPMENT:
        After changes to digitransit-ui run "npm run build" + "heroku local",
        connect your mobile to same network as your computer,
        change URL to your localhost ip (on mac run ifconfig and find ip e.g. http://192.168.1.124:5000/)
        After changes to Mobility-Profile-API just hit RUN

        PRODUCTION:
        Push your digitransit-ui changes to remote,
        change URL to https://digitransit.herokuapp.com/
         */
        webview.loadUrl("http://192.168.1.124:5000/");
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

    private class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            System.out.println("AAA " + url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            System.out.println("AAA " + url);
            String str = "EXACTUM";
            view.loadUrl("javascript:toinenSuunta('"+str+"')");
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            super.shouldOverrideUrlLoading(view, url);
            System.out.println("AAA " + url);

            return false;
        }

        @Override
        public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
            System.out.println("AAA " + url);
        }
    }
}
