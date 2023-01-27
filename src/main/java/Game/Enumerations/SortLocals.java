package Game.Enumerations;

public enum SortLocals {
    ID, TYPE, LATITUDE, LONGITUDE, ENERGY;

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
