package graph.tree.dfs.islands;

public class NumEnclaves {

    /**
     * 1、用0表示陆地，用1表示海水。
     * <p>
     * 2、让你计算「封闭岛屿」的面积总和
     */
    // 主函数：计算封闭岛屿的面积总和

    int area = 0;

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

        // 遍历 grid，单纯统计总数量/面积即可
        for (int i = 1; i < grid.length - 1; i++) {
            for (int j = 1; j < grid[0].length - 1; j++) {
                if (grid[i][j] == 0) {
                    //dfs(grid, i, j);
                    area++;
                }
            }
        }
        return area;
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
