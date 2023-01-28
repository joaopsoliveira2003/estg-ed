package Game.Entities;

import Collections.Exceptions.IllegalArgumentException;
import org.json.simple.JSONObject;

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
     * @throws IllegalArgumentException if the name is null.
     */
    void setName(String name) throws IllegalArgumentException;

    /**
     * Generates a JSON object representing the team.
     *
     * @return a JSON object representing the team
     */
    JSONObject getJSON();

    /**
     * Returns true if the team is equal to the specified object.
     *
     * @param obj the object to compare
     * @return true if the team is equal to the specified object
     */
    @Override
    boolean equals(Object obj);

    /**
     * Returns a string representation of the team.
     *
     * @return a string representation of the team
     */
    String toString();

}
