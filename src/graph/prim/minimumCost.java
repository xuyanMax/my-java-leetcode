package graph.prim;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by xu on 2022/9/19 08:42.
 */
public class minimumCost {
    //[from, to, weight]
    public int minimumCost(int n, int[][] connections) {
        List<int[]>[] graph = buildGraph(n, connections);
        Prim prim = new Prim(graph);
        if (!prim.allConnected())
            return -1;
        return prim.weightSum();
    }

    public List<int[]>[] buildGraph(int n, int[][] connections) {
        List<int[]>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i++)
            graph[n] = new LinkedList<>();

        for (int[] connect : connections) {
            // 题目给的节点编号是从 1 开始的，
            // 但我们实现的 Prim 算法需要从 0 开始编号
            int from = connect[0] - 1;
            int to = connect[1] - 1;
            int weight = connect[2];
            graph[connect[from]].add(new int[]{from, to, weight});
            graph[connect[to]].add(new int[]{to, from, weight});
        }
        return graph;

    }
}
