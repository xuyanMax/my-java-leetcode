package graph.dijkstra;

import org.junit.Test;

import java.util.*;

// leetcode 1631 这道题中评判一条路径是长还是短的标准不再是路径经过的权重总和，而是路径经过的权重最大值
public class MinPhysicalStrengthPath {

    class State {
        // matrix indexes
        int x, y;
        // min physical strength from start (0,0)
        int absHeightFromStart;

        public State(int x, int y, int absHeightFromStart) {
            this.x = x;
            this.y = y;
            this.absHeightFromStart = absHeightFromStart;
        }
    }
    // distFromStart[i][j] = Math.max(distFromStart[currX][currY], Math.abs(heights[i][j] -heights[currX][currY]))
    // pq.add(new State(i, j, distFromStart[i][j]))

    /* Dijkstra 算法，计算 (0, 0) 到 (m - 1, n - 1) 的最小体力消耗*/
    int minimumEffortPath(int[][] heights) {
        int[][] absDistFromStart = new int[heights.length][heights[0].length];
        for (int i = 0; i < heights.length; i++)
            Arrays.fill(absDistFromStart[i], Integer.MAX_VALUE);

        absDistFromStart[0][0] = 0;
        Queue<State> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.absHeightFromStart));
        pq.add(new State(0, 0, absDistFromStart[0][0]));

        while (!pq.isEmpty()) {
            State curr = pq.poll();
            int x = curr.x, y = curr.y, absHeightFromStart = curr.absHeightFromStart;
            System.out.println("[" + x + "," + y + "]");
            // reach target end
            if (x == heights.length - 1 && y == heights[0].length - 1)
                return absHeightFromStart;

            if (absHeightFromStart > absDistFromStart[x][y])
                continue;// already have a shorter path to X, Y node

            for (int[] edge : getAdj(heights, x, y)) {
                int nextX = edge[0], nextY = edge[1];
                // get max jump btw [x,y] and [nextX, nextY]; compare it with the previous max jump to [x,y]
                int absHeightFromCurr2Next = Math.max(absDistFromStart[x][y], Math.abs(heights[nextX][nextY] - heights[x][y]));
                System.out.println(nextX + "," + nextY + ", height jump=" + absHeightFromCurr2Next);
                // get min effort
                System.out.println(absDistFromStart[nextX][nextY]);
                if (absHeightFromCurr2Next < absDistFromStart[nextX][nextY]) {
                    absDistFromStart[nextX][nextY] = absHeightFromCurr2Next;
                    pq.add(new State(nextX, nextY, absDistFromStart[nextX][nextY]));
                }
            }
        }
        return 0;

    }

    int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public List<int[]> getAdj(int[][] matrix, int x, int y) {
        int m = matrix.length, n = matrix[0].length;
        List<int[]> res = new ArrayList<>();
//        List<int[]>[] graph = new ArrayList<>[matrix.length];
        for (int[] dir : dirs) {
            int nextX = x + dir[0], nextY = y + dir[1];
            if (nextX >= m || nextX < 0 || nextY >= n || nextY < 0) continue;
            res.add(new int[]{nextX, nextY});
        }
        return res;
    }


    @Test
    public void test() {
        int[][] heights = new int[][]{{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
        System.out.println(minimumEffortPath(heights));
    }

    /*short solution, no need to create ds to hold id, weight.*/
    int minimumEffortPath_short(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);

        dist[0][0] = 0;

        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(intArray(0, 0, dist[0][0]));

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int d = curr[0], src = curr[1], dest = curr[2];

            if (d > dist[src][dest]) continue;
            if (src == m - 1 && dest == n - 1) return d;


            for (int[] edge : getAdj(heights, src, dest)) {
                int nX = edge[0], nY = edge[1];
                int newDist = Math.max(dist[src][dest], Math.abs(heights[nX][nY] - heights[src][dest]));
                if (newDist < dist[nX][nY]) {
                    dist[nX][nY] = newDist;
                    pq.add(intArray(dist[nX][nY], nX, nY));
                }
            }
        }
        return 0;
    }

    int[] intArray(int dist, int src, int end) {
        return new int[]{dist, src, end};
    }

    @Test
    public void test_short() {
        int[][] heights = new int[][]{{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
        System.out.println(minimumEffortPath_short(heights));
    }

}
