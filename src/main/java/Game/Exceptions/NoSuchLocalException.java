package Game.Exceptions;

/**
 * NoSuchLocalException is thrown when a local does not exist.
 */
public class NoSuchLocalException extends RuntimeException {

    /**
     * Constructs a NoSuchLocalException with no detail message.
     */
    public NoSuchLocalException() {
        super();
    }

    /**
     * Constructs a NoSuchLocalException with the specified detail message.
     *
     * @param message the detail message
     */
    public NoSuchLocalException(String message) {
        super(message);
    }
        
}
