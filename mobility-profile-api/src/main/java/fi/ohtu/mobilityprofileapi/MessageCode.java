package fi.ohtu.mobilityprofileapi;

/**
 * Common codes for communicating between your application and Mobility Profile.
 */
public class MessageCode {
    /**
     * Request for route suggestions.
     */
    public static final int REQUEST_SUGGESTIONS = 100;

    /**
     * Send information about a searched route.
     */
    public static final int SEND_SEARCHED_ROUTE = 110;

    /**
     * Request for transport mode preferences.
     */
    public static final int REQUEST_TRANSPORT_PREFERENCES = 120;

    /**
     * Response containing a list of likely suggestions.
     */
    public static final int RESPOND_SUGGESTIONS = 200;

    /**
     * Response containing a list of transport mode preferences.
     */
    public static final int RESPOND_TRANSPORT_PREFERENCES = 220;

    /**
     * Error code for unknown codes.
     */
    public static final int ERROR_UNKNOWN_CODE = 400;
}
