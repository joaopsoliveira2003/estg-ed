package Game.API;

import Collections.Exceptions.IllegalArgumentException;
import Collections.Lists.LinkedOrderedList;
import Game.Entities.Local;
import Game.Entities.Player;
import Game.Entities.Team;
import Game.Enumerations.LocalFilter;
import Game.Enumerations.PlayerFilter;
import Game.Exceptions.*;

import java.io.IOException;
import java.util.Iterator;

/**
 * Game defines the interface for the game.
 */
public interface Game {

    /**
     * Adds a local to the network.
     *
     * @param local the place to be added
     * @throws IllegalArgumentException if the local is null
     * @throws AlreadyExistsException   if the local already exists
     */
    void addLocal(Local local) throws IllegalArgumentException, AlreadyExistsException;

    /**
     * Updates a local in the network.
     *
     * @param oldLocal the local to be updated
     * @param newLocal the new local
     * @throws IllegalArgumentException if the local is null
     * @throws NoSuchLocalException     if the local does not exist
     */
    void updateLocal(Local oldLocal, Local newLocal) throws IllegalArgumentException, NoSuchLocalException;

    /**
     * Removes a local from the network.
     *
     * @param local the local to be removed
     * @throws IllegalArgumentException if the local is null
     * @throws NoSuchLocalException     if the local does not exist
     */
    void removeLocal(Local local) throws IllegalArgumentException, NoSuchLocalException;

    /**
     * Lists the locals in the network.
     *
     * @param filter the filter to be applied
     * @return the list of locals
     */
    LinkedOrderedList<Local> listPlacesOrdered(LocalFilter filter);

    /**
     * Adds a route between two locals.
     *
     * @param local1 the first local
     * @param local2 the second local
     * @throws IllegalArgumentException if one of the local is null
     * @throws NoSuchLocalException     if one of the local does not exist
     * @throws AlreadyExistsException   if the route already exists
     */
    void addRoute(Local local1, Local local2) throws IllegalArgumentException, NoSuchLocalException, AlreadyExistsException;

    /**
     * Removes a route between two locals.
     *
     * @param local1 the first local
     * @param local2 the second local
     * @throws IllegalArgumentException if one of the places is null
     * @throws NoSuchLocalException     if one of the places does not exist
     */
    void removeRoute(Local local1, Local local2) throws IllegalArgumentException, NoSuchLocalException;

    Iterator<Local> getShortestPath(Local local1, Local local2) throws IllegalArgumentException, NoSuchLocalException;

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
