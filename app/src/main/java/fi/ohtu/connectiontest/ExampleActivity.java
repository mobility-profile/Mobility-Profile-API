package fi.ohtu.connectiontest;

import android.os.Bundle;

import fi.ohtu.mobilityprofileapi.AbstractMessageListener;
import fi.ohtu.mobilityprofileapi.MobilityProfileApp;
import fi.ohtu.mobilityprofileapi.MobilityProfileInterface;
import fi.ohtu.mobilityprofileapi.Place;

/*
This is a basic example of how to integrate Mobility Profile to your journey planner app.
 */

// Your Activity should extend MobilityProfileApp
public class ExampleActivity extends MobilityProfileApp {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // This method should be called before making any requests or sending any information to
        // Mobility Profile. Otherwise the application will crash.
        mobilityProfileInterface.setMode(MobilityProfileInterface.MODE_INTRACITY);

        // This method should be called on your activity's onCreate() method before making any
        // requests to the Mobility Profile. You can use AbstractMessageListener to handle some
        // of the events or make your own class that implements the MessageListener.
        setMessageListener(new AbstractMessageListener() {
            @Override
            public void onConnect() {
                // We got connected to Mobility Profile, just request a list of suggested
                // destinations.
                mobilityProfileInterface.requestSuggestions();
            }

            @Override
            public void onSuggestionsResponse(String suggestions) {
                // Mobility Profile responded to our request and sent a string of the most probable
                // next destinations in GeoJSON form in JSONArray. Suggest destinations to the user
                // in a pop up list or in some other way.

                // Note that onSuggestionsResponse(List<Place> suggestions) will be called as
                // well in case you don't want to handle JSON strings.

                // If this method is called, there is always at least one suggestion in the list.
                // If Mobility Profile didn't have any relevant suggestions, onNoSuggestions() is
                // called instead.
            }
        });
    }

    public void searchTrip() {
        // Some journey planner application logic.
        // ...

        // When the user has searched for a trip, we should send information about it to
        // MobilityProfile so future suggestions can be more accurate.
        mobilityProfileInterface.sendSearchedRoute(new Place(20.00f, 30.00f), new Place(40.00f, 54.00f));
    }
}
