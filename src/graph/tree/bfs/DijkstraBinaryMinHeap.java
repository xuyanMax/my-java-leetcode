package graph.tree.bfs;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xu
 * <p>
 * Find single source S.P. using Dijkstra's algorithm
 * <p>
 * time complexity: O(ElogV) with the use of binary min arr.heap; O(VlgV) with the use of fibonacci arr.heap
 * space complexity: O(E+V)
 * <p>
 * references:
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/DijkstraShortestPath.java
 * CLRS books
 */

public class DijkstraBinaryMinHeap {

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<Integer>(true);
        graph.addEdge(1, 5, 3);
        graph.addEdge(1, 2, 5);
        graph.addEdge(1, 4, 9);
        graph.addEdge(2, 3, 2);
        graph.addEdge(3, 4, 3);
        graph.addEdge(6, 4, 2);
        graph.addEdge(5, 6, 2);

        DijkstraBinaryMinHeap a = new DijkstraBinaryMinHeap();
        Vertex<Integer> sourceVertex = graph.getVertex(1);

        Map<Vertex<Integer>, Integer> distances = a.shortestPath(graph, sourceVertex);
        System.out.println(distances);
    }

    public Map<Vertex<Integer>, Integer> shortestPath(Graph<Integer> graph, Vertex<Integer> sourceVertex) {
        // hold all s.p. distances from all nodes to the sourceVertex
        Map<Vertex<Integer>, Integer> distances = new HashMap<>();

        // minHeap hold all distances (all nodes to the sourceVertex) as weight
        BinaryMinHeap<Vertex<Integer>> bMinHeap = new BinaryMinHeap<>();

        // parent pointer
        Map<Vertex<Integer>, Vertex<Integer>> parents = new HashMap<>();

        // set all vertices distance to sourceVertex as infinity
        for (Vertex<Integer> vertex : graph.getAllVertex())
            bMinHeap.add(10000, vertex);

        // decrease sourceVertex as 0
        bMinHeap.decrease(sourceVertex, 0);

        distances.put(sourceVertex, 0);

        // set source parent as NULL
        parents.put(sourceVertex, null);

        while (!bMinHeap.empty()) {
            BinaryMinHeap<Vertex<Integer>>.Node currentNode = bMinHeap.extractMinNode();

            Vertex<Integer> currentVertex = (Vertex<Integer>) currentNode.key;
//			Vertex<Integer> currentVertex = bMinHeap.extractMin(); 

            // updateHighestHeightBtwLR distance(src, currentVertex) <- currentNode.weight
            distances.put(currentVertex, currentNode.weight);

            for (Edge<Integer> edge : currentVertex.getEdges()) {

                Vertex<Integer> vertAdj = getAdjVertFromCurrVert(edge, currentVertex);

                /*if arr.heap does not contain adjacent vertex means adjacent vertex already has shortest distance from source vertex*/
                if (!bMinHeap.containsData(vertAdj))
                    continue;

                Relax(currentVertex, vertAdj, bMinHeap, edge, parents, distances);
            }

        }

        return distances;
    }

    public Vertex<Integer> getAdjVertFromCurrVert(Edge<Integer> edge, Vertex<Integer> curr) {
        return edge.getVertex1().equals(curr) ? edge.getVertex2() : edge.getVertex1();
    }

    public void Relax(Vertex<Integer> u, Vertex<Integer> v,
                      BinaryMinHeap<Vertex<Integer>> bMinHeap, Edge<Integer> edge, Map<Vertex<Integer>, Vertex<Integer>> parents,
                      Map<Vertex<Integer>, Integer> distances) {

        /* get distance form distances rather than bMinHeap of u!!! */
        int newDistance = distances.get(u) + edge.getWeight();

        if (bMinHeap.getWeight(v) > newDistance) {
            bMinHeap.decrease(v, newDistance);
            parents.put(v, u);
        }

    }

}
