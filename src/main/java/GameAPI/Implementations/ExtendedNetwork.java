package GameAPI.Implementations;

import Collections.Implementations.Graphs.Network;
import Collections.Implementations.Lists.ArrayUnorderedList;
import Collections.Implementations.Stacks.LinkedStack;
import Collections.Implementations.Trees.LinkedHeap;
import GameAPI.Interfaces.Connector;
import GameAPI.Interfaces.Portal;

import java.util.Iterator;

public class ExtendedNetwork<Place> extends Network<Place> {

    public ExtendedNetwork() {
        super();
    }

    @Override
    public void addEdge(Place vertex1, Place vertex2) {
        GameAPI.Interfaces.Place place1 = (GameAPI.Interfaces.Place) vertex1;
        GameAPI.Interfaces.Place place2 = (GameAPI.Interfaces.Place) vertex2;
        addEdge(getIndex(vertex1), getIndex(vertex2), place1.getDistanceTo(place2));
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

    public Iterator<Place> iteratorShortestPathPortals(int startIndex, int targetIndex, boolean portals) {
        ArrayUnorderedList<Place> templist = new ArrayUnorderedList<>();
        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return templist.iterator();
        }

        Iterator<Integer> it = iteratorShortestPathIndices(startIndex, targetIndex, portals);
        while (it.hasNext()) {
            templist.addToRear(vertices[it.next()]);
        }
        return templist.iterator();
    }

    public Iterator<Place> iteratorShortestPath(Place startVertex, Place targetVertex, boolean portals) {
        return iteratorShortestPathPortals(getIndex(startVertex), getIndex(targetVertex), portals);
    }
}
