package Game.Entities;

import Collections.Exceptions.IllegalArgumentException;

/**
 * Connector defines the interface for a connector in the game.
 */
public interface Connector extends Local {

    /**
     * Returns the cooldown time of the connector that is set for a player when he interacts with.
     *
     * @return the cooldown time of the connector that is set for a player when he interacts with.
     */
    int getCoolDownTime();

    /**
     * Sets the cooldown time of the connector that is set for a player when he interacts with.
     *
     * @param coolDownTime the cooldown time of the connector that is set for a player when he interacts with.
     * @throws IllegalArgumentException if the cooldown time is negative.
     */
    void setCoolDownTime(int coolDownTime) throws IllegalArgumentException;

    /**
     * Adds the last interaction of the player with the connector.
     *
     * @param player the player that interacts with the connector.
     * @throws IllegalArgumentException if the player is not valid.
     */
    void addLastInteraction(Player player) throws IllegalArgumentException;

    /**
     * Returns true if the cooldown time of the connector is over for the player.
     *
     * @param player the player that interacts with the connector.
     * @return true if the cooldown time of the connector is over for the player.
     * @throws IllegalArgumentException if the player is not valid.
     */
    boolean isCoolDownOver(Player player) throws IllegalArgumentException;

    /**
     * Returns the description of the place.
     *
     * @return the description of the place
     */
    String toString();
}
