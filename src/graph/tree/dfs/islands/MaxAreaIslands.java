package graph.tree.dfs.islands;

import java.util.ArrayDeque;
import java.util.Deque;

//695. Max Area of Island
public class MaxAreaIslands {

    //graph.tree.dfs
    public int maxAreaOfIsland(int[][] grid) {
        int max_area = 0;
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                if (grid[i][j] == 1)
                    max_area = Math.max(max_area, dfs(grid, i, j));
        return max_area;
    }

    public int dfs(int[][] grid, int i, int j) {

        int m = grid.length, n = grid[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n) {
            // 超出索引边界
            return 0;
        }
        if (grid[i][j] == 0) {
            // 已经是海水了
            return 0;
        }
        // 将 (i, j) 变成海水
        grid[i][j] = 0;

        return dfs(grid, i + 1, j)
                + dfs(grid, i, j + 1)
                + dfs(grid, i - 1, j)
                + dfs(grid, i, j - 1) + 1;
    }

    // graph.tree.bfs
    public int maxAreaOfIsland2(int[][] grid) {
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1)
                    max = Math.max(max, bfs(grid, i, j));
            }
        }
        return max;
    }

    public int bfs(int[][] grid, int i, int j) {
        Deque<int[]> queue = new ArrayDeque<>();
        int[] dirs = new int[]{-1, 0, 1, 0, -1};
        queue.add(new int[]{i, j});

        // mark it visited
        grid[i][j] = -1;
        int island = 1;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            for (int k = 0; k < dirs.length - 1; k++) {
                int r = curr[0] + dirs[k];
                int c = curr[1] + dirs[k + 1];
                if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] == 1) {
                    island++;
                    grid[r][c] = -1;
                    queue.add(new int[]{r, c});
                }

            }
        }
        return island;
    }

}
