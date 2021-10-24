package tokyo.northside.oxfordapi;

/**
 * Exception class for the OD API client.
 */
public class OxfordClientException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public OxfordClientException(final String message) {
        super(message);
    }
}
