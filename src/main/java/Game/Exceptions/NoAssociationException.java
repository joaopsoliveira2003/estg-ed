package Game.Exceptions;

/**
 * NoAssociationException is thrown when an association between two objects is not found.
 */
public class NoAssociationException extends RuntimeException {

    /**
     * Creates a new instance of AssociationException without detail message.
     */
    public NoAssociationException() {
    }

    /**
     * Constructs an instance of AssociationException with the specified detail message.
     * @param message the detail message.
     */
    public NoAssociationException(String message) {
        super(message);
    }

}
