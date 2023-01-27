package Game.Entities;

import Collections.Exceptions.IllegalArgumentException;
import org.json.simple.JSONObject;

/**
 * Local defines the interface for a local in the game.
 */
public interface Local extends Comparable<Local> {

    /**
     * Returns the id of the local.
     *
     * @return the id of the local
     */
    int getID();

    /**
     * Sets the id of the local.
     *
     * @param id the id of the local
     */
    void setID(int id) throws IllegalArgumentException;

    /**
     * Returns the name of the local.
     *
     * @return the name of the local
     */
    String getName();

    /**
     * Sets the name of the local.
     *
     * @param name the name of the local
     */
    void setName(String name) throws IllegalArgumentException;

    /**
     * Returns the description of the local.
     *
     * @return the description of the local
     */
    double getLatitude();

    /**
     * Sets the latitude of the local.
     *
     * @param latitude the latitude of the local
     */
    void setLatitude(double latitude) throws IllegalArgumentException;

    /**
     * Returns the longitude of the local.
     *
     * @return the longitude of the local
     */
    double getLongitude();

    /**
     * Sets the longitude of the local.
     *
     * @param longitude the longitude of the local
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
     * Returns the energy of the local.
     *
     * @return the energy of the local
     */
    int getEnergy();

    /**
     * Sets the energy of the local.
     *
     * @param energy the energy of the local
     */
    void setEnergy(int energy) throws IllegalArgumentException;

    /**
     * Adds energy to the local.
     *
     * @param energy the energy to add
     */
    void addEnergy(int energy) throws IllegalArgumentException;

    /**
     * Removes energy from the local.
     *
     * @param energy the energy to remove
     */
    void removeEnergy(int energy) throws IllegalArgumentException;

    /**
     * Returns the json of the local.
     *
     * @return the json of the local
     */
    JSONObject getJSON();

    /**
     * Generates a hash code for the local.
     *
     * @return a hash code for the local
     */
    @Override
    int hashCode();

    /**
     * Compares the local to the specified object.
     *
     * @param obj the object to compare
     * @return true if the local is equal to the specified object, false otherwise
     */
    @Override
    boolean equals(Object obj);

    /**
     * Returns the description of the local.
     *
     * @return the description of the local
     */
    String toString();
}
