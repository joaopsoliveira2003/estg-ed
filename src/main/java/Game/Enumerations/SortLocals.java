package Game.Enumerations;

/**
 * SortLocals defines the possible ways to sort the locals.
 */
public enum SortLocals {
    ID, TYPE, LATITUDE, LONGITUDE, ENERGY;

    /**
     * Returns the string representation of the sort locals.
     *
     * @return the string representation of the sort locals
     */
    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
