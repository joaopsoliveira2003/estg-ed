package Game.Entities;

import Collections.Lists.UnorderedListADT;
import Collections.Exceptions.IllegalArgumentException;
import Game.Exceptions.NoAssociationException;
import org.json.simple.JSONObject;

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
     * @throws IllegalArgumentException if the id is negative.
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
     * @throws IllegalArgumentException if the name is null.
     */
    void setName(String name) throws IllegalArgumentException;

    /**
     * Returns the team of the player.
     *
     * @return the team of the player
     * @throws NoAssociationException if the player is not associated with a team.
     */
    Team getTeam() throws NoAssociationException;

    /**
     * Sets the team of the player.
     *
     * @param team the of the player
     * @throws IllegalArgumentException if the team is null.
     */
    void setTeam(Team team) throws IllegalArgumentException;

    /**
     * Removes the team of the player.
     *
     * @throws NoAssociationException if the player is not associated with a team.
     */
    void removeTeam() throws NoAssociationException;

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
     * @throws IllegalArgumentException if the energy is negative.
     */
    void setCurrentEnergy(int energy) throws IllegalArgumentException;

    /**
     * Adds the specified energy to the player.
     *
     * @param energy the energy to add
     * @throws IllegalArgumentException if the energy is negative.
     */
    void addEnergy(int energy) throws IllegalArgumentException;

    /**
     * Removes the specified energy from the player.
     *
     * @param energy the energy to remove
     * @throws IllegalArgumentException if the energy is negative.
     */
    void removeEnergy(int energy) throws IllegalArgumentException;

    /**
     * Returns the max energy of the player.
     *
     * @return the max energy of the player
     */
    int getMaxEnergy();

    /**
     * Sets the max energy of the player.
     *
     * @param maxEnergy the max energy of the player
     * @throws IllegalArgumentException if the maxEnergy is negative.
     */
    void setMaxEnergy(int maxEnergy) throws IllegalArgumentException;

    /**
     * Returns the level of the player.
     *
     * @return the level of the player
     */
    int getLevel();

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
     * @throws IllegalArgumentException if the experiencePoints is negative.
     */
    void setExperiencePoints(int experiencePoints) throws IllegalArgumentException;

    /**
     * Adds the specified experiencePoints to the player.
     *
     * @param experiencePoints the experiencePoints to add
     * @throws IllegalArgumentException if the experiencePoints is negative.
     */
    void addExperiencePoints(int experiencePoints) throws IllegalArgumentException;

    /**
     * Returns the list of portals the player owns.
     *
     * @return the portals the player owns
     * @throws NoAssociationException if the player is not associated with a team.
     */
    UnorderedListADT<Portal> getPortals() throws NoAssociationException;

    /**
     * Generates a JSON object for the player.
     *
     * @return a JSON object for the player
     */
    JSONObject getJSON();

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
