package fi.ohtu.connectiontest;

import android.os.Bundle;

import java.util.ArrayList;

import fi.ohtu.connectiontest.remoteconnection.AbstractMessageListener;
import fi.ohtu.connectiontest.remoteconnection.MobilityProfileApp;

/*
This is a basic example of how to integrate Mobility Profile to your journey planner app.
 */

// Your MainActivity should extend MobilityProfileApp
public class ExampleActivity extends MobilityProfileApp {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // This method should be called on your activity's onCreate() method before making any
        // requests to the Mobility Profile. You can use AbstractMessageListener to handle the
        // events or make your own class that implements the MessageListener.
        setMessageListener(new AbstractMessageListener() {
            @Override
            public void onConnect() {
                // We got connected to the Mobility Profile, just request the most likely next
                // destination.
                mobilityProfile.requestMostLikelyDestination();
            }

            @Override
            public void onGetMostLikelyDestination(String destination) {
                // Mobility Profile responded to our request and sent the most probable next
                // destination. Just put the destination to your journey planner's destination
                // field or suggest it to the user in some other way.
            }

            @Override
            public void onGetListOfMostLikelyDestinations(ArrayList<String> destinations) {
                // Mobility Profile responded to our request and sent a list of the most probable next
                // destinations. Suggest destinations to the user in a pop up list or in some other way.
            }

            // There are more methods for handling the events, check MessageListener's javadoc for
            // more information.
        });
    }

    public void searchTrip() {
        // Some journey planner application logic.
        // ...

        // When the user has searched for a trip, we should send information about it to the
        // MobilityProfile so future suggestions can be more accurate.
        mobilityProfile.sendUsedDestination("Used destination");
    }
}
