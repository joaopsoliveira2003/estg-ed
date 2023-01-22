package GameAPI.Interfaces;

import GameAPI.Exceptions.InvalidArgumentException;

/**
 * Place defines the interface for a place in the game.
 */
public interface Place extends Comparable<Place> {

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
    void setID(int id) throws InvalidArgumentException;

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
    void setName(String name) throws InvalidArgumentException;

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
    void setLatitude(double latitude) throws InvalidArgumentException;

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
    void setLongitude(double longitude) throws InvalidArgumentException;

    /**
     * Returns the distance between the place and the specified place.
     *
     * @param place the place to compare
     * @return the distance between the place and the specified place
     */
    double getDistanceTo(Place place) throws InvalidArgumentException;

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
    void setEnergy(int energy) throws InvalidArgumentException;

    /**
     * Generates a hash code for the place.
     *
     * @return a hash code for the place
     */
    @Override
    int hashCode();

    @Override
    boolean equals(Object obj);

    /**
     * Returns the description of the place.
     *
     * @return the description of the place
     */
    String toString();
    
}
