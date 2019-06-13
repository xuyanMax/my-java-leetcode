package bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by xu on 11/10/2017.
 */
public class BFS_adj {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();// nodes
        int m = in.nextInt();// edges
        int[][] adj = new int[n][n];
//             Arrays.fill(adj, Integer.MAX_VALUE);

        int src = in.nextInt();
        int dest = in.nextInt();
        // m 是往返的联通的路线
        for (int i = 0; i < m; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            adj[from][to] = 1;
            adj[to][from] = 1;
        }
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        queue.add(src);
        visited[src] = true;
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            step++;

            while (size > 0) {
                int curr = queue.poll();
                for (int i = 0; i < n; i++) {

                    if (i == curr || adj[curr][i] == 0 || visited[i])
                        continue;
                    if (i == dest) {
                        System.out.println(step);
                        return;
                    }
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }
}
