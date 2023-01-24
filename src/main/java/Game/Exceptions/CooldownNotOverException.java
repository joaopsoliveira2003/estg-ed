package Game.Exceptions;

/**
 * CooldownNotOverException is thrown when a cooldown is not over.
 */
public class CooldownNotOverException extends RuntimeException {

    /**
     * Constructs a CooldownNotOverException with no detail message.
     */
    public CooldownNotOverException() {
        super();
    }

    /**
     * Constructs a CooldownNotOverException with the specified detail message.
     *
     * @param message the detail message
     */
    public CooldownNotOverException(String message) {
        super(message);
    }

}
