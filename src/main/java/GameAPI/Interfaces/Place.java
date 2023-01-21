package GameAPI.Interfaces;

/**
 * Place defines the interface for a place in the game.
 */
public interface Place {

    /**
     * Generates a hash code for the place.
     * 
     * @return a hash code for the place
     */
    @Override
    int hashCode();

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
    void setID(int id);

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
    void setName(String name);

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
    void setLatitude(double latitude);

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
    void setLongitude(double longitude);

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
    void setEnergy(int energy);
    
    /**
     * Returns the description of the place.
     *
     * @return the description of the place
     */
    String toString();
    
}
