package GameAPI.Exceptions;

/**
 * InvalidArgumentException is thrown when an invalid argument is passed to a method.
 */
public class InvalidArgumentException extends RuntimeException {

    /**
     * Constructs an InvalidArgumentException with no detail message.
     */
    public InvalidArgumentException() {
        super();
    }

    /**
     * Constructs an InvalidArgumentException with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidArgumentException(String message) {
        super(message);
    }

}
