package Game.API;

import Collections.Exceptions.EmptyCollectionException;
import Collections.Exceptions.IllegalArgumentException;
import Collections.Exceptions.NoSuchElementException;
import Collections.HashTables.HashMap;
import Collections.HashTables.MapADT;
import Collections.HashTables.SetADT;
import Collections.HashTables.HashSet;
import Collections.Lists.LinkedOrderedList;
import Game.CustomCollections.ExtendedNetworkADT;
import Game.CustomCollections.ExtendedNetwork;
import Game.Entities.*;
import Game.Enumerations.LocalFilter;
import Game.Enumerations.PlayerFilter;
import Game.Exceptions.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class GameImpl implements Game {

    private final ExtendedNetworkADT<Local> network;
    private final SetADT<Player> players;
    private final SetADT<Team> teams;
    private final MapADT<Player, Local> playerPositions;

    public GameImpl() {
        network = new ExtendedNetwork<>();
        players = new HashSet<>();
        teams = new HashSet<>();
        playerPositions = new HashMap<>();
    }

    // PLACES AND ROUTES

    @Override
    public void addPlace(Local vertex) throws IllegalArgumentException {
        if (vertex == null) {
            throw new IllegalArgumentException("Local cannot be null");
        }
        network.addVertex(vertex);
    }

    @Override
    public void updatePlace(Local oldVertex, Local newVertex) throws NoSuchLocalException, IllegalArgumentException {
        if (oldVertex == null || newVertex == null) {
            throw new IllegalArgumentException("Local cannot be null");
        }
        if (network.containsVertex(oldVertex)) {
            throw new NoSuchLocalException("Local " + oldVertex + " does not exist");
        }
        network.updateVertex(oldVertex, newVertex);
    }

    @Override
    public void removePlace(Local vertex) throws NoSuchLocalException, IllegalArgumentException {
        if (vertex == null) {
            throw new IllegalArgumentException("Local cannot be null");
        }
        if (network.containsVertex(vertex)) {
            throw new NoSuchLocalException("Local " + vertex + " does not exist");
        }
        network.removeVertex(vertex);
    }

    @Override
    public LinkedOrderedList<Local> listPlacesOrdered(LocalFilter filter) {
        return null;
    }

    @Override
    public void addRoute(Local vertex1, Local vertex2) throws NoSuchLocalException, IllegalArgumentException {
        if (vertex1 == null || vertex2 == null) {
            throw new IllegalArgumentException("Local cannot be null");
        }
        if (!network.containsVertex(vertex1) || !network.containsVertex(vertex2)) {
            throw new NoSuchLocalException("Local " + vertex1 + " or " + vertex2 + " does not exist");
        }
        network.addEdge(vertex1, vertex2);
    }

    @Override
    public void removeRoute(Local vertex1, Local vertex2) throws NoSuchLocalException, IllegalArgumentException {
        if (vertex1 == null || vertex2 == null) {
            throw new IllegalArgumentException("Local cannot be null");
        }
        if (!network.containsVertex(vertex1) || !network.containsVertex(vertex2)) {
            throw new NoSuchLocalException("Local " + vertex1 + " or " + vertex2 + " does not exist");
        }
        network.removeEdge(vertex1, vertex2);
    }

    @Override
    public Iterator<Local> getShortestPath(Local vertex1, Local vertex2) throws NoSuchLocalException, IllegalArgumentException {
        return network.iteratorShortestPath(vertex1, vertex2);
    }

    @Override
    public Iterator<Local> getShortestPath(Local vertex1, Local vertex2, boolean portals) throws NoSuchLocalException, IllegalArgumentException {
        return network.iteratorShortestPath(vertex1, vertex2, portals);
    }

    @Override
    public void exportShortestPath(Iterator<Local> path, String fileName) throws IOException, IllegalArgumentException {
        if (fileName == null) {
            throw new IllegalArgumentException("File name cannot be null");
        }
        JSONObject main = new JSONObject();
        JSONArray array = new JSONArray();
        while (path.hasNext()) {
            array.add(path.next().getName());
        }
        main.put("path", array);
        File file = new File(fileName);
        FileOutputStream stream = new FileOutputStream(file);
        stream.write(main.toJSONString().getBytes());
        stream.close();
    }

    @Override
    public void addPlayer(Player player) throws IllegalArgumentException, AlreadyExistsException {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        if (players.contains(player)) {
            throw new AlreadyExistsException("Player " + player + " already exists");
        }
        players.add(player);
    }

    @Override
    public void updatePlayer(Player oldPlayer, Player newPlayer) throws NoSuchPlayerException, IllegalArgumentException {
        if (oldPlayer == null || newPlayer == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        if (!players.contains(oldPlayer)) {
            throw new NoSuchPlayerException("Player " + oldPlayer + " does not exist");
        }
        players.remove(oldPlayer);
        try {
            playerPositions.remove(oldPlayer);
        } catch (EmptyCollectionException | NoSuchElementException ignored) {}
        players.add(newPlayer);
    }

    @Override
    public void removePlayer(Player player) throws NoSuchPlayerException, IllegalArgumentException {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        if (!players.contains(player)) {
            throw new NoSuchPlayerException("Player " + player + " does not exist");
        }
        players.remove(player);
        try {
            playerPositions.remove(player);
        } catch (EmptyCollectionException | NoSuchElementException ignored) {}
    }

    @Override
    public void movePlayer(Player player, Local local) throws NoSuchPlayerException, NoSuchLocalException, IllegalArgumentException {
        if (player == null || local == null) {
            throw new IllegalArgumentException("Player or local cannot be null");
        }
        if (!players.contains(player)) {
            throw new NoSuchPlayerException("Player " + player + " does not exist");
        }
        if (!network.containsVertex(local)) {
            throw new NoSuchLocalException("Local " + local + " does not exist");
        }
        playerPositions.put(player, local);
    }

    @Override
    public void addTeam(Team team) throws IllegalArgumentException, AlreadyExistsException {
        if (team == null) {
            throw new IllegalArgumentException("Team cannot be null");
        }
        if (teams.contains(team)) {
            throw new AlreadyExistsException("Team " + team + " already exists");
        }
        teams.add(team);
    }

    @Override
    public void addPlayerToTeam(Player player, Team team) throws NoSuchPlayerException, NoSuchTeamException, IllegalArgumentException {
        if (player == null || team == null) {
            throw new IllegalArgumentException("Player or team cannot be null");
        }
        if (!players.contains(player)) {
            throw new NoSuchPlayerException("Player " + player + " does not exist");
        }
        player.setTeam(team);
    }

    @Override
    public void removePlayerFromTeam(Player player, Team team) throws NoSuchPlayerException, NoSuchTeamException, IllegalArgumentException {
        if (player == null || team == null) {
            throw new IllegalArgumentException("Player or team cannot be null");
        }
        if (!players.contains(player)) {
            throw new NoSuchPlayerException("Player " + player + " does not exist");
        }
        //player.setTeam(null);
    }

    @Override
    public LinkedOrderedList<Player> listPlayersOrdered(PlayerFilter filter) {
        return null;
    }

    @Override
    public void chargePlayer(Player player, Local local) throws NoSuchPlayerException, NoSuchLocalException, InvalidLocalException, CooldownNotOverException, IllegalArgumentException {
        if (player == null || local == null) {
            throw new IllegalArgumentException("Player or local cannot be null");
        }

        if (!players.contains(player)) {
            throw new NoSuchPlayerException("Player " + player.getName() + " does not exist");
        }

        if (!network.containsVertex(local)) {
            throw new NoSuchLocalException("Local " + local.getName() + " does not exist");
        }

        try {
            if (playerPositions.get(player) != local) {
                throw new NoSuchElementException();
            }
        } catch (EmptyCollectionException | NoSuchElementException ignored) {
            throw new InvalidLocalException("Player " + player.getName() + " is not in local " + local.getName() + "!");
        }

        if (local instanceof Connector) {
            //cast local to connector
            Connector connector = (Connector) local;
            if (connector.isCoolDownOver(player)) {
                //charge player with 10% of the energy of the connector
                player.addEnergy((int) (connector.getEnergy() * 0.1));
                connector.addLastInteraction(player);
            } else {
                throw new CooldownNotOverException("Cooldown for player " + player.getName() + " is not over!");
            }
        } else {
            throw new InvalidLocalException("Local " + local.getName() + " is not a connector!");
        }
    }

    @Override
    public void acquirePortal(Player player, Local local) throws NoSuchPlayerException, NoSuchLocalException, NoTeamException, NotEnoughEnergyException, AlreadyConqueredPortalException, IllegalArgumentException {
        if (player == null || local == null) {
            throw new IllegalArgumentException("Player or local cannot be null");
        }
        if (!players.contains(player)) {
            throw new NoSuchPlayerException("Player " + player.getName() + " does not exist");
        }
        if (!network.containsVertex(local)) {
            throw new NoSuchLocalException("Local " + local.getName() + " does not exist");
        }
        try {
            if (playerPositions.get(player) != local) {
                throw new NoSuchElementException();
            }
        } catch (EmptyCollectionException | NoSuchElementException ignored) {
            throw new InvalidLocalException("Player " + player.getName() + " is not in local " + local.getName() + "!");
        }
        //check if the player has a team
        if (player.getTeam() == null) {
            throw new NoTeamException("Player " + player.getName() + " does not have a team!");
        }
        if (local instanceof Portal) {
            Portal portal = (Portal) local;
            try {

                if (portal.getOwner().getTeam() != player.getTeam()) {
                    //equipa adversária
                    // in order to conquer a portal of a different team, the player needs to discharge the portal and then charge with 25% of the max energy of the portal otherwise the portal won't have an owner
                    if (player.getCurrentEnergy() >= portal.getEnergy()) {
                        //enough energy
                        portal.removeOwner();
                        player.removeEnergy(portal.getEnergy());
                        portal.removeEnergy(portal.getEnergy());

                        //now check if the player has enough energy to charge the portal with 25% of the max energy of the portal
                        if (player.getCurrentEnergy() >= (portal.getMaxEnergy() * 0.25)) {
                            //enough energy
                            portal.setOwner(player);
                            player.removeEnergy((int) (portal.getMaxEnergy() * 0.25));
                            portal.addEnergy((int) (portal.getMaxEnergy() * 0.25));
                        } else {
                            //not enough energy
                            throw new NotEnoughEnergyException("Player " + player.getName() + " does not have enough energy to conquer the portal");
                        }
                    } else {
                        //not enough energy
                        throw new NotEnoughEnergyException("Player " + player.getName() + " does not have enough energy to reset the portal");
                    }
                } else {
                    //mesma equipa
                    throw new AlreadyConqueredPortalException("Portal " + portal.getName() + " is already conquered by " + portal.getOwner());
                }
            } catch (NoAssociationException ignored) {
                //sem equipa

                //check if the player has enough energy to charge the portal with 25% of the max energy of the portal
                if (player.getCurrentEnergy() >= (portal.getMaxEnergy() * 0.25)) {
                    //enough energy
                    portal.setOwner(player);
                    player.removeEnergy((int) (portal.getMaxEnergy() * 0.25));
                    portal.addEnergy((int) (portal.getMaxEnergy() * 0.25));
                } else {
                    //not enough energy
                    throw new NotEnoughEnergyException("Player " + player.getName() + " does not have enough energy to conquer the portal");
                }
            }
        }
    }

    @Override
    public void loadGameData(String fileName) throws IOException, IllegalArgumentException {

    }

    @Override
    public void saveGameData(String fileName) throws IOException, IllegalArgumentException {
        JSONObject mainObject = new JSONObject();
        JSONObject locals = new JSONObject();
        JSONArray localsArray = new JSONArray();
        Iterator<Local> iterator = network.iteratorVertexes();
        while (iterator.hasNext()) {
            Local place = iterator.next();
            localsArray.add(place.getJSON());
        }
        locals.put("locals", localsArray);
        mainObject.put("locals", locals);
        JSONObject routes = new JSONObject();
        JSONArray routesArray = new JSONArray();
        iterator = network.iteratorRoutes();
        while (iterator.hasNext()) {
            JSONObject route = new JSONObject();
            route.put("from", iterator.next().getID());
            route.put("to", iterator.next().getID());
            routesArray.add(route);
        }
        routes.put("routes", routesArray);
        mainObject.put("routes", routes);

        JSONObject players = new JSONObject();
        JSONObject teams = new JSONObject();

        //write to a file with fileName
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        //write to text file
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(mainObject.toJSONString().getBytes());
        fileOutputStream.close();
    }
}
