package Game.Enumerations;

public enum SortPlayers {
    ID, TEAM, LEVEL, PORTALS;

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
