package fi.ohtu.connectiontest;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import fi.ohtu.connectiontest.remoteconnection.MobilityProfileApp;
import fi.ohtu.connectiontest.remoteconnection.RequestCreator;

public class MainActivity extends MobilityProfileApp {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UIHandler uiHandler = new UIHandler();
        uiHandler.setDestinationField((EditText) findViewById(R.id.destination));

        setResponseListener(new ResponseHandler(uiHandler));
    }

    public void invoke(View view) {
        mobilityProfile.requestMostLikelyDestination();
    }
}
