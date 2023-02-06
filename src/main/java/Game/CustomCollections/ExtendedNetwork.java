package Game.CustomCollections;

import Collections.Exceptions.IllegalArgumentException;
import Collections.Exceptions.NoSuchElementException;
import Collections.Graphs.Network;
import Collections.Lists.ArrayUnorderedList;
import Collections.Lists.LinkedUnorderedList;
import Collections.Stacks.LinkedStack;
import Collections.Trees.LinkedHeap;
import Game.Entities.Connector;
import Game.Entities.Local;
import Game.Entities.Portal;

import java.util.Iterator;

public class ExtendedNetwork<T> extends Network<T> implements ExtendedNetworkADT<T> {

    public ExtendedNetwork() {
        super();
    }

    @Override
    public void addEdge(T vertex1, T vertex2) throws NoSuchElementException, IllegalArgumentException {
        if (vertex1 == null || vertex2 == null) {
            throw new IllegalArgumentException("The vertex cannot be null.");
        }
        Local local1 = (Local) vertex1;
        Local local2 = (Local) vertex2;
        int index1 = getIndex(vertex1);
        int index2 = getIndex(vertex2);
        if (index1 == -1 || index2 == -1) {
            throw new NoSuchElementException("The vertex is not in the network.");
        }
        addEdge(index1, index2, local1.getDistanceTo(local2));
    }

    @Override
    public void addEdge(T place1, T place2, double weight) throws NoSuchElementException, IllegalArgumentException {
        if (place1 == null || place2 == null) {
            throw new IllegalArgumentException("The vertex cannot be null.");
        }
        int index1 = getIndex(place1);
        int index2 = getIndex(place2);
        if (index1 == -1 || index2 == -1) {
            throw new NoSuchElementException("The vertex is not in the network.");
        }
        addEdge(index1, index2, weight);
    }

    @Override
    public void updateVertex(T oldVertex, T newVertex) throws NoSuchElementException, IllegalArgumentException {
        if (oldVertex == null || newVertex == null) {
            throw new IllegalArgumentException("The vertex cannot be null.");
        }
        int index = getIndex(oldVertex);
        if (index == -1) {
            throw new NoSuchElementException("The vertex is not in the network.");
        }
        vertices[index] = newVertex;
    }

    @Override
    public boolean containsVertex(T vertex) throws IllegalArgumentException {
        if (vertex == null) {
            throw new IllegalArgumentException("The vertex cannot be null.");
        }
        return getIndex(vertex) != -1;
    }

    @Override
    public void clearRoutes() {
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (i != j) {
                    adjMatrix[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }
    }

    @Override
    public Iterator<T> iteratorVertexes() {
        LinkedUnorderedList<T> templist = new LinkedUnorderedList<>();
        for (int i = 0; i < numVertices; i++) {
            templist.addToRear(vertices[i]);
        }
        return templist.iterator();
    }

    @Override
    public Iterator<T> iteratorRoutes() {
        LinkedUnorderedList<T> templist = new LinkedUnorderedList<>();
        for (int i = 0; i < numVertices; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (adjMatrix[i][j] != Double.POSITIVE_INFINITY && i != j) {
                    templist.addToRear(vertices[i]);
                    templist.addToRear(vertices[j]);
                }
            }
        }
        return templist.iterator();
    }

    @Override
    public Iterator<T> iteratorShortestPath(int startIndex, int targetIndex, boolean portals) {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<>();
        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return templist.iterator();
        }

        Iterator<Integer> it = iteratorShortestPathIndices(startIndex, targetIndex, portals);
        while (it.hasNext()) {
            templist.addToRear(vertices[it.next()]);
        }
        return templist.iterator();
    }

    @Override
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex, boolean portals) {
        return iteratorShortestPath(getIndex(startVertex), getIndex(targetVertex), portals);
    }

    protected Iterator<Integer> iteratorShortestPathIndices(int startIndex, int targetIndex, boolean portals) {
        int index;
        double weight;
        int[] predecessor = new int[numVertices];
        LinkedHeap<Double> traversalMinHeap = new LinkedHeap<>();
        ArrayUnorderedList<Integer> resultList = new ArrayUnorderedList<>();
        LinkedStack<Integer> stack = new LinkedStack<>();

        int[] pathIndex = new int[numVertices];
        double[] pathWeight = new double[numVertices];
        for (int i = 0; i < numVertices; i++) {
            pathWeight[i] = Double.POSITIVE_INFINITY;
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex) || (startIndex == targetIndex) || isEmpty()) {
            return resultList.iterator();
        }

        pathWeight[startIndex] = 0;
        predecessor[startIndex] = -1;
        visited[startIndex] = true;
        weight = 0;

        //Update the pathWeight for each vertex except the startVertex. Notice
        //that all vertices not adjacent to the startVertex will have a
        //pathWeight of infinity for now
        for (int i = 0; i < numVertices; i++) {
            boolean flag = portals ? (vertices[i] instanceof Portal) : (vertices[i] instanceof Connector);
            if (i == startIndex || i == targetIndex) {
                flag = true;
            }
            if (!visited[i] && flag) {
                pathWeight[i] = pathWeight[startIndex] + adjMatrix[startIndex][i];
                predecessor[i] = startIndex;
                traversalMinHeap.addElement(pathWeight[i]);
            }
        }

        do {
            weight = traversalMinHeap.removeMin();
            traversalMinHeap.removeAllElements();
            if (weight == Double.POSITIVE_INFINITY) // no possible path
            {
                return resultList.iterator();
            } else {
                index = getIndexOfAdjVertexWithWeightOf(visited, pathWeight, weight);
                if (index != -1) {
                    visited[index] = true;
                } else {
                    return resultList.iterator();
                }
            }

            //Update the pathWeight for each vertex that has not been
            //visited and is adjacent to the last vertex that was visited.
            //Also, add each unvisited vertex to the heap
            for (int i = 0; i < numVertices; i++) {
                if (!visited[i]) {
                    if ((adjMatrix[index][i] < Double.POSITIVE_INFINITY) && (pathWeight[index] + adjMatrix[index][i]) < pathWeight[i]) {
                        pathWeight[i] = pathWeight[index] + adjMatrix[index][i];
                        predecessor[i] = index;
                    }
                    traversalMinHeap.addElement(pathWeight[i]);
                }
            }
        } while (!traversalMinHeap.isEmpty() && !visited[targetIndex]);

        index = targetIndex;
        stack.push(index);
        do {
            index = predecessor[index];
            stack.push(index);
        } while (index != startIndex);

        while (!stack.isEmpty()) {
            resultList.addToRear((stack.pop()));
        }

        return resultList.iterator();
    }
}
