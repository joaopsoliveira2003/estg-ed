package GameAPI.Interfaces;

import GameAPI.Exceptions.InvalidArgumentException;

/**
 * Connector defines the interface for a connector in the game.
 */
public interface Connector extends Place {

    int getCoolDownTime();

    void setCoolDownTime(int coolDownTime) throws InvalidArgumentException;

    void addLastInteraction(Player player) throws InvalidArgumentException;

    boolean isCoolDownOver(Player player) throws InvalidArgumentException;

}
