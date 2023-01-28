package Game.Exceptions;

/**
 * NotConqueredPortalException is thrown when a portal is not conquered.
 */
public class NotConqueredPortalException extends RuntimeException {

    /**
     * Constructs a NotConqueredPortalException with no detail message.
     */
    public NotConqueredPortalException() {
        super();
    }

    /**
     * Constructs a NotConqueredPortalException with the specified detail message.
     *
     * @param message the detail message
     */
    public NotConqueredPortalException(String message) {
        super(message);
    }

}
