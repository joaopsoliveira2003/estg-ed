package GameAPI.Exceptions;

/**
 * NoSuchPortalException is thrown when a portal does not exist.
 */
public class NoSuchPortalException extends RuntimeException {

    /**
     * Constructs a NoSuchPortalException with no detail message.
     */
    public NoSuchPortalException() {
        super();
    }

    /**
     * Constructs a NoSuchPortalException with the specified detail message.
     *
     * @param message the detail message
     */
    public NoSuchPortalException(String message) {
        super(message);
    }
        
}
