package Game.Exceptions;

/**
 * AssociationException is thrown when an association between two objects already exists.
 */
public class AlreadyAssociatedException extends RuntimeException {

    /**
     * Creates a new instance of AssociationException without detail message.
     */
    public AlreadyAssociatedException() {
    }

    /**
     * Constructs an instance of AssociationException with the specified detail message.
     * @param message the detail message.
     */
    public AlreadyAssociatedException(String message) {
        super(message);
    }

}
