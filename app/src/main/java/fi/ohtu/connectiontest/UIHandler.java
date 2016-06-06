package fi.ohtu.connectiontest;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import fi.ohtu.connectiontest.remoteconnection.RequestCreator;
import fi.ohtu.connectiontest.remoteconnection.ResponseListener;

/**
 * This class is just for demonstrating how you could use the mobility profile.
 */
public class UIHandler {
    private EditText destinationField;
    private PopupWindow askTripPopup;
    private TextView popupText;
    private Button btnYes;

    public void setDestinationField(EditText destinationField) {
        this.destinationField = destinationField;
    }
    
    public void setAskTripPopup(PopupWindow askTripPopup) {
        this.askTripPopup = askTripPopup;
    }
    
    public void setPopupText(TextView popupText) {
        this.popupText = popupText;
    }
    
    public void setBtnYes(Button yes) {
        this.btnYes = yes;
    }

    public void updateDestination(String destination) {
        assert destinationField != null;
        destinationField.setText(destination);
    }
    
    
    public void showPopup(final String destination) {
        askTripPopup.showAsDropDown(askTripPopup.getContentView());
        popupText.setText("Are you going to " + destination + "?");

        assert btnYes != null;
        btnYes.setOnClickListener(new Button.OnClickListener(){
            
            @Override
            public void onClick(View v) {
                // Todo: send "yes" message to Mobility Profile
                updateDestination(destination);
                askTripPopup.dismiss();
            }
        });
        
    }
}
