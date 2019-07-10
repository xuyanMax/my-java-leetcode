package graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xu
 * <p>
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/PrimMST.java
 * Space complexity - O(E + V)
 * Time complexity - O(ElogV)
 * <p>
 * References
 * https://en.wikipedia.org/wiki/Prim%27s_algorithm
 * CLRS book
 */
public class PrimMST {

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>(false);
        graph.addEdge(1, 2, 3);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 1, 1);
        graph.addEdge(1, 4, 1);
        graph.addEdge(2, 4, 3);
        graph.addEdge(4, 5, 6);
        graph.addEdge(5, 6, 2);
        graph.addEdge(3, 5, 5);
        graph.addEdge(3, 6, 4);

        PrimMST primMST = new PrimMST();

        Collection<Edge<Integer>> mst = primMST.primMST(graph);
        for (Edge<Integer> edge : mst)
            System.out.println(edge);

    }

    public List<Edge<Integer>> primMST(Graph<Integer> graph) {

        //stores final result
        List<Edge<Integer>> result = new ArrayList<>();

        //	binary heap + map val structure
        BinaryMinHeap<Vertex<Integer>> dists = new BinaryMinHeap<>();

        //	map of vertex to edge which gave min cost to this vertex.
        Map<Vertex<Integer>, Edge<Integer>> vertexToEdge = new HashMap<>();

        for (Vertex<Integer> vertex : graph.getAllVertex()) {
            dists.add(Integer.MAX_VALUE, vertex);
        }

        // start mst from any random vertex
        Vertex<Integer> startVertex = graph.getAllVertex().iterator().next();

        //for the start vertex decrease the value in heap + map to 0
        dists.decrease(startVertex, 0);

        while (!dists.empty()) {

            Vertex<Integer> curr = dists.extractMin();

            // get the corresponding edge for this vertex if present and add it to final result.
            // This edge wont be present for first vertex.
            Edge<Integer> spanningTree = vertexToEdge.get(curr);
            if (spanningTree != null)
                result.add(spanningTree);


            // iterate through all the adjacent vertices
            for (Edge<Integer> edge : curr.getEdges()) {
                Vertex<Integer> adj = getVertexForEdge(curr, edge);

                //check if adjacent vertex exist in heap + map and weight attached
                //with this vertex is greater than this edge weight

                if (dists.containsData(adj) && dists.getWeight(adj) > edge.getWeight()) {
                    //decrease the value of adjacent vertex to this edge weight.
                    dists.decrease(adj, edge.getWeight());
                    //add vertex->edge mapping in the graph.
                    vertexToEdge.put(adj, edge);
                }
            }
        }
        return result;
    }

    public Vertex<Integer> getVertexForEdge(Vertex<Integer> u, Edge<Integer> edge) {
        return edge.getVertex1().equals(u) ? edge.getVertex2() : edge.getVertex1();
    }

}
