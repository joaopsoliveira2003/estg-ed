package Game.API;

import Collections.Exceptions.IllegalArgumentException;
import Collections.Lists.LinkedOrderedList;
import Collections.Lists.OrderedListADT;
import Game.Entities.Local;
import Game.Entities.Player;
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

    void addPortal(int id, String name, double latitude, double longitude, int energy, int maxEnergy) throws IllegalArgumentException, AlreadyExistsException;

    void addConnector(int id, String name, double latitude, double longitude, int energy, int cooldown) throws IllegalArgumentException, AlreadyExistsException;

    void updatePortal(int id, String name, double latitude, double longitude, int energy, int maxEnergy) throws IllegalArgumentException, NoSuchLocalException;

    void updateConnector(int id, String name, double latitude, double longitude, int energy, int cooldown) throws IllegalArgumentException, NoSuchLocalException;

    void removePortal(int id) throws IllegalArgumentException, NoSuchLocalException;

    void removeConnector(int id) throws IllegalArgumentException, NoSuchLocalException;

    Iterator<Local> listLocalsOrdered(SortLocals filter);

    void loadLocals(String fileName) throws IllegalArgumentException, IOException;

    void exportLocals(String fileName) throws IllegalArgumentException, IOException;

    // ROUTES MANAGEMENT

    void addRoute(int id1, int id2) throws IllegalArgumentException, NoSuchLocalException;

    void removeRoute(int id1, int id2) throws IllegalArgumentException, NoSuchLocalException;

    void loadRoutesLocals(String fileName) throws IllegalArgumentException, IOException;

    void exportRoutesLocals(String fileName) throws IllegalArgumentException, IOException;

    // PLAYERS MANAGEMENT

    void addPlayer(int id, String name, String team) throws IllegalArgumentException, AlreadyExistsException, NoSuchTeamException;

    void updatePlayer(int id, String name, String team) throws IllegalArgumentException, NoSuchPlayerException, NoSuchTeamException;

    void removePlayer(int id) throws IllegalArgumentException, NoSuchPlayerException;

    void movePlayer(int player, int local) throws IllegalArgumentException, NoSuchPlayerException, NoSuchLocalException;

    void addTeam(String name) throws IllegalArgumentException, AlreadyExistsException;

    void addPlayerToTeam(int player, String team) throws IllegalArgumentException, NoSuchPlayerException, NoSuchTeamException;

    void removePlayerFromTeam(int player, String team) throws IllegalArgumentException, NoSuchPlayerException, NoSuchTeamException;

    Iterator<Player> listPlayersOrdered(SortPlayers filter);

    void loadPlayers(String fileName) throws IllegalArgumentException, IOException;

    void exportPlayers(String fileName) throws IllegalArgumentException, IOException;

    // GAME MANAGEMENT

    void chargePlayer(int player, int local) throws IllegalArgumentException, NoSuchPlayerException, NoSuchLocalException, InvalidLocalException, WrongLocationException, CooldownNotOverException;

    void acquirePortal(int player, int local) throws IllegalArgumentException, NoSuchPlayerException, NoSuchLocalException, InvalidLocalException, NoTeamException, WrongLocationException, NotEnoughEnergyException, AlreadyConqueredPortalException;

    void chargePortal(int player, int local, int energy) throws IllegalArgumentException, NoSuchPlayerException, NoSuchLocalException, InvalidLocalException, WrongLocationException, NoTeamException, NotEnoughEnergyException, NotConqueredPortalException;

    Iterator<Local> getShortestPath(int local1, int local2) throws IllegalArgumentException, NoSuchLocalException;

    Iterator<Local> getShortestPath(int local1, int local2, boolean portals) throws IllegalArgumentException, NoSuchLocalException;

    void exportShortestPath(Iterator<Local> path, String fileName) throws IllegalArgumentException, IOException;

    void loadGameData(String fileName) throws IllegalArgumentException, IOException;

    void exportGameData(String fileName) throws IllegalArgumentException, IOException;

    // PLAYER ONLY NEEDED FUNCTIONS

    int getPlayerEnergy(int player) throws IllegalArgumentException, NoSuchPlayerException;

    int getPlayerLocal(int player) throws IllegalArgumentException, NoSuchPlayerException;

    String getPlayerTeam(int player) throws IllegalArgumentException, NoSuchPlayerException;

    int getPlayerExperiencePoints(int player) throws IllegalArgumentException, NoSuchPlayerException;

    int getPlayerLevel(int player) throws IllegalArgumentException, NoSuchPlayerException;

    Iterator<Local> getConqueredPortals(int player) throws IllegalArgumentException, NoSuchPlayerException;

    Iterator<Local> getLocalsInRange(int local, double range) throws IllegalArgumentException, NoSuchLocalException;
}
