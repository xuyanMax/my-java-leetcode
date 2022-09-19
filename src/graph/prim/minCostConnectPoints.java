package graph.prim;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by xu on 2022/9/19 08:42.
 */
public class minCostConnectPoints {
    //point(x,y) points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
    public int minCostConnectPoints(int[][] points) {
        List<int[]>[] graph = buildGraph(points);
        Prim prim = new Prim(graph);
        if (!prim.allConnected())
            return -1;
        return prim.weightSum();
    }

    // 构造无向图
    public List<int[]>[] buildGraph(int[][] points) {
        List<int[]>[] graph = new LinkedList[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                // 用 points 中的索引表示坐标点
                int x1 = points[i][0], y1 = points[i][1];
                int x2 = points[j][0], y2 = points[j][1];
                int weight = Math.abs(x1 - x2) + Math.abs(y1 - y2);
                //无向图，两条边
                graph[i].add(new int[]{i, j, weight});
                graph[j].add(new int[]{j, i, weight});

            }
        }
        return graph;
    }
}
