package graph.prim;

import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by xu on 2022/9/17 23:25.
 * https://mp.weixin.qq.com/s/bvi0wGdbtB4nkYye0yzmqg
 */
public class Prim {
    // 核心数据结构，存储「横切边」的优先级队列
    private PriorityQueue<int[]> pq;
    // 类似 visited 数组的作用，记录哪些节点已经成为最小生成树的一部分
    private boolean[] inMST;
    // 记录最小生成树的权重和
    private int weightSum = 0;
    // graph 是用邻接表表示的一幅图，
    // graph[s] 记录节点 s 所有相邻的边，
    // 三元组 int[]{from, to, weight} 表示一条边

    private List<int[]>[] graph;

    public Prim(List<int[]>[] graph) {
        this.graph = graph;
        pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        //任意切分一个节点
        this.inMST = new boolean[graph.length];
        // 随便从一个点开始切分都可以，我们不妨从节点 0 开始
        cut(0);
        inMST[0] = true;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            if (inMST[curr[1]])
                continue;
            // 将边 edge 加入最小生成树
            weightSum += curr[2];
            inMST[curr[1]] = true;
            cut(curr[1]);// 节点 to 加入后，进行新一轮切分，会产生更多横切边
        }
    }

    // 将 s 的横切边加入优先队列
    private void cut(int s) {
        // 遍历s临边
        for (int[] edge : graph[s]) {
            if (inMST[edge[1]])
                // 相邻接点 to 已经在最小生成树中，跳过
                // 否则这条边会产生环
                continue;
            // 加入横切边队列
            pq.add(edge);
        }

    }

    // 最小生成树的权重和
    public int weightSum() {
        return weightSum;
    }

    // 判断最小生成树是否包含图中的所有节点
    public boolean allConnected() {
        for (int i = 0; i < inMST.length; i++)
            if (!inMST[i])
                return false;
        return true;
    }
}
