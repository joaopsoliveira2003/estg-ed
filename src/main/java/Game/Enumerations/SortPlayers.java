package Game.Enumerations;

/**
 * SortPlayers defines the possible ways to sort the players.
 */
public enum SortPlayers {
    ID, TEAM, LEVEL, PORTALS;

    /**
     * Returns the string representation of the sort players.
     *
     * @return the string representation of the sort players
     */
    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
