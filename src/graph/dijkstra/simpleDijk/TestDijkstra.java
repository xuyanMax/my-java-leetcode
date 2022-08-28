package graph.dijkstra.simpleDijk;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xu
 * reference:
 * http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html
 */
public class TestDijkstra {


    public static void main(String[] args) {
        List<Vertex> nodes = new ArrayList<Vertex>();
        List<Edge> edges = new ArrayList<Edge>();
        for (int i = 0; i < 11; i++) {
            Vertex location = new Vertex("Node_" + i, "Node_" + i);
            nodes.add(location);
        }
        addLane("Edge_0", 0, 1, 85, nodes, edges);
        addLane("Edge_1", 0, 2, 217, nodes, edges);
        addLane("Edge_2", 0, 4, 173, nodes, edges);
        addLane("Edge_3", 2, 6, 186, nodes, edges);
        addLane("Edge_4", 2, 7, 103, nodes, edges);
        addLane("Edge_5", 3, 7, 183, nodes, edges);
        addLane("Edge_6", 5, 8, 250, nodes, edges);
        addLane("Edge_7", 8, 9, 84, nodes, edges);
        addLane("Edge_8", 7, 9, 167, nodes, edges);
        addLane("Edge_9", 4, 9, 502, nodes, edges);
        addLane("Edge_10", 9, 10, 40, nodes, edges);
        addLane("Edge_11", 1, 10, 600, nodes, edges);

        Graph graph = new Graph(nodes, edges);
        Dijkstra_simple dijkstra_simple = new Dijkstra_simple(graph);

        dijkstra_simple.execute(nodes.get(0));

        String path = dijkstra_simple.getPath(nodes.get(10));

        System.out.println(path);

    }

    public static void addLane(String id, int srcID, int dstID, int weight, List<Vertex> nodes, List<Edge> edges) {
        Edge tmp = new Edge(id, nodes.get(srcID), nodes.get(dstID), weight);
        edges.add(tmp);
    }
}
