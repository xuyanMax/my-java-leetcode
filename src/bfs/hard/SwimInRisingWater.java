package bfs.hard;

import java.util.PriorityQueue;

//778. Swim in Rising Water

/**
 * You start at the top left square (0, 0).
 * What is the least time until you can reach the bottom right square (N-1, N-1)?
 * <p>
 * Input: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
 * Output: 16
 * Explanation:
 * 0  1  2  3  4
 * 24 23 22 21  5
 * 12 13 14 15 16
 * 11 17 18 19 20
 * 10  9  8  7  6
 * <p>
 * The final route is marked in bold.
 * We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
 * <p>
 * 2 <= N <= 50.
 * grid[i][j] is a permutation of [0, ..., N*N - 1].
 */
public class SwimInRisingWater {

    //  BFS (Dijkstra)即可
    // 所求的时间即 所有从 00 到 n n 路径中的最大值中的最小值
    public int swimInWater(int[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>((a, b) -> (a.height - b.height));
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        priorityQueue.add(new Node(0, 0, grid[0][0]));
        int max = grid[0][0];
        while (!priorityQueue.isEmpty()) {
            Node curr = priorityQueue.poll();

            //先比较，更新max
            max = Math.max(max, grid[curr.x][curr.y]);
            //最短路径变形
            if (curr.x == grid.length - 1 && curr.y == grid[0].length - 1)
                break;

//            if (visited[curr.x][curr.y])
//                continue;
//            visited[curr.x][curr.y] = true;
//            max = Math.max(max, grid[curr.x][curr.y]);
            add(grid, priorityQueue, curr.x, curr.y, visited);
        }
        return max;
    }

    public void add(int[][] grid, PriorityQueue<Node> pq, int x, int y, boolean[][] visited) {
        if (x - 1 >= 0 && !visited[x - 1][y]) {
            pq.add(new Node(x - 1, y, grid[x - 1][y]));
            visited[x - 1][y] = true;//加入后就标记，无向图
        }
        if (x + 1 < grid.length && !visited[x + 1][y]) {
            pq.add(new Node(x + 1, y, grid[x + 1][y]));
            visited[x + 1][y] = true;
        }
        if (y - 1 >= 0 && !visited[x][y - 1]) {
            pq.add(new Node(x, y - 1, grid[x][y - 1]));
            visited[x][y - 1] = true;//加入后就标记，无向图
        }
        if (y + 1 < grid.length && !visited[x][y + 1]) {
            pq.add(new Node(x, y + 1, grid[x][y + 1]));
            visited[x][y + 1] = true;
        }
    }

    class Node {
        int x, y, height;

        public Node(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }
}
