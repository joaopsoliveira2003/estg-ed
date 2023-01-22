package Game.API;

import Exceptions.IllegalArgumentException;
import Game.CustomCollections.ExtendedNetwork;
import Game.CustomCollections.ExtendedNetworkImpl;
import Game.Entities.Place;
import Game.Exceptions.NoSuchPlaceException;

public class GameImpl implements Game {

    private final ExtendedNetwork<Place> network;

    public GameImpl() {
        network = new ExtendedNetworkImpl<>();
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

    // PLAYERS AND TEAMS


}
