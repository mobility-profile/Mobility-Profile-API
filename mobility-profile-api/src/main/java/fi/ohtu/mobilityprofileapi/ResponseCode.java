package fi.ohtu.mobilityprofileapi;

/**
 * Common codes for communicating between your application and Mobility Profile.
 */
public class ResponseCode {
    /**
     * Request for route suggestions inside a city or a metropolis.
     */
    public static final int REQUEST_INTRA_CITY_SUGGESTIONS = 101;

    /**
     * Send information about a searched route.
     */
    public static final int SEND_SEARCHED_ROUTE = 102;

    /**
     * Request for route suggestions between cities.
     */
    public static final int REQUEST_INTER_CITY_SUGGESTIONS = 103;


    /**
     * Request for transport mode preferences.
     */
    public static final int REQUEST_TRANSPORT_PREFERENCES = 105;

    /**
     * Response containing a list of likely suggestions.
     */
    public static final int RESPOND_MOST_LIKELY_SUGGESTIONS = 201;

    /**
     * Response containing a list of transport mode preferences.
     */
    public static final int RESPOND_TRANSPORT_PREFERENCES = 205;

    /**
     * Error code for unknown codes.
     */
    public static final int ERROR_UNKNOWN_CODE = 401;
}
