package Game.Exceptions;

public class NoTeamException extends RuntimeException {

    /**
     * Constructs a NoTeamException with no detail message.
     */
    public NoTeamException() {
        super();
    }

    /**
     * Constructs a NoTeamException with the specified detail message.
     *
     * @param message the detail message
     */
    public NoTeamException(String message) {
        super(message);
    }

}
