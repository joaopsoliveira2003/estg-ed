package GameAPI.Interfaces;

/**
 * Team defines a team of players.
 */
public interface Team {
    
     /**
     * Returns the name of the team.

     * @return the name of the team
     */
    String getName();

    /**
     * Sets the name of the team.
     *
     * @param name the name of the team
     */
    void setName(String name);

    /**
     * Returns a string representation of the team.
     *
     * @return a string representation of the team
     */
    String toString();

}
