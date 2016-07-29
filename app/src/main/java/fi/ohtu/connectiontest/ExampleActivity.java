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
        // requests to the Mobility Profile. You can use AbstractMessageListener to handle some
        // of the events or make your own class that implements the MessageListener.
        setMessageListener(new AbstractMessageListener() {
            @Override
            public void onConnect() {
                // We got connected to Mobility Profile, just request a list of suggested
                // destinations. Note that this method is used for trips inside a city (or
                // metropolis).
                mobilityProfile.requestIntraCitySuggestions();

                // If you want to request suggestions for a trip between cities, use
                // mobilityProfile.requestInterCitySuggestions() instead.
            }

            @Override
            public void onDisconnect() {
                // We got disconnected from Mobility Profile. This should only happen when the user
                // exits the activity or opens a new activity.
            }

            @Override
            public void onSuggestionsResponse(ArrayList<String> suggestions) {
                // Mobility Profile responded to our request and sent a list of the most probable
                // next destinations. Suggest destinations to the user in a pop up list or in some
                // other way.

                // Note that onSuggestionsResponse(destination) will be called as well in case
                // you want to use just the first suggestion.

                // If this method is called, there is always at least one suggestion in the list.
                // If Mobility Profile didn't have any relevant suggestions, onNoSuggestions() is
                // called instead.
            }

            @Override
            public void onSuggestionsResponse(String suggestion) {
                // Same as onSuggestionsResponse, but instead of a list, this method gives just the
                // first suggestion.

                // If this method is called, it has always a valid suggestion as a parameter.
            }

            @Override
            public void onNoSuggestions() {
                // Mobility Profile got our request, but didn't have any relevant suggestions.
            }

            @Override
            public void onUnknownCode() {
                // This method is called when we send an unknown code to Mobility Profile. This
                // basically means the user is using an incompatible version of Mobility Profile
                // that doesn't support the request we made. You can suggest the user to update
                // their Mobility Profile or just ignore this completely.
            }
        });
    }

    public void searchTrip() {
        // Some journey planner application logic.
        // ...

        // When the user has searched for a trip, we should send information about it to
        // MobilityProfile so future suggestions can be more accurate.
        mobilityProfile.sendSearchedRoute("Start Location", "Used destination");
    }
}
