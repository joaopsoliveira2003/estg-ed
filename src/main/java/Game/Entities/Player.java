package Game.Entities;

import Collections.Lists.UnorderedListADT;
import Collections.Exceptions.IllegalArgumentException;
import Game.Exceptions.NoAssociationException;

/**
 * Player defines the interface for a player in the game.
 */
public interface Player extends Comparable<Player> {

    /**
     * Returns the id of the player.
     *
     * @return the id of the player
     */
    int getID();

    /**
     * Sets the id of the player.
     *
     * @param id the id of the player
     */
    void setID(int id) throws IllegalArgumentException;

    /**
     * Returns the name of the player.
     *
     * @return the name of the player
     */
    String getName();

    /**
     * Sets the name of the player.
     * 
     * @param name the name of the player
     */
    void setName(String name) throws IllegalArgumentException;

    /**
     * Returns the team of the player.
     *
     * @return the team of the player
     */
    Team getTeam();

    /**
     * Sets the team of the player.
     *
     * @param team the of the player
     */
    void setTeam(Team team) throws IllegalArgumentException;

    /**
     * Returns the Current Energy of the player.
     *
     * @return the Current Energy of the player
     */
    int getCurrentEnergy();

    /**
     * Sets the Current Energy of the player.
     *
     * @param energy the Current Energy of the player
     */
    void setCurrentEnergy(int energy) throws IllegalArgumentException;

    /**
     * Returns the level of the player.
     *
     * @return the level of the player
     */
    int getLevel();

    /**
     * Sets the level of the player.
     *
     * @param level the level of the player
     */
    void setLevel(int level) throws IllegalArgumentException;

    /**
     * Returns the experience of the player.
     *
     * @return the experience of the player
     */
    int getExperiencePoints();

    /**
     * Sets the experiencePoints of the player.
     *
     * @param experiencePoints the experience of the player
     */
    void setExperiencePoints(int experiencePoints) throws IllegalArgumentException;

    /**
     * Adds the specified experiencePoints to the player.
     *
     * @param experiencePoints the experiencePoints to add
     */
    void addExperiencePoints(int experiencePoints) throws IllegalArgumentException;

    /**
     * Returns the list of portals the player owns.
     *
     * @return the portals the player owns
     */
    UnorderedListADT<Portal> getPortals() throws NoAssociationException;

    /**
     * Generates a hash code for the player.
     *
     * @return a hash code for the player
     */
    @Override
    int hashCode();

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    boolean equals(Object obj);

    /**
     * Returns a string representation of the player.
     *
     * @return a string representation of the player
     */
    String toString();
}
