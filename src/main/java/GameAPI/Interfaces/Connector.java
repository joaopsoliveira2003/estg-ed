package GameAPI.Interfaces;

/**
 * Connector defines the interface for a connector in the game.
 */
public interface Connector extends Place {

    int getCoolDown();

    void setCoolDown(int coolDown);

}
