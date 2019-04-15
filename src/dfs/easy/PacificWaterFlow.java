package dfs.easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by xu on 08/08/2017.
 * 417.
 * Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent,
 * the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the
 * right and bottom edges.
 * <p>
 * Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal
 * or lower.
 * <p>
 * Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
 * <p>
 * Note:
 * The order of returned grid coordinates does not matter.
 * Both m and n are less than 150.
 * Example:
 * <p>
 * Given the following 5x5 matrix:
 * <p>
 * Pacific ~   ~   ~   ~   ~
 * ~  1   2   2   3  (5) *
 * ~  3   2   3  (4) (4) *
 * ~  2   4  (5)  3   1  *
 * ~ (6) (7)  1   4   5  *
 * ~ (5)  1   1   2   4  *
 * *   *   *   * Atlantic
 * <p>
 * Return:
 * <p>
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
 */
public class PacificWaterFlow {
    //创建两个Queue，分别将对应大洋边界加入其中
    //保留两个大小相同visited matrix，分别存储两个Queue所有能到达对应边界的点
    //最终，提取所有能同时到达两个边界的点，可就是在两个visited matrix中都为true的点
    public int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // DFS
    public List<int[]> pacificAtlantic_dfs(int[][] matrix) {
        List<int[]> ret = new ArrayList<>();
        if (matrix == null || matrix.length == 0)
            return ret;

        int ROW = matrix.length;
        int COL = matrix[0].length;

        boolean[][] pacificVisited = new boolean[ROW][COL];
        boolean[][] atlanticVisited = new boolean[ROW][COL];

        // 深度优先搜索；仍然需要将四条边界作为初始的节点
        for (int i = 0; i < ROW; i++) {
            //初始化value为MIN值,即边界值与MIN值相比一定得到大于，标记visited为true
            dfs(matrix, pacificVisited, Integer.MIN_VALUE, i, 0);
            dfs(matrix, atlanticVisited, Integer.MIN_VALUE, i, COL - 1);
        }
        for (int i = 0; i < COL; i++) {
            dfs(matrix, pacificVisited, Integer.MIN_VALUE, 0, i);
            dfs(matrix, atlanticVisited, Integer.MIN_VALUE, ROW - 1, i);
        }
        // 返回结果
        for (int i = 0; i < ROW; i++)
            for (int j = 0; j < COL; j++)
                if (pacificVisited[i][j] && atlanticVisited[i][j])
                    ret.add(new int[]{i, j});
        return ret;
    }

    public void dfs(int[][] matrix, boolean[][] visited, int value, int x, int y) {
        //dfs边界条件判断
        // 超越边界||已经访问过||该节点的值小于上一层调用节点的值
        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length ||
                visited[x][y] ||
                matrix[x][y] < value)
            return;

        visited[x][y] = true;

        for (int[] direction : directions)
            dfs(matrix, visited, matrix[x][y], x + direction[0], y + direction[1]);
    }
}
