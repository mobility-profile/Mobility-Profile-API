package fi.ohtu.mobilityprofileapi;

/**
 * Class that represents a location.
 */
public class Place {
    private String address;
    private float longitude;
    private float latitude;

    /**
     * Creates a place object with the given coordinates.
     *
     * @param longitude Longitude of the place
     * @param latitude  Latitude of the place
     */
    public Place(float longitude, float latitude) {
        this("", longitude, latitude);
    }

    /**
     * Creates a place object with the given parameters.
     *
     * @param address   Address of the place
     * @param longitude Longitude of the place
     * @param latitude  Latitude of the place
     */
    public Place(String address, float longitude, float latitude) {
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }
}
