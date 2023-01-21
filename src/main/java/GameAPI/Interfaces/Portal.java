package GameAPI.Interfaces;

/**
 * Portal defines the interface for a portal in the game.
 */
public interface Portal extends Place {
    
    int getMaxEnergy();

    void setMaxEnergy(int maxEnergy);

    Player getOwner();

    void setOwner(Player owner);
    
}
