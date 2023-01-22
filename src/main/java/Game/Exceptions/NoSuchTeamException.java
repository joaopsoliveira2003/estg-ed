package Game.Exceptions;

/**
 * NoSuchTeamException is thrown when a team is not found.
 */
public class NoSuchTeamException extends RuntimeException {

    /**
     * Constructs a NoSuchTeamException with no detail message.
     */
    public NoSuchTeamException() {
        super();
    }

    /**
     * Constructs a NoSuchTeamException with the specified detail message.
     *
     * @param message the detail message
     */
    public NoSuchTeamException(String message) {
        super(message);
    }

}
