package Game.Utilities;

import Collections.Exceptions.IllegalArgumentException;

/**
 * Validations contains methods to validate the parameters of the methods.
 */
public class Validations {
    /**
     * Validates if the value is negative.
     *
     * @param value the value to validate
     * @param name the name of the value
     * @throws IllegalArgumentException if the value is invalid
     */
    public static void validateInteger(int value, String name) throws IllegalArgumentException {
        if (value < 0) {
            throw new IllegalArgumentException(name + " cannot be negative.");
        }
    }

    /**
     * Validates if the value is null, empty or blank.
     *
     * @param value the value to validate
     * @param name the name of the value
     * @throws IllegalArgumentException if the value is invalid
     */
    public static void validateString(String value, String name) throws IllegalArgumentException {
        if (value == null || value.isEmpty() || value.isBlank()) {
            throw new IllegalArgumentException(name + " cannot be null, empty or blank.");
        }
    }
}
