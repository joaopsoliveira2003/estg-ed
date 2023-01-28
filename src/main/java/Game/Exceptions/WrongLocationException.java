package Game.Exceptions;

/**
 * WrongLocationException is thrown when a player is not in the right location.
 */
public class WrongLocationException extends RuntimeException {

    /**
     * Constructs a WrongLocationException with no detail message.
     */
    public WrongLocationException() {
        super();
    }

    /**
     * Constructs a WrongLocationException with the specified detail message.
     *
     * @param message the detail message
     */
    public WrongLocationException(String message) {
        super(message);
    }

}
