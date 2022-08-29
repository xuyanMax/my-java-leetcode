package graph.dijkstra;

import java.util.*;

//1514
//https://mp.weixin.qq.com/s/RXR18dNUyIVoCQXrO46gWA
public class PathWithMaxProb {
    /*edge[i] = [a,b] 表示a-b之间连接的一条无向边, */
    double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        int node = edges.length;

        /*from node - [prob, edge node][prob, edge node]*/
        Map<Integer, List<double[]>> fromMapProbToArray = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0], to = edges[i][1];
            double prob = succProb[i];
            if (!fromMapProbToArray.containsKey(from))
                fromMapProbToArray.put(from, new ArrayList<>());
            else
                fromMapProbToArray.get(from).add(new double[]{prob, to});
        }
        Queue<double[]> pq = new PriorityQueue<>((a, b) -> (int) (b[0] - a[0]));

        double dist_prod[] = new double[n];
        Arrays.fill(dist_prod, -1);

        // start node with p=1
        dist_prod[start] = 1;
        pq.add(newArray(1.0, start));

        while (!pq.isEmpty()) {
            double[] curr = pq.poll();
            double currProb = curr[0];
            int currNode = (int) curr[1];

            if (currNode == end) return currProb;
            if (currProb < dist_prod[currNode]) continue;

            for (double[] prob_dest : fromMapProbToArray.get(currNode)) {
                double next_prob = prob_dest[0] * currProb;
                int nextNode = (int) prob_dest[1];
                // dist prob > cached value
                if (next_prob > dist_prod[nextNode]) {
                    dist_prod[nextNode] = next_prob;
                    pq.add(newArray(next_prob, nextNode));
                }
            }
        }
        //unreachable end node/
        return 0.0;
    }


    private double[] newArray(double prob, int node) {
        return new double[]{prob, node};
    }
}
