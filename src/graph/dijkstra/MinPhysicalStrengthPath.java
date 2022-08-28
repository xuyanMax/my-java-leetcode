package graph.dijkstra;

import java.util.*;

// leetcode 1631 这道题中评判一条路径是长还是短的标准不再是路径经过的权重总和，而是路径经过的权重最大值
public class MinPhysicalStrengthPath {

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

            if (absHeightFromStart > absDistFromStart[x][y])
                continue;// already have a shorter path to X, Y node

            for (int[] edge : getAdj(heights, x, y)) {
                int nextX = edge[0], nextY = edge[1];
                // get max jump btw [x,y] and [nextX, nextY]; compare it with the previous max jump to [x,y]
                int absHeightFromCurr2Next = Math.max(absDistFromStart[x][y], Math.abs(heights[nextX][nextY] - heights[x][y]));
                // get min effort
                if (absHeightFromCurr2Next < absDistFromStart[nextX][nextY]){
                    absDistFromStart[nextX][nextY] = absHeightFromCurr2Next;
                    pq.add(new State(nextX, nextY, absDistFromStart[nextX][nextY]));
                }
            }
        }
        return -1;

    }

    int[][] dirs = new int[][]{{0, 1}, {1, 1}, {-1, -1}, {1, 0}};

    public List<int[]> getAdj(int[][] matrix, int x, int y) {
        int m = matrix.length, n = matrix[0].length;
        List<int[]> res = new ArrayList<>();
//        List<int[]>[] graph = new ArrayList<>[matrix.length];
        for (int[] dir : dirs) {
            int nextX = x + dir[0], nextY = y + dir[1];
            if (nextX >= m || x < 0 || y >= n || y < 0) continue;
            res.add(new int[]{nextX, nextY});
        }
        return res;
    }

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
}
