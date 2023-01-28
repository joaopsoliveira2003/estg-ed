package Game.Entities;

import Collections.Exceptions.IllegalArgumentException;
import Game.Exceptions.NoAssociationException;

/**
 * Portal defines the interface for a portal in the game.
 */
public interface Portal extends Local {

    /**
     * Sets the energy of the local.
     *
     * @param energy the energy of the local
     * @throws IllegalArgumentException if the energy is negative.
     */
    @Override
    void setEnergy(int energy) throws IllegalArgumentException;

    /**
     * Adds energy to the local.
     *
     * @param energy the energy to add
     * @throws IllegalArgumentException if the energy is negative.
     */
    @Override
    void addEnergy(int energy) throws IllegalArgumentException;

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
     * @throws IllegalArgumentException if the maximum energy is negative
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
     * @throws IllegalArgumentException if the owner is null
     */
    void setOwner(Player owner) throws IllegalArgumentException;

    /**
     * Removes the owner of the portal.
     *
     * @throws NoAssociationException if the portal has no owner
     */
    void removeOwner() throws NoAssociationException;
}
