package GameAPI.Interfaces;

/**
 * Player defines the interface for a player in the game.
 */
public interface Player {

    /**
     * Generates a hash code for the player.
     *
     * @return a hash code for the player
     */
    @Override
    int hashCode();

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
    void setID(int id);

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
    void setName(String name);

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
    void setTeam(Team team);

    /**
     * Returns the CurrentEnergy of the player.
     *
     * @return the CurrentEnergy of the player
     */
    int getCurrentEnergy();

    /**
     * Sets the CurrentEnergy of the player.
     *
     * @param energy the CurrentEnergy of the player
     */
    void setCurrentEnergy(int energy);

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
    void setLevel(int level);

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
    void setExperiencePoints(int experiencePoints);

    /**
     * Returns a string representation of the player.
     *
     * @return a string representation of the player
     */
    String toString();
}
