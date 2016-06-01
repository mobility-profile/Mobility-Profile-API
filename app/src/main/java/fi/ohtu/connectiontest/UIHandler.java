package fi.ohtu.connectiontest;

import android.widget.EditText;

import fi.ohtu.connectiontest.remoteconnection.ResponseListener;

/**
 * This class is just for demonstrating how you could use the mobility profile.
 */
public class UIHandler {
    private EditText destinationField;

    public void setDestinationField(EditText destinationField) {
        this.destinationField = destinationField;
    }

    public void updateDestination(String destination) {
        assert destinationField != null;
        destinationField.setText(destination);
    }
}
