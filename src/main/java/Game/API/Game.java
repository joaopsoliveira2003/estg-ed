package Game.API;

import Game.Entities.Place;
import Game.Entities.Player;
import Game.Entities.Team;
import Game.Exceptions.NoSuchPlaceException;
import Collections.Exceptions.IllegalArgumentException;
import Game.Exceptions.NoSuchPlayerException;
import Game.Exceptions.NoSuchTeamException;

import java.util.Iterator;

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

    void addPlayer(Player player) throws IllegalArgumentException;

    void updatePlayer(Player oldPlayer, Player newPlayer) throws NoSuchPlayerException, IllegalArgumentException;

    void removePlayer(Player player) throws NoSuchPlayerException, IllegalArgumentException;

    void movePlayer(Player player, Place place) throws NoSuchPlayerException, NoSuchPlaceException, IllegalArgumentException;

    void addTeam(Team team) throws IllegalArgumentException;

    void addPlayerToTeam(Player player, Team team) throws NoSuchPlayerException, NoSuchTeamException, IllegalArgumentException;

    void removePlayerFromTeam(Player player, Team team) throws NoSuchPlayerException, NoSuchTeamException, IllegalArgumentException;



    Iterator<Place> getShortestPath(Place start, Place end) throws NoSuchPlaceException, IllegalArgumentException;

    Iterator<Place> getShortestPathPortals(Player player, Place end, boolean portals) throws NoSuchPlaceException, IllegalArgumentException;



}
