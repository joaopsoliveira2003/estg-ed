package Game.API;

import Collections.Exceptions.IllegalArgumentException;
import Collections.HashTables.HashMap;
import Collections.Lists.LinkedOrderedList;
import Game.CustomCollections.ExtendedNetwork;
import Game.CustomCollections.ExtendedNetworkImpl;
import Game.Entities.Local;
import Game.Entities.Player;
import Game.Entities.Team;
import Game.Enumerations.LocalFilter;
import Game.Enumerations.PlayerFilter;
import Game.Exceptions.NoSuchLocalException;
import Game.Exceptions.NoSuchPlayerException;
import Game.Exceptions.NoSuchTeamException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class GameImpl implements Game {

    private final ExtendedNetwork<Local> network;
    private final HashMap<Player, Local> players;

    public GameImpl() {
        network = new ExtendedNetworkImpl<>();
        players = new HashMap<>();
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
    public void addPlayer(Player player) throws IllegalArgumentException {

    }

    @Override
    public void updatePlayer(Player oldPlayer, Player newPlayer) throws NoSuchPlayerException, IllegalArgumentException {

    }

    @Override
    public void removePlayer(Player player) throws NoSuchPlayerException, IllegalArgumentException {

    }

    @Override
    public void movePlayer(Player player, Local local) throws NoSuchPlayerException, NoSuchLocalException, IllegalArgumentException {

    }

    @Override
    public void addTeam(Team team) throws IllegalArgumentException {

    }

    @Override
    public void addPlayerToTeam(Player player, Team team) throws NoSuchPlayerException, NoSuchTeamException, IllegalArgumentException {

    }

    @Override
    public void removePlayerFromTeam(Player player, Team team) throws NoSuchPlayerException, NoSuchTeamException, IllegalArgumentException {

    }

    @Override
    public LinkedOrderedList<Player> listPlayersOrdered(PlayerFilter filter) {
        return null;
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
