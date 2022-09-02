package graph.tree.dfs.islands;

public class CountSubIslands {
    //1905
    //这道题的关键在于，如何快速判断子岛屿？肯定可以借助 Union Find 并查集算法 来判断
    //什么情况下grid2中的一个岛屿B是grid1中的一个岛屿A的子岛？
    //当岛屿B中所有陆地在岛屿A中也是陆地的时候，岛屿B是岛屿A的子岛。
    //反过来说，如果岛屿B中存在一片陆地，在岛屿A的对应位置是海水，那么岛屿B就不是岛屿A的子岛。

    //那么，我们只要遍历grid2中的所有岛屿，把那些不可能是子岛的岛屿排除掉，剩下的就是子岛。
    // 1 - 岛
    // 0 - 海
    int countSubIslands(int[][] grid1, int[][] grid2) {
        //淹掉，或者标记掉所有不可能是子岛的位置
        for (int i = 0; i < grid1.length; i++)
            for (int j = 0; j < grid1[0].length; j++)
                if (grid2[i][j] == 1 && grid1[i][j] == 0)
                    dfs(grid2, i, j);
        //统计子岛个数

        int num = 0;
        for (int i = 0; i < grid1.length; i++)
            for (int j = 0; j < grid1[0].length; j++)
                if (grid2[i][j] == 1) {
                    dfs(grid1, i, j);
                    num++;
                }
        return num;
    }

    public void dfs(int[][] grid, int i, int j) {
        if (i < 0 | j < 0 | i >= grid.length | j >= grid[0].length)
            return;
        if (grid[i][j] == 0)
            return;
        //标记为海
        grid[i][j] = 0;
        dfs(grid, i + 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);
    }
}
