package graph.tree.dfs.islands;

public class ClosedIslands {
    /**
     * 1、用0表示陆地，用1表示海水。
     * <p>
     * 2、让你计算「封闭岛屿」的数目。所谓「封闭岛屿」就是上下左右全部被1包围的0，也就是说靠边的陆地不算作「封闭岛屿」。
     */
    // 主函数：计算封闭岛屿的数量
    int closedIsland(int[][] grid) {
        // 把靠边的岛屿淹掉
        for (int i = 0; i < grid.length; i++) {
            dfs(grid, i, 0);
            dfs(grid, i, grid[0].length - 1);
        }
        // 把靠边的岛屿淹掉
        for (int j = 0; j < grid[0].length; j++) {
            dfs(grid, grid.length - 1, j);
            dfs(grid, 0, j);
        }
        int num = 0;
        // 遍历 grid，剩下的岛屿都是封闭岛屿
        for (int i = 1; i < grid.length - 1; i++) {
            for (int j = 1; j < grid[0].length - 1; j++) {
                if (grid[i][j] == 0) {
                    dfs(grid, i, j);
                    num++;
                }
            }
        }
        return num;
    }

    void dfs(int[][] grid, int x, int y) {
        if (x < 0 | y < 0 | x >= grid.length | y >= grid[0].length)
            return;
        //不是陆地
        if (grid[x][y] != 0)
            return;
        //变为海水，相当于标记为visited
        grid[x][y] = 1;
        dfs(grid, x + 1, y);
        dfs(grid, x - 1, y);
        dfs(grid, x, y + 1);
        dfs(grid, x, y + 1);

    }
}
