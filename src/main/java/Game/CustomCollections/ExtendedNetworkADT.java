package Game.CustomCollections;

import Collections.Exceptions.IllegalArgumentException;
import Collections.Exceptions.NoSuchElementException;
import Collections.Graphs.NetworkADT;

import java.util.Iterator;

/**
 * ExtendedNetworkADT defines the interface for a network with additional features.
 */
public interface ExtendedNetworkADT<T> extends NetworkADT<T> {

    @Override
    void addEdge(T vertex1, T vertex2) throws NoSuchElementException, IllegalArgumentException;

    @Override
    void addEdge(T place1, T place2, double weight) throws NoSuchElementException, IllegalArgumentException;

    void updateVertex(T oldVertex, T newVertex) throws NoSuchElementException, IllegalArgumentException;

    boolean containsVertex(T vertex);

    //return all vertexes
    Iterator<T> iteratorVertexes();

    //return all routes
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
