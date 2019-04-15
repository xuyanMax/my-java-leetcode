package dfs.hard;

//778. Swim in Rising Water
//2 <= N <= 50.
//grid[i][j] is a permutation of [0, ..., N*N - 1].
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
