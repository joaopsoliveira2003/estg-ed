package Game.Exceptions;

/**
 * AlreadyExistsException is thrown when an object already exists.
 */
public class AlreadyExistsException extends RuntimeException {

    /**
     * Constructs a AlreadyExistsException with no detail message.
     */
    public AlreadyExistsException() {
        super();
    }

    /**
     * Constructs a AlreadyExistsException with the specified detail message.
     *
     * @param message the detail message
     */
    public AlreadyExistsException(String message) {
        super(message);
    }

}
