package Game.CustomCollections;

import Collections.Exceptions.IllegalArgumentException;
import Collections.Exceptions.NoSuchElementException;
import Collections.Graphs.NetworkADT;

import java.util.Iterator;

/**
 * ExtendedNetworkADT defines the interface for a network with additional features.
 */
public interface ExtendedNetworkADT<T> extends NetworkADT<T> {

    /**
     * Adds an edge between the two vertices and with the real distance between them.
     *
     * @param vertex1 the first vertex
     * @param vertex2 the second vertex
     * @throws NoSuchElementException if either vertex does not exist
     * @throws IllegalArgumentException if either vertex is null
     */
    @Override
    void addEdge(T vertex1, T vertex2) throws NoSuchElementException, IllegalArgumentException;

    /**
     * Adds an edge between the two vertices and with the given weight.
     *
     * @param place1 the first vertex
     * @param place2 the second vertex
     * @param weight the weight of the edge
     * @throws NoSuchElementException if either vertex does not exist
     * @throws IllegalArgumentException if either vertex is null
     */
    @Override
    void addEdge(T place1, T place2, double weight) throws NoSuchElementException, IllegalArgumentException;

    /**
     * Updates the vertex with the given old vertex with the new vertex.
     *
     * @param oldVertex the old vertex
     * @param newVertex the new vertex
     * @throws NoSuchElementException if the old vertex does not exist
     * @throws IllegalArgumentException if either vertex is null
     */
    void updateVertex(T oldVertex, T newVertex) throws NoSuchElementException, IllegalArgumentException;

    /**
     * Checks if the network contains the given vertex.
     *
     * @param vertex the vertex
     * @return the index of the given vertex
     */
    boolean containsVertex(T vertex);

    /**
     * Clears all the edges in the network.
     */
    void clearRoutes();

    /**
     * Returns all the vertexes in the network.
     *
     * @return all the vertexes in the network
     */
    Iterator<T> iteratorVertexes();

    /**
     * Returns all the routes in the network.
     *
     * @return all the routes in the network
     */
    Iterator<T> iteratorRoutes();

    /**
     * Returns an iterator that performs a traversal considering if the path should go through portals or connectors.
     *
     * @param startIndex the index of the start vertex
     * @param targetIndex the index of the target vertex
     * @param portals true if the path should go through portals, false if the path should go through connectors
     * @return an iterator that performs a traversal considering if the path should go through portals or connectors
     */
    Iterator<T> iteratorShortestPath(int startIndex, int targetIndex, boolean portals);

    /**
     * Returns an iterator that performs a traversal considering if the path should go through portals or connectors.
     *
     * @param startVertex the start vertex
     * @param targetVertex the target vertex
     * @param portals true if the path should go through portals, false if the path should go through connectors
     * @return an iterator that performs a traversal considering if the path should go through portals or connectors
     */
    Iterator<T> iteratorShortestPath(T startVertex, T targetVertex, boolean portals);
}
