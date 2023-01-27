package Game.Entities;

import Collections.Exceptions.IllegalArgumentException;
import org.json.simple.JSONObject;

import java.io.IOException;

/**
 * Local defines the interface for a place in the game.
 */
public interface Local extends Comparable<Local> {

    /**
     * Returns the id of the place.
     *
     * @return the id of the place
     */
    int getID();

    /**
     * Sets the id of the place.
     *
     * @param id the id of the place
     */
    void setID(int id) throws IllegalArgumentException;

    /**
     * Returns the name of the place.
     *
     * @return the name of the place
     */
    String getName();

    /**
     * Sets the name of the place.
     *
     * @param name the name of the place
     */
    void setName(String name) throws IllegalArgumentException;

    /**
     * Returns the description of the place.
     *
     * @return the description of the place
     */
    double getLatitude();

    /**
     * Sets the latitude of the place.
     *
     * @param latitude the latitude of the place
     */
    void setLatitude(double latitude) throws IllegalArgumentException;

    /**
     * Returns the longitude of the place.
     *
     * @return the longitude of the place
     */
    double getLongitude();

    /**
     * Sets the longitude of the place.
     *
     * @param longitude the longitude of the place
     */
    void setLongitude(double longitude) throws IllegalArgumentException;

    /**
     * Returns the distance between the local and the specified local.
     *
     * @param local the local to compare
     * @return the distance between the local and the specified local
     */
    double getDistanceTo(Local local) throws IllegalArgumentException;

    /**
     * Returns the energy of the place.
     *
     * @return the energy of the place
     */
    int getEnergy();

    /**
     * Sets the energy of the place.
     *
     * @param energy the energy of the place
     */
    void setEnergy(int energy) throws IllegalArgumentException;

    void addEnergy(int energy) throws IllegalArgumentException;

    void removeEnergy(int energy) throws IllegalArgumentException;

    /**
     * Returns the json of the place.
     *
     * @return the json of the place
     */
    JSONObject getJSON();

    /**
     * Generates a hash code for the place.
     *
     * @return a hash code for the place
     */
    @Override
    int hashCode();

    /**
     * Compares the place to the specified object.
     *
     * @param obj the object to compare
     * @return true if the place is equal to the specified object, false otherwise
     */
    @Override
    boolean equals(Object obj);

    /**
     * Returns the description of the place.
     *
     * @return the description of the place
     */
    String toString();
    
}
