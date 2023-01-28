package Game.Exceptions;

/**
 * AlreadyConqueredPortalException is thrown when a portal is already conquered by the same team.
 */
public class AlreadyConqueredPortalException extends RuntimeException {

        /**
        * Constructs a AlreadyConqueredPortalException with no detail message.
        */
        public AlreadyConqueredPortalException() {
            super();
        }

        /**
        * Constructs a AlreadyConqueredPortalException with the specified detail message.
        *
        * @param message the detail message
        */
        public AlreadyConqueredPortalException(String message) {
            super(message);
        }

}
