package fi.ohtu.connectiontest;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

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
        webSettings.setAppCacheEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationEnabled(true);

        webview.addJavascriptInterface(new WebAppInterface(messageHandler, mobilityProfile), "MobilityProfile");

        webview.setWebChromeClient(new WebChromeClient() {
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
            }
        });

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

        webview.loadUrl("http://192.168.1.238:5000/");
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
