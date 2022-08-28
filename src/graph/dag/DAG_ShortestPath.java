package graph.dag;
/**
 * We calculate single source shortest distances in O(|V|+|E|), the idea is to use TOPOLOGICAL SORTING
 * Once we find the topo order, we one by one process all vertices in topo order and
 * for every vertex being processed, we updateHighestHeightBtwLR its adjacent nodes' distance by RELAXING the edge
 * <p>
 * Time Complexity O(|V|+|E|)
 * <p>
 * After finding topo.order, the algorithm processes all vertices in topo. order and for every vertex,
 * it runs a for loop for all adjacent vertices. Total adjacent vertices in a graph is O(E).
 * Thus, overall complexity is O(V+E)
 * <p>
 * references:
 * http://www.geeksforgeeks.org/shortest-path-for-directed-acyclic-graphs/
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/DAGShortestPathTopological.java
 */

import graph.Edge;
import graph.Graph;
import graph.Vertex;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class DAG_ShortestPath<T> {
    //private Map<Vertex<T>, Integer> distance = new HashMap<Vertex<T>, Integer>();
    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<Integer>(true);

        /**
         * 10 edges using topo.sort first and then
         * 	s r t x y z
         0 1 2 3 4 5
         */
        graph.addEdge(1, 0, 5);
        graph.addEdge(1, 2, 3);
        graph.addEdge(0, 2, 2);
        graph.addEdge(0, 3, 6);
        graph.addEdge(2, 3, 7);
        graph.addEdge(3, 4, -1);
        graph.addEdge(4, 5, -2);
        graph.addEdge(2, 4, 4);
        graph.addEdge(2, 5, 2);
        graph.addEdge(3, 5, 1);
//		graph.

        DAG_ShortestPath<Integer> DAP_SP = new DAG_ShortestPath<Integer>();

        /*Map<Vertex<Integer>, Integer> distance = DAP_SP.shortestPath(graph, new Vertex<Integer>(0)); // fails */

        for (Vertex<Integer> vert : graph.getAllVertex())
            System.out.println(vert.getID());

        Map<Vertex<Integer>, Integer> distance = DAP_SP.shortestPath(graph, graph.getAllVertex().iterator().next());//graph.getVertex(0);
        System.out.println(distance);

    }

    public Map<Vertex<T>, Integer> shortestPath(Graph<T> graph, Vertex<T> source) {
        Map<Vertex<T>, Integer> distance = new HashMap<Vertex<T>, Integer>();

        toposortdfs<T> toposort = new toposortdfs<T>();
        Deque<Vertex<T>> topoSorted = toposort.TopoSort(graph);
        distance.put(source, 0);

        while (!topoSorted.isEmpty()) {
            Vertex<T> vert = topoSorted.pollFirst();

            for (Edge<T> edge : vert.getEdges())
                Relax(vert, edge.getVertex2(), edge, distance);

        }

        return distance;
    }

    public void Relax(Vertex<T> u, Vertex<T> v, Edge<T> edge, Map<Vertex<T>, Integer> distance) {
//		if (distance.get(v)<distance.get(u)+ edge.getWeight()) 
//			distance.put(v, distance.get(u)+ edge.getWeight());

        if (getDistance(v, distance) > getDistance(u, distance) + edge.getWeight()) {
            distance.put(v, getDistance(u, distance) + edge.getWeight());
        }
    }

    public int getDistance(Vertex<T> vertex, Map<Vertex<T>, Integer> distance) {

        return distance.containsKey(vertex) ? distance.get(vertex) : 10000;
    }
}
