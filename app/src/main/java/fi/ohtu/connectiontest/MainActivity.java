package fi.ohtu.connectiontest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
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

        webview = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webview.setWebViewClient(new MyWebViewClient());

        webview.addJavascriptInterface(new WebAppInterface(this), "MobilityProfile");

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




        /*UIHandler uiHandler = new UIHandler(mobilityProfile);
        uiHandler.setDestinationField((EditText) findViewById(R.id.destination));

        // Popup window
        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext()
                .getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.popup, null);
        final PopupWindow askTripPopup = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        uiHandler.setAskTripPopup(askTripPopup);
        uiHandler.setPopupText((TextView) popupView.findViewById(R.id.popuptext));
        uiHandler.setBtnYes((Button) popupView.findViewById(R.id.yes));
        uiHandler.setBtnNo((Button) popupView.findViewById(R.id.no));

        setMessageListener(new MessageHandler(uiHandler, mobilityProfile));*/
    }

    /*
    public void searchTrip(View view) {
        Toast.makeText(getApplicationContext(), "etsitään trippiä", Toast.LENGTH_SHORT).show();
        mobilityProfile.sendUsedDestination(((EditText) this.findViewById(R.id.destination)).getText().toString());
    }
    */

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
