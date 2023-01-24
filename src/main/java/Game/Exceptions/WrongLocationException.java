package Game.Exceptions;

public class WrongLocationException extends RuntimeException {

        public WrongLocationException() {
            super();
        }

        public WrongLocationException(String message) {
            super(message);
        }

}
