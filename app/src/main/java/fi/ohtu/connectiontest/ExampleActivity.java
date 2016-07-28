package fi.ohtu.connectiontest;

import android.os.Bundle;

import java.util.ArrayList;

import fi.ohtu.mobilityprofileapi.AbstractMessageListener;
import fi.ohtu.mobilityprofileapi.MobilityProfileApp;

/*
This is a basic example of how to integrate Mobility Profile to your journey planner app.
 */

// Your Activity should extend MobilityProfileApp
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
                mobilityProfile.requestIntraCitySuggestions();
            }

            @Override
            public void onSuggestionsResponse(ArrayList<String> destinations) {
                // Mobility Profile responded to our request and sent a list of the most probable
                // next destinations. Suggest destinations to the user in a pop up list or in some
                // other way.
                // Note that onSuggestionsResponse(destination) will be called as well in case
                // you want to use just the first suggestion.
            }

            @Override
            public void onSuggestionsResponse(String destination) {
                // Same as onSuggestionsResponse, but instead of a list, this method gives just the
                // first suggestion.
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
        mobilityProfile.sendSearchedRoute("Start Location", "Used destination");
    }
}
