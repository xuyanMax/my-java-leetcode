package dfs.hard;

//778. Swim in Rising Water
//2 <= N <= 50.
//grid[i][j] is a permutation of [0, ..., N*N - 1].

/**
 * On an N x N grid, each square grid[i][j] represents the elevation at that point (i,j).
 *
 * Now rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another
 * 4-directionally adjacent square if and only if the elevation of both squares individually are at most t.
 * You can swim infinite distance in zero time.
 * Of course, you must stay within the boundaries of the grid during your swim.
 *
 * You start at the top left square (0, 0). What is the least time until you can reach the bottom right square (N-1, N-1)?
 *
 * Example 1:
 *
 * Input: [[0,2],[1,3]]
 * Output: 3
 * Explanation:
 * At time 0, you are in grid location (0, 0).
 * You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
 *
 * You cannot reach point (1, 1) until time 3.
 * When the depth of water is 3, we can swim anywhere inside the grid.
 * Example 2:
 *
 * Input: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
 * Output: 16
 * Explanation:
 *  0  1  2  3  4
 * 24 23 22 21  5
 * 12 13 14 15 16
 * 11 17 18 19 20
 * 10  9  8  7  6
 *
 * The final route is marked in bold.
 * We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
 * Note:
 *
 * 2 <= N <= 50.
 * grid[i][j] is a permutation of [0, ..., N*N - 1].
 */
public class SwimmingRisingWater {

    // check bfs solution
    // dfs solution

    public int swimInWater(int[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;
        int N = grid.length, M = grid[0].length;
        boolean[][] visited = new boolean[N][M];
        // Set<Integer> set = new HashSet<Integer>();
        // 每次添加[i,j] set.add(i*N+j)即可
        // while(!set.contains(N*N-1))

        int time = 0;// 每次递增1 dfs返回后，直到能达NN
        while (!visited[N - 1][M - 1]) {
            // clear visited marks
            visited = new boolean[N][M];
            dfs(grid, 0, 0, time, visited);
            time++;
        }
        // 减去dfs返回后time++的成本
        return time - 1;

    }

    public void dfs(int[][] grid, int x, int y, int time, boolean[][] visited) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || visited[x][y] || grid[x][y] > time)
            return;
        // mark visited
        visited[x][y] = true;
        // end point check
        if (x == grid.length - 1 && y == grid[0].length - 1)
            return;

        int[] dir = new int[]{-1, 0, 1, 0, -1};
        for (int i = 0; i < dir.length - 1; i++) {
            dfs(grid, x + dir[i], y + dir[i + 1], time, visited);
        }
    }
}
