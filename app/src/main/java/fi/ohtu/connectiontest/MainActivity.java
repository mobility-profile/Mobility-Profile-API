package fi.ohtu.connectiontest;

import android.os.Bundle;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import fi.ohtu.connectiontest.remoteconnection.MobilityProfileApp;

public class MainActivity extends MobilityProfileApp {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UIHandler uiHandler = new UIHandler(mobilityProfile);
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

        setResponseListener(new MessageHandler(uiHandler, mobilityProfile));
    }

    public void searchTrip(View view) {
        Toast.makeText(getApplicationContext(), "etsitään trippiä", Toast.LENGTH_SHORT).show();
        mobilityProfile.sendUsedDestination(((EditText) this.findViewById(R.id.destination)).getText().toString());
    }
}
