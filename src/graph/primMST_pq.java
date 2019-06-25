package graph;

import javax.jnlp.IntegrationService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

// 未完待续
public class primMST_pq {

    public List<Edge<Integer>> prim(int[][] graph) {
        List<Edge<Integer>> res = new ArrayList<>();
        // pq heap为存储尚未被探索的节点集合
        PriorityQueue<Vertex<Integer>> pq = new PriorityQueue<>((a, b) -> (a.getData() - b.getData()));
        Map<Vertex<Integer>, Edge<Integer>> map = new HashMap<>();
        Graph<Integer> GRAPH = new Graph<>(false);

        for (int i = 0; i < graph.length; i++) {
            for (int j = i + 1; j < graph[0].length; j++) {
                if (graph[i][j] != 0)
                    GRAPH.addEdge(i, j, graph[i][j]);
            }
        }
        Vertex<Integer> start = GRAPH.getAllVertex().iterator().next();
        pq.add(start);
        boolean[] visited = new boolean[graph.length];

        while (!pq.isEmpty()) {

            Vertex<Integer> curr = pq.poll();
            visited[(int) curr.getID()] = true;

            Edge<Integer> edge = map.get(curr);

            if (edge != null)
                res.add(edge);

            for (Edge<Integer> e : curr.getEdges()) {
                Vertex<Integer> adj = e.getVertex1().equals(curr) ? e.getVertex2() : e.getVertex1();

                if (visited[(int) adj.getID()])
                    continue;

                adj.setData(e.getWeight());
                pq.add(adj);//pq中可能重复出现同一个Vertex with 不同的 data(priority)

            }

        }
        return res;


    }
}
