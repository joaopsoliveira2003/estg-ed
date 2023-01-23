package Game.API;

import Collections.Exceptions.IllegalArgumentException;
import Collections.HashTables.HashMap;
import Game.CustomCollections.ExtendedNetwork;
import Game.CustomCollections.ExtendedNetworkImpl;
import Game.Entities.Place;
import Game.Entities.Player;
import Game.Entities.Team;
import Game.Exceptions.NoSuchPlaceException;
import Game.Exceptions.NoSuchPlayerException;
import Game.Exceptions.NoSuchTeamException;

import java.util.Iterator;

public class GameImpl implements Game {

    private final ExtendedNetwork<Place> network;
    private final HashMap<Player, Place> players;

    public GameImpl() {
        network = new ExtendedNetworkImpl<>();
        players = new HashMap<>();
    }

    // PLACES AND ROUTES

    @Override
    public void addPlace(Place vertex) throws IllegalArgumentException {
        if (vertex == null) {
            throw new IllegalArgumentException("Place cannot be null");
        }
        network.addVertex(vertex);
    }

    @Override
    public void updatePlace(Place oldVertex, Place newVertex) throws NoSuchPlaceException, IllegalArgumentException {
        if (oldVertex == null || newVertex == null) {
            throw new IllegalArgumentException("Place cannot be null");
        }
        if (network.containsVertex(oldVertex)) {
            throw new NoSuchPlaceException("Place " + oldVertex + " does not exist");
        }
        network.updateVertex(oldVertex, newVertex);
    }

    @Override
    public void removePlace(Place vertex) throws NoSuchPlaceException, IllegalArgumentException {
        if (vertex == null) {
            throw new IllegalArgumentException("Place cannot be null");
        }
        if (network.containsVertex(vertex)) {
            throw new NoSuchPlaceException("Place " + vertex + " does not exist");
        }
        network.removeVertex(vertex);
    }

    @Override
    public void addRoute(Place vertex1, Place vertex2) throws NoSuchPlaceException, IllegalArgumentException {
        if (vertex1 == null || vertex2 == null) {
            throw new IllegalArgumentException("Place cannot be null");
        }
        if (!network.containsVertex(vertex1) || !network.containsVertex(vertex2)) {
            throw new NoSuchPlaceException("Place " + vertex1 + " or " + vertex2 + " does not exist");
        }
        network.addEdge(vertex1, vertex2);
    }

    @Override
    public void removeRoute(Place vertex1, Place vertex2) throws NoSuchPlaceException, IllegalArgumentException {
        if (vertex1 == null || vertex2 == null) {
            throw new IllegalArgumentException("Place cannot be null");
        }
        if (!network.containsVertex(vertex1) || !network.containsVertex(vertex2)) {
            throw new NoSuchPlaceException("Place " + vertex1 + " or " + vertex2 + " does not exist");
        }
        network.removeEdge(vertex1, vertex2);
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
    public void movePlayer(Player player, Place place) throws NoSuchPlayerException, NoSuchPlaceException, IllegalArgumentException {

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
    public Iterator<Place> getShortestPath(Place start, Place end) throws NoSuchPlaceException, IllegalArgumentException {
        return null;
    }

    @Override
    public Iterator<Place> getShortestPathPortals(Player player, Place end, boolean portals) throws NoSuchPlaceException, IllegalArgumentException {
        return null;
    }

    // PLAYERS AND TEAMS


}
