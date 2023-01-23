package Game.Entities;

import Collections.Exceptions.IllegalArgumentException;
import Game.Exceptions.NoAssociationException;

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
    void setMaxEnergy(int maxEnergy) throws IllegalArgumentException;

    /**
     * Returns the owner of the portal.
     * 
     * @return the owner of the portal
     * @throws NoAssociationException if the portal has no owner
     */
    Player getOwner() throws NoAssociationException;

    /**
     * Sets the owner of the portal.
     * 
     * @param owner the owner of the portal
     */
    void setOwner(Player owner) throws IllegalArgumentException;
    
}
