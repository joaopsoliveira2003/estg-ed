package Game.Utilities;

import Collections.Exceptions.IllegalArgumentException;

public class Validations {

    public static void validateInteger(int value, String name) throws IllegalArgumentException {
        if (value < 0) {
            throw new IllegalArgumentException(name + " cannot be negative.");
        }
    }

    public static void validateString(String value, String name) throws IllegalArgumentException {
        if (value == null || value.isEmpty() || value.isBlank()) {
            throw new IllegalArgumentException(name + " cannot be null, empty or blank.");
        }
    }
}
