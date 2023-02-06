package Game.API;

import Collections.Exceptions.IllegalArgumentException;
import Game.Entities.Local;
import Game.Entities.Player;
import Game.Entities.Portal;
import Game.Enumerations.SortLocals;
import Game.Enumerations.SortPlayers;
import Game.Exceptions.*;

import java.io.IOException;
import java.util.Iterator;

/**
 * Game defines the interface for the game.
 */
public interface Game {

    // LOCALS MANAGEMENT

    /**
     * Adds a portal to the game.
     *
     * @param id The id of the portal.
     * @param name The name of the portal.
     * @param latitude The latitude of the portal.
     * @param longitude The longitude of the portal.
     * @param energy The energy of the portal.
     * @param maxEnergy The maximum energy of the portal.
     * @throws IllegalArgumentException If any of the parameters is invalid.
     * @throws AlreadyExistsException If the portal already exists.
     */
    void addPortal(int id, String name, double latitude, double longitude, int energy, int maxEnergy) throws IllegalArgumentException, AlreadyExistsException;

    /**
     * Adds a connector to the game.
     *
     * @param id The id of the connector.
     * @param name The name of the connector.
     * @param latitude The latitude of the connector.
     * @param longitude The longitude of the connector.
     * @param energy The energy of the connector.
     * @param cooldown The cooldown of the connector.
     * @throws IllegalArgumentException If any of the parameters is invalid.
     * @throws AlreadyExistsException If the connector already exists.
     */
    void addConnector(int id, String name, double latitude, double longitude, int energy, int cooldown) throws IllegalArgumentException, AlreadyExistsException;

    /**
     * Updates a portal.
     *
     * @param id The id of the portal.
     * @param name The name of the portal.
     * @param latitude The latitude of the portal.
     * @param longitude The longitude of the portal.
     * @param energy The energy of the portal.
     * @param maxEnergy The maximum energy of the portal.
     * @throws IllegalArgumentException If any of the parameters is invalid.
     * @throws NoSuchLocalException If the portal does not exist.
     */
    void updatePortal(int id, String name, double latitude, double longitude, int energy, int maxEnergy) throws IllegalArgumentException, NoSuchLocalException;

    /**
     * Updates a connector.
     *
     * @param id The id of the connector.
     * @param name The name of the connector.
     * @param latitude The latitude of the connector.
     * @param longitude The longitude of the connector.
     * @param energy The energy of the connector.
     * @param cooldown The cooldown of the connector.
     * @throws IllegalArgumentException If any of the parameters is invalid.
     * @throws NoSuchLocalException If the connector does not exist.
     */
    void updateConnector(int id, String name, double latitude, double longitude, int energy, int cooldown) throws IllegalArgumentException, NoSuchLocalException;

    /**
     * Removes a portal from the game.
     *
     * @param id The id of the portal.
     * @throws IllegalArgumentException If the id is invalid.
     * @throws NoSuchLocalException If the portal does not exist.
     */
    void removePortal(int id) throws IllegalArgumentException, NoSuchLocalException;

    /**
     * Removes a connector from the game.
     *
     * @param id The id of the connector.
     * @throws IllegalArgumentException If the id is invalid.
     * @throws NoSuchLocalException If the connector does not exist.
     */
    void removeConnector(int id) throws IllegalArgumentException, NoSuchLocalException;

    /**
     * Lists the portals ordered by a given filter.
     *
     * @param filter The filter to use.
     * @return An iterator with the portals ordered by the given filter.
     */
    Iterator<Local> listLocalsOrdered(SortLocals filter);

    /**
     * Loads the locals from a file.
     *
     * @param fileName The name of the file.
     * @throws IllegalArgumentException If the file name is invalid.
     * @throws IOException If there is an error reading the file.
     */
    void loadLocals(String fileName) throws IllegalArgumentException, IOException;

    /**
     * Exports the locals to a file.
     *
     * @param fileName The name of the file.
     * @throws IllegalArgumentException If the file name is invalid.
     * @throws IOException If there is an error writing the file.
     */
    void exportLocals(String fileName) throws IllegalArgumentException, IOException;

    // ROUTES MANAGEMENT

    /**
     * Adds a route to the game.
     *
     * @param id1 The id of the first local.
     * @param id2 The id of the second local.
     * @throws IllegalArgumentException If any of the ids is invalid.
     * @throws NoSuchLocalException If any of the locals does not exist.
     */
    void addRoute(int id1, int id2) throws IllegalArgumentException, NoSuchLocalException;

    /**
     * Removes a route from the game.
     *
     * @param id1 The id of the first local.
     * @param id2 The id of the second local.
     * @throws IllegalArgumentException If any of the ids is invalid.
     * @throws NoSuchLocalException If any of the locals does not exist.
     */
    void removeRoute(int id1, int id2) throws IllegalArgumentException, NoSuchLocalException;

    /**
     * Loads all the routes and locals from a file.
     *
     * @param fileName The name of the file.
     * @throws IllegalArgumentException If the file name is invalid.
     * @throws IOException If there is an error reading the file.
     */
    void loadRoutesLocals(String fileName) throws IllegalArgumentException, IOException;

    /**
     * Exports all the routes and locals to a file.
     *
     * @param fileName The name of the file.
     * @throws IllegalArgumentException If the file name is invalid.
     * @throws IOException If there is an error writing the file.
     */
    void exportRoutesLocals(String fileName) throws IllegalArgumentException, IOException;

    // PLAYERS MANAGEMENT

    /**
     * Adds a player to the game.
     *
     * @param id The id of the player.
     * @param name The name of the player.
     * @param team The team of the player.
     * @throws IllegalArgumentException If any of the parameters is invalid.
     * @throws AlreadyExistsException If the player already exists.
     * @throws NoSuchTeamException If the team does not exist.
     */
    void addPlayer(int id, String name, String team) throws IllegalArgumentException, AlreadyExistsException, NoSuchTeamException;

    /**
     * Updates a player.
     *
     * @param id The id of the player.
     * @param name The name of the player.
     * @param team The team of the player.
     * @throws IllegalArgumentException If any of the parameters is invalid.
     * @throws NoSuchPlayerException If the player does not exist.
     * @throws NoSuchTeamException If the team does not exist.
     */
    void updatePlayer(int id, String name, String team) throws IllegalArgumentException, NoSuchPlayerException, NoSuchTeamException;

    /**
     * Removes a player from the game.
     *
     * @param id The id of the player.
     * @throws IllegalArgumentException If the id is invalid.
     * @throws NoSuchPlayerException If the player does not exist.
     */
    void removePlayer(int id) throws IllegalArgumentException, NoSuchPlayerException;

    /**
     * Moves a player to a local.
     *
     * @param player The id of the player.
     * @param local The id of the local.
     * @throws IllegalArgumentException If any of the ids is invalid.
     * @throws NoSuchPlayerException If the player does not exist.
     * @throws NoSuchLocalException If the local does not exist.
     */
    void movePlayer(int player, int local) throws IllegalArgumentException, NoSuchPlayerException, NoSuchLocalException;

    /**
     * Adds a team to the game.
     *
     * @param name The name of the team.
     * @throws IllegalArgumentException If the name is invalid.
     * @throws AlreadyExistsException If the team already exists.
     */
    void addTeam(String name) throws IllegalArgumentException, AlreadyExistsException;

    /**
     * Adds a player to a team.
     *
     * @param player The id of the player.
     * @param team The name of the team.
     * @throws IllegalArgumentException If any of the parameters is invalid.
     * @throws NoSuchPlayerException If the player does not exist.
     * @throws NoSuchTeamException If the team does not exist.
     */
    void addPlayerToTeam(int player, String team) throws IllegalArgumentException, NoSuchPlayerException, NoSuchTeamException;

    /**
     * Removes a player from a team.
     *
     * @param player The id of the player.
     * @throws IllegalArgumentException If any of the parameters is invalid.
     * @throws NoSuchPlayerException If the player does not exist.
     * @throws NoSuchTeamException If the team does not exist.
     */
    void removePlayerFromTeam(int player) throws IllegalArgumentException, NoSuchPlayerException, NoSuchTeamException;

    /**
     * Lists all the players ordered by the given filter.
     *
     * @param filter The filter to use.
     * @return An iterator with the players.
     */
    Iterator<Player> listPlayersOrdered(SortPlayers filter);

    /**
     * Loads all the players and teams from a file.
     *
     * @param fileName The name of the file.
     * @throws IllegalArgumentException If the file name is invalid.
     * @throws IOException If there is an error reading the file.
     */
    void loadPlayersTeams(String fileName) throws IllegalArgumentException, IOException;

    /**
     * Exports all the players and teams to a file.
     *
     * @param fileName The name of the file.
     * @throws IllegalArgumentException If the file name is invalid.
     * @throws IOException If there is an error writing the file.
     */
    void exportPlayersTeams(String fileName) throws IllegalArgumentException, IOException;

    // GAME MANAGEMENT

    /**
     * Charges a player energy.
     *
     * @param player The id of the player.
     * @param local The id of the local.
     * @throws IllegalArgumentException If any of the ids is invalid.
     * @throws NoSuchPlayerException If the player does not exist.
     * @throws NoSuchLocalException If the local does not exist.
     * @throws InvalidLocalException If the local is not a charging station.
     * @throws WrongLocationException If the player is not in the local.
     * @throws CooldownNotOverException If the cooldown is not over.
     */
    void chargePlayer(int player, int local) throws IllegalArgumentException, NoSuchPlayerException, NoSuchLocalException, InvalidLocalException, WrongLocationException, CooldownNotOverException;

    /**
     * Attacks a Portal.
     *
     * @param player The id of the player.
     * @param local The id of the local.
     * @throws IllegalArgumentException If any of the ids is invalid.
     * @throws NoSuchPlayerException If the player does not exist.
     * @throws NoSuchLocalException If the local does not exist.
     * @throws InvalidLocalException If the local is not a portal.
     * @throws WrongLocationException If the player is not in the local.
     * @throws NoTeamException If the player does not have a team.
     * @throws NotEnoughEnergyException If the player does not have enough energy.
     * @throws AlreadyConqueredPortalException If the portal is already conquered by the team.
     */
    void acquirePortal(int player, int local) throws IllegalArgumentException, NoSuchPlayerException, NoSuchLocalException, InvalidLocalException, NoTeamException, WrongLocationException, NotEnoughEnergyException, AlreadyConqueredPortalException;

    /**
     * Charges a Portal of the same team.
     *
     * @param player The id of the player.
     * @param local The id of the local.
     * @param energy The amount of energy to charge.
     * @throws IllegalArgumentException If any of the ids is invalid.
     * @throws NoSuchPlayerException If the player does not exist.
     * @throws NoSuchLocalException If the local does not exist.
     * @throws InvalidLocalException If the local is not a portal.
     * @throws WrongLocationException If the player is not in the local.
     * @throws NoTeamException If the player does not have a team.
     * @throws NotEnoughEnergyException If the player does not have enough energy.
     * @throws NotConqueredPortalException If the portal is not conquered by the team.
     */
    void chargePortal(int player, int local, int energy) throws IllegalArgumentException, NoSuchPlayerException, NoSuchLocalException, InvalidLocalException, WrongLocationException, NoTeamException, NotEnoughEnergyException, NotConqueredPortalException;

    /**
     * Gets the shortest path between two locals.
     *
     * @param local1 The id of the first local.
     * @param local2 The id of the second local.
     * @return An iterator with the path.
     * @throws IllegalArgumentException If any of the ids is invalid.
     * @throws NoSuchLocalException If any of the locals does not exist.
     */
    Iterator<Local> getShortestPath(int local1, int local2) throws IllegalArgumentException, NoSuchLocalException;

    /**
     * Gets the shortest path between two locals only using portals or connectors.
     *
     * @param local1 The id of the first local.
     * @param local2 The id of the second local.
     * @param portals True if only portals are allowed, false if only connectors are allowed.
     * @return An iterator with the path.
     * @throws IllegalArgumentException If any of the ids is invalid.
     * @throws NoSuchLocalException If any of the locals does not exist.
     */
    Iterator<Local> getShortestPath(int local1, int local2, boolean portals) throws IllegalArgumentException, NoSuchLocalException;

    /**
     * Exports the shortest path to a file.
     *
     * @param path The path to export.
     * @param fileName The name of the file.
     * @throws IllegalArgumentException If the file name is invalid.
     * @throws IOException If there is an error writing the file.
     */
    void exportShortestPath(Iterator<Local> path, String fileName) throws IllegalArgumentException, IOException;

    /**
     * Loads the game data from a file.
     *
     * @param fileName The name of the file.
     * @throws IllegalArgumentException If the file name is invalid.
     * @throws IOException If there is an error reading the file.
     */
    void loadGameData(String fileName) throws IllegalArgumentException, IOException;

    /**
     * Exports the game data to a file.
     *
     * @param fileName The name of the file.
     * @throws IllegalArgumentException If the file name is invalid.
     * @throws IOException If there is an error writing the file.
     */
    void exportGameData(String fileName) throws IllegalArgumentException, IOException;

    // PLAYER ONLY NEEDED FUNCTIONS

    /**
     * Gets the player's position.
     *
     * @param player The id of the player.
     * @return The position of the player.
     * @throws IllegalArgumentException If the id is invalid.
     * @throws NoSuchPlayerException If the player does not exist.
     */
    int getPlayerPosition(int player) throws IllegalArgumentException, NoSuchPlayerException;

    /**
     * Gets the player's energy.
     *
     * @param player The id of the player.
     * @return The energy of the player.
     * @throws IllegalArgumentException If the id is invalid.
     * @throws NoSuchPlayerException If the player does not exist.
     */
    int getPlayerEnergy(int player) throws IllegalArgumentException, NoSuchPlayerException;

    /**
     * Gets the player's team.
     *
     * @param player The id of the player.
     * @return The team of the player.
     * @throws IllegalArgumentException If the id is invalid.
     * @throws NoSuchPlayerException If the player does not exist.
     * @throws NoTeamException If the player does not have a team.
     */
    String getPlayerTeam(int player) throws IllegalArgumentException, NoSuchPlayerException, NoSuchTeamException;

    /**
     * Gets the player's experience points.
     *
     * @param player The id of the player.
     * @return The experience points of the player.
     * @throws IllegalArgumentException If the id is invalid.
     * @throws NoSuchPlayerException If the player does not exist.
     */
    int getPlayerExperiencePoints(int player) throws IllegalArgumentException, NoSuchPlayerException;

    /**
     * Gets the player's level.
     *
     * @param player The id of the player.
     * @return The level of the player.
     * @throws IllegalArgumentException If the id is invalid.
     * @throws NoSuchPlayerException If the player does not exist.
     */
    int getPlayerLevel(int player) throws IllegalArgumentException, NoSuchPlayerException;

    /**
     * Gets the portals conquered by the player.
     *
     * @param player The id of the player.
     * @return An iterator with the portals.
     * @throws IllegalArgumentException If the id is invalid.
     * @throws NoSuchPlayerException If the player does not exist.
     */
    Iterator<Portal> getConqueredPortals(int player) throws IllegalArgumentException, NoSuchPlayerException;

    /**
     * Gets the locals in range of the player.
     *
     * @param local The id of the local.
     * @return An iterator with the locals.
     * @throws IllegalArgumentException If the id is invalid.
     * @throws NoSuchLocalException If the local does not exist.
     */
    Iterator<Local> getLocalsInRange(int local) throws IllegalArgumentException, NoSuchLocalException;
}
