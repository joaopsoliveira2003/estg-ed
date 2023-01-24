package Game.Exceptions;

/**
 * NotEnoughEnergyException is thrown when an entity doesn't have enough energy.
 */
public class NotEnoughEnergyException extends RuntimeException {

    /**
     * Constructs a NotEnoughEnergyException exception with no detail message.
     */
    public NotEnoughEnergyException() {
        super();
    }

    /**
     * Constructs a NotEnoughEnergyException exception with the specified detail message.
     *
     * @param message the detail message
     */
    public NotEnoughEnergyException(String message) {
        super(message);
    }

}
