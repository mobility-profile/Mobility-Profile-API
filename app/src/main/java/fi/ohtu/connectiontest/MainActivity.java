package fi.ohtu.connectiontest;

import android.os.Bundle;
import android.view.View;
import android.view.LayoutInflater;import android.widget.EditText;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.Button;
import android.widget.TextView;

import fi.ohtu.connectiontest.remoteconnection.MobilityProfileApp;
import fi.ohtu.connectiontest.remoteconnection.RequestCreator;

public class MainActivity extends MobilityProfileApp {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UIHandler uiHandler = new UIHandler();
        uiHandler.setDestinationField((EditText) findViewById(R.id.destination));
        
        // Popup window
        LayoutInflater layoutInflater = (LayoutInflater)getBaseContext()
          .getSystemService(LAYOUT_INFLATER_SERVICE);  
        View popupView = layoutInflater.inflate(R.layout.popup, null);
        final PopupWindow askTripPopup = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        Button btnNo = (Button) popupView.findViewById(R.id.no);
        btnNo.setOnClickListener(new Button.OnClickListener(){
            
            @Override
            public void onClick(View v) {
                // Todo: send "false" message to mobility profile
                askTripPopup.dismiss();
            }
        });
uiHandler.setAskTripPopup(askTripPopup);        
uiHandler.setPopupText((TextView) popupView.findViewById(R.id.popuptext));
uiHandler.setBtnYes((Button) popupView.findViewById(R.id.yes));

        setResponseListener(new ResponseHandler(uiHandler, mobilityProfile));
    }

    public void invoke(View view) {
        mobilityProfile.requestMostLikelyDestination();
    }
}
