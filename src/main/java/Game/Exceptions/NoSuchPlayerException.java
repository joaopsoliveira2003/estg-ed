package Game.Exceptions;

/**
 * NoSuchPlayerException is thrown when a player is not found.
 */
public class NoSuchPlayerException extends RuntimeException {

    /**
     * Constructs a NoSuchPlayerException with no detail message.
     */
    public NoSuchPlayerException() {
        super();
    }

    /**
     * Constructs a NoSuchPlayerException with the specified detail message.
     *
     * @param message the detail message
     */
    public NoSuchPlayerException(String message) {
        super(message);
    }

}
