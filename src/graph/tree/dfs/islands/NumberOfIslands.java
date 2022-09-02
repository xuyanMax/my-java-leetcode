package graph.tree.dfs.islands;

/**
 * Created by xu on 19/08/2017.
 * 200. Number of Islands
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 * <p>
 * Example 1:
 * <p>
 * 11110
 * 11010
 * 11000
 * 00000
 * Answer: 1
 * <p>
 * Example 2:
 * <p>
 * 11000
 * 11000
 * 00100
 * 00011
 * Answer: 3
 */
public class NumberOfIslands {
    // visit 遍历每一个grid cell
    // 对于标记为'1'的cell recursively  向周边四个方向 visit
    // 对于访问过标记为'1'的cell，标记为'0'，或者创建一个二维矩阵mat，存储访问信息
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;
        int islands = 0;

        //遍历每一个cell
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                //判断cell是否访问过
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    islands++;
                }
            }
        }

        return islands;
    }
    // 为什么每次遇到岛屿，都要用 DFS 算法把岛屿「淹了」呢？主要是为了省事，避免维护visited数组
    // 从 (x,y) 开始，将与之相邻的陆地都变成海水
    public void dfs(char[][] grid, int x, int y) {
        //边界条件判断
        if (x < 0 || x >= grid.length || y < 0 || y > grid[0].length
                || grid[x][y] != '1')
            return;

        // 标记访问信息:visited
        // 将 (x, y) 变成海水
        grid[x][y] = '0';

        // 向四个方向探索
        // 注意 要与边界条件判断的次序一致
        dfs(grid, x + 1, y);
        dfs(grid, x - 1, y);
        dfs(grid, x, y + 1);
        dfs(grid, x, y - 1);

    }
}
