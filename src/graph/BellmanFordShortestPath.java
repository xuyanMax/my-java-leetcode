package graph;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xu
 * Given a graph and a source vertex src in graph, find shortest paths from src to all
 * vertices in the given graph. The graph may contain negative weight edges.
 * <p>
 * Input: Graph and a source vertex src
 * Output: Shortest distance to all vertices from src. If there is a negative weight cycle,
 * then shortest distances are not calculated, negative weight cycle is reported.
 * <p>
 * time complexity - O(EV)
 * space complexity - O(V)
 * <p>
 * References
 * https://en.wikipedia.org/wiki/Bellman%E2%80%93Ford_algorithm
 * http://www.geeksforgeeks.org/dynamic-programming-set-23-bellman-ford-algorithm/
 */

public class BellmanFordShortestPath {

    class NegativeWeightCycleException extends RuntimeException {
    }

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<Integer>(true);
        graph.addEdge(0, 1, -6);
        graph.addEdge(1, 2, 3);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 4, 2);

        graph.addEdge(1, 3, 2);
        graph.addEdge(3, 1, 1);
        graph.addEdge(3, 2, 5);
        graph.addEdge(4, 3, -3);

        // REPROTS NEGATIVE WEIGHT CYCLE
//	        graph.addEdge(0, 1, -6);
//		    graph.addEdge(1, 2, 3);
//	        graph.addEdge(2, 0, 2);
//	        graph.addEdge(1, 4, 2);
//	      
//	        graph.addEdge(1, 3, 2);
//	        graph.addEdge(3, 1, 1);
//	        graph.addEdge(3, 2, 5);
//	        graph.addEdge(4, 3, -3);
//	        
        BellmanFordShortestPath bfsp = new BellmanFordShortestPath();

        Map<Vertex<Integer>, Integer> distances = bfsp.shortestPath(graph, graph.getVertex(0));
        System.out.println(distances);
    }

    public Map<Vertex<Integer>, Integer> shortestPath(Graph<Integer> graph, Vertex<Integer> sourceVertex) {
        Map<Vertex<Integer>, Integer> distances = new HashMap<>();
        Map<Vertex<Integer>, Vertex<Integer>> parents = new HashMap<>();

        // Step 1:set distance of every vertex to be infinity initially
        for (Vertex<Integer> v : graph.getAllVertex())
            distances.put(v, Integer.MAX_VALUE);

        //set distance of source vertex to be 0
        distances.put(sourceVertex, 0);
        parents.put(sourceVertex, null);

        /**
         * iterate |V|-1 times
         * After the i-th iteration of outer loop, the shortest paths with at most i edges are calculated.
         * There can be maximum |V| – 1 edges in any simple path, that is why the outer loop runs |v| – 1 times
         */
        // Step 2: Relax all edges |V| - 1 times. A simple
        // shortest path from src to any other vertex can
        // have at most |V| - 1 edges:
        for (int i = 0; i < graph.getAllVertex().size() - 1; i++) {
            for (Edge<Integer> edge : graph.getAllEdges()) {
                Relax(edge, edge.getVertex1(), edge.getVertex2(), parents, distances);
            }
        }

        // Step 3: check for negative-weight cycles.  The above
        // step guarantees shortest distances if graph doesn't
        // contain negative weight cycle. If we get a shorter
        //  path, then there is a cycle
        for (Edge<Integer> edge : graph.getAllEdges()) {
            if (distances.get(edge.getVertex2()) > distances.get(edge.getVertex1()) + edge.getWeight())
                throw new NegativeWeightCycleException();
        }

        return distances;

    }

    public void Relax(Edge<Integer> edge, Vertex<Integer> u, Vertex<Integer> v,
                      Map<Vertex<Integer>, Vertex<Integer>> parents,
                      Map<Vertex<Integer>, Integer> distances) {
        //relax the edge
        //if we get better distance to v via u then use this distance
        //and set u as parent of v.
        if (distances.get(v) > distances.get(u) + edge.getWeight()) {
            distances.put(v, distances.get(u) + edge.getWeight());
            parents.put(v, u);
        }

    }
}
