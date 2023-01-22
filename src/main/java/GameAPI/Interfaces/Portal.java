package GameAPI.Interfaces;


import GameAPI.Exceptions.InvalidArgumentException;

/**
 * Portal defines the interface for a portal in the game.
 */
public interface Portal extends Place {

    /**
     * Returns the maximum energy that can be stored in the portal.
     * 
     * @return the maximum energy that can be stored in the portal
     */
    int getMaxEnergy();
    
    /**
     * Sets the maximum energy that can be stored in the portal.
     * 
     * @param maxEnergy the maximum energy that can be stored in the portal
     */
    void setMaxEnergy(int maxEnergy) throws InvalidArgumentException;

    /**
     * Returns the owner of the portal.
     * 
     * @return the owner of the portal
     */
    Player getOwner();

    /**
     * Sets the owner of the portal.
     * 
     * @param owner the owner of the portal
     */
    void setOwner(Player owner) throws InvalidArgumentException;
    
}
