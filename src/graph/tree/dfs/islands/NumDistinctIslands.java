package graph.tree.dfs.islands;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class NumDistinctIslands {
    //694
    // 「不同的岛屿数量」，题目还是输入一个二维矩阵，0表示海水，1表示陆地，这次让你计算 不同的 (distinct, 外形上) 岛屿数量，函数签名如下：
    // 很显然我们得想办法把二维矩阵中的「岛屿」进行转化，变成比如字符串这样的类型，然后利用 HashSet 这样的数据结构去重，最终得到不同的岛屿的个数。

    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;
        Set<String> islands = new HashSet<>();

        //遍历每一个cell
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                //判断cell是否访问过
                if (grid[i][j] == 1) {
                    StringBuilder path = new StringBuilder();
                    dfs(grid, i, j, islands, path, 100);
                    islands.add(path.toString());
                }
            }
        }
        for (String island : islands) {
            System.out.println(island);
        }
        return islands.size();
    }

    // 为什么每次遇到岛屿，都要用 DFS 算法把岛屿「淹了」呢？主要是为了省事，避免维护visited数组
    // 从 (x,y) 开始，将与之相邻的陆地都变成海水

    public void dfs(int[][] grid, int x, int y, Set<String> islands, StringBuilder path, int dir) {
        //边界条件判断
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length
                || grid[x][y] != 1) {
            //islands.add(path.toString());
            //System.out.println(path.toString());
            return;
        }

        // 标记访问信息:visited
        // 将 (x, y) 变成海水
        // 前序遍历位置：进入 (i, j)
        grid[x][y] = 0;

        // 向四个方向探索
        // 注意 要与边界条件判断的次序一致
        path.append(dir).append(",");
        dfs(grid, x + 1, y, islands, path, 1);
        dfs(grid, x - 1, y, islands, path, 2);
        dfs(grid, x, y + 1, islands, path, 3);
        dfs(grid, x, y - 1, islands, path, 4);
        // 后序遍历位置：离开 (i, j)
        path.append(-dir).append(",");

    }

    @Test
    public void test() {
//        int[][] grid = new int[][]{{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}};
        int[][] grid = new int[][]{{1, 1, 0, 1, 1}, {1, 0, 0, 0, 0}, {0, 0, 0, 0, 1}, {1, 1, 0, 1, 1}};
        numDistinctIslands(grid);

    }
}
