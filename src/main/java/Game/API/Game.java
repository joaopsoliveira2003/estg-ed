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

    void addPortal(int id, String name, double latitude, double longitude, int energy, int maxEnergy) throws IllegalArgumentException, AlreadyExistsException;

    void addConnector(int id, String name, double latitude, double longitude, int energy, int cooldown) throws IllegalArgumentException, AlreadyExistsException;

    void updatePortal(int id, String name, double latitude, double longitude, int energy, int maxEnergy) throws IllegalArgumentException, NoSuchLocalException;

    void updateConnector(int id, String name, double latitude, double longitude, int energy, int cooldown) throws IllegalArgumentException, NoSuchLocalException;

    void removePortal(int id) throws IllegalArgumentException, NoSuchLocalException;

    void removeConnector(int id) throws IllegalArgumentException, NoSuchLocalException;

    Iterator<Local> listLocalsOrdered(LocalFilter filter);

    void addRoute(int id1, int id2) throws IllegalArgumentException, NoSuchLocalException;

    void removeRoute(int id1, int id2) throws IllegalArgumentException, NoSuchLocalException;

    Iterator<Local> getShortestPath(Local local1, Local local2) throws IllegalArgumentException, NoSuchLocalException;

    Iterator<Local> getShortestPath(Local vertex1, Local vertex2, boolean portals) throws IllegalArgumentException, NoSuchLocalException;

    void exportShortestPath(Iterator<Local> path, String fileName) throws IllegalArgumentException, IOException;

    void addPlayer(int id, String name, String team) throws IllegalArgumentException, AlreadyExistsException, NoSuchTeamException;

    void updatePlayer(int id, String name, String team) throws IllegalArgumentException, NoSuchPlayerException, NoSuchTeamException;

    void removePlayer(int id) throws IllegalArgumentException, NoSuchPlayerException;

    void movePlayer(int player, int local) throws IllegalArgumentException, NoSuchPlayerException, NoSuchLocalException;

    void addTeam(String name) throws IllegalArgumentException, AlreadyExistsException;

    void addPlayerToTeam(int player, String team) throws IllegalArgumentException, NoSuchPlayerException, NoSuchTeamException;

    void removePlayerFromTeam(int player, String team) throws IllegalArgumentException, NoSuchPlayerException, NoSuchTeamException;

    LinkedOrderedList<Player> listPlayersOrdered(PlayerFilter filter);

    void chargePlayer(int player, int local) throws IllegalArgumentException, NoSuchPlayerException, NoSuchLocalException, InvalidLocalException, WrongLocationException, CooldownNotOverException;

    void acquirePortal(int player, int local) throws IllegalArgumentException, NoSuchPlayerException, NoSuchLocalException, InvalidLocalException, NoTeamException, WrongLocationException, NotEnoughEnergyException, AlreadyConqueredPortalException;

    void chargePortal(int player, int local, int energy) throws IllegalArgumentException, NoSuchPlayerException, NoSuchLocalException, InvalidLocalException, WrongLocationException, NoTeamException, NotEnoughEnergyException, NotConqueredPortalException;

    void loadGameData(String fileName) throws IllegalArgumentException, IOException;

    void saveGameData(String fileName) throws IllegalArgumentException, IOException;
}
