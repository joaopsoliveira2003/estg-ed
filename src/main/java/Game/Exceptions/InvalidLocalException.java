package Game.Exceptions;

/**
 * InvalidLocalException is thrown when a local is invalid for a specific action.
 */
public class InvalidLocalException extends RuntimeException {

        /**
        * Constructs an InvalidLocalException with no detail message.
        */
        public InvalidLocalException() {
            super();
        }

        /**
        * Constructs an InvalidLocalException with the specified detail message.
        *
        * @param message the detail message
        */
        public InvalidLocalException(String message) {
            super(message);
        }

}
