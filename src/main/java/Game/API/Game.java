package Game.API;

import Collections.Lists.LinkedOrderedList;
import Game.Entities.Connector;
import Game.Entities.Local;
import Game.Entities.Player;
import Game.Entities.Team;
import Game.Enumerations.LocalFilter;
import Game.Enumerations.PlayerFilter;
import Game.Exceptions.*;
import Collections.Exceptions.IllegalArgumentException;

import java.io.IOException;
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
    void addPlace(Local vertex) throws IllegalArgumentException;

    /**
     * Updates a place in the network.
     *
     * @param oldVertex the place to be updated
     * @param newVertex the new place
     * @throws NoSuchLocalException     if the place does not exist
     * @throws IllegalArgumentException if the place is null
     */
    void updatePlace(Local oldVertex, Local newVertex) throws NoSuchLocalException, IllegalArgumentException;

    /**
     * Removes a place from the network.
     *
     * @param vertex the place to be removed
     * @throws NoSuchLocalException     if the place does not exist
     * @throws IllegalArgumentException if the place is null
     */
    void removePlace(Local vertex) throws NoSuchLocalException, IllegalArgumentException;

    LinkedOrderedList<Local> listPlacesOrdered(LocalFilter filter);


    /**
     * Adds a route between two places.
     *
     * @param vertex1 the first place
     * @param vertex2 the second place
     * @throws NoSuchLocalException     if one of the places does not exist
     * @throws IllegalArgumentException if one of the places is null
     */
    void addRoute(Local vertex1, Local vertex2) throws NoSuchLocalException, IllegalArgumentException;

    /**
     * Removes a route between two places.
     *
     * @param vertex1 the first place
     * @param vertex2 the second place
     * @throws NoSuchLocalException     if one of the places does not exist
     * @throws IllegalArgumentException if one of the places is null
     */
    void removeRoute(Local vertex1, Local vertex2) throws NoSuchLocalException, IllegalArgumentException;

    Iterator<Local> getShortestPath(Local vertex1, Local vertex2) throws NoSuchLocalException, IllegalArgumentException;

    Iterator<Local> getShortestPath(Local vertex1, Local vertex2, boolean portals) throws NoSuchLocalException, IllegalArgumentException;

    void exportShortestPath(Iterator<Local> path, String fileName) throws IOException, IllegalArgumentException;

    void addPlayer(Player player) throws IllegalArgumentException, AlreadyExistsException;

    void updatePlayer(Player oldPlayer, Player newPlayer) throws NoSuchPlayerException, IllegalArgumentException;

    void removePlayer(Player player) throws NoSuchPlayerException, IllegalArgumentException;

    void movePlayer(Player player, Local local) throws NoSuchPlayerException, NoSuchLocalException, IllegalArgumentException;

    void addTeam(Team team) throws IllegalArgumentException, AlreadyExistsException;

    void addPlayerToTeam(Player player, Team team) throws NoSuchPlayerException, NoSuchTeamException, IllegalArgumentException;

    void removePlayerFromTeam(Player player, Team team) throws NoSuchPlayerException, NoSuchTeamException, IllegalArgumentException;

    LinkedOrderedList<Player> listPlayersOrdered(PlayerFilter filter);

    void chargePlayer(Player player, Local local) throws NoSuchPlayerException, NoSuchLocalException, CooldownNotOverException, IllegalArgumentException;

    void acquirePortal(Player player, Local local) throws NoSuchPlayerException, NoSuchLocalException, NoTeamException, NotEnoughEnergyException, AlreadyConqueredPortalException, IllegalArgumentException;

    void loadGameData(String fileName) throws IOException, IllegalArgumentException;

    void saveGameData(String fileName) throws IOException, IllegalArgumentException;
}
