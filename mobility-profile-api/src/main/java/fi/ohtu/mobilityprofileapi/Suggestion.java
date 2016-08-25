package fi.ohtu.mobilityprofileapi;

/**
 * Class that represents one suggestion given by Mobility Profile.
 */
public class Suggestion {
    private String address;
    private double longitude;
    private double latitude;

    /**
     * Creates a suggestion object with the given parameters.
     *
     * @param address   Address of the suggestion
     * @param longitude Longitude off the suggestion
     * @param latitude  Latitude of the suggestion
     */
    public Suggestion(String address, double longitude, double latitude) {
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}
