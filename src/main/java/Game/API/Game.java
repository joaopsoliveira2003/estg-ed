package Game.API;

import Game.Entities.Place;
import Game.Exceptions.NoSuchPlaceException;
import Exceptions.IllegalArgumentException;

/**
 * Game defines the interface for the game.
 */
public interface Game {

    /**
     * Adds a place to the network.
     *
     * @param vertex the place to be added
     * @throws IllegalArgumentException if the place is null
     */
    void addPlace(Place vertex) throws IllegalArgumentException;

    /**
     * Updates a place in the network.
     *
     * @param oldVertex the place to be updated
     * @param newVertex the new place
     * @throws NoSuchPlaceException if the place does not exist
     * @throws IllegalArgumentException if the place is null
     */
    void updatePlace(Place oldVertex, Place newVertex) throws NoSuchPlaceException, IllegalArgumentException;

    /**
     * Removes a place from the network.
     *
     * @param vertex the place to be removed
     * @throws NoSuchPlaceException if the place does not exist
     * @throws IllegalArgumentException if the place is null
     */
    void removePlace(Place vertex) throws NoSuchPlaceException, IllegalArgumentException;

    /**
     * Adds a route between two places.
     *
     * @param vertex1 the first place
     * @param vertex2 the second place
     * @throws NoSuchPlaceException if one of the places does not exist
     * @throws IllegalArgumentException if one of the places is null
     */
    void addRoute(Place vertex1, Place vertex2) throws NoSuchPlaceException, IllegalArgumentException;

    /**
     * Removes a route between two places.
     *
     * @param vertex1 the first place
     * @param vertex2 the second place
     * @throws NoSuchPlaceException if one of the places does not exist
     * @throws IllegalArgumentException if one of the places is null
     */
    void removeRoute(Place vertex1, Place vertex2) throws NoSuchPlaceException, IllegalArgumentException;

}
