package Game.Exceptions;

/**
 * NoSuchPlaceException is thrown when a portal does not exist.
 */
public class NoSuchPlaceException extends RuntimeException {

    /**
     * Constructs a NoSuchPlaceException with no detail message.
     */
    public NoSuchPlaceException() {
        super();
    }

    /**
     * Constructs a NoSuchPlaceException with the specified detail message.
     *
     * @param message the detail message
     */
    public NoSuchPlaceException(String message) {
        super(message);
    }
        
}
