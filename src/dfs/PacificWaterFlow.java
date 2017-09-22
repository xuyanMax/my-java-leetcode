package dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by xu on 08/08/2017.
 */
/*
Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent,
the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the
right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal
or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Note:
The order of returned grid coordinates does not matter.
Both m and n are less than 150.
Example:

Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).

*/
public class PacificWaterFlow {
    //创建两个Queue，分别将对应大洋边界加入其中
    //保留两个大小相同visited matrix，分别存储两个Queue所有能到达对应边界的点
    //最终，提取所有能同时到达两个边界的点，可就是在两个visited matrix中都为true的点
    public int[][] directions = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};

    public List<int[]> pacificAtlantic(int[][] matrix) {

        List<int[]> ret = new ArrayList<>();
        if (matrix == null || matrix.length == 0)
            return ret;

        Queue<int[]> pacific = new LinkedList<>();
        Queue<int[]> atlantic = new LinkedList<>();

        int ROW = matrix.length;
        int COL = matrix[0].length;


        boolean[][] pacificVisited = new boolean[ROW][COL];
        boolean[][] atlanticVisited = new boolean[ROW][COL];

        // 初始化，加入边界节点到queue
        for (int i=0; i<ROW; i++) {
            pacific.add(new int[]{i, 0});
            pacificVisited[i][0] = true;

            atlantic.add(new int[]{i, COL-1});
            atlanticVisited[i][COL-1] = true;
        }

        for (int i=0; i<COL; i++) {
            pacific.add(new int[]{0, i});
            pacificVisited[0][i] = true;

            atlantic.add(new int[]{ROW-1, i});
            atlanticVisited[ROW-1][i] = true;
        }
        bfs(pacific, pacificVisited, matrix);
        bfs(atlantic, atlanticVisited, matrix);

        //整合能同时到达两组边界的点
        for (int i=0; i<ROW; i++)
            for (int j=0; j<COL; j++)
                if (atlanticVisited[i][j] && pacificVisited[i][j])
                    ret.add(new int[]{i,j});

        return ret;
    }

    public void bfs(Queue<int[]> queue, boolean[][] visited, int[][] matrix) {
        while (!queue.isEmpty()) {
            int[] currCell = queue.poll();
            // 对于每一个节点，都要向四个可能的方向探索
            for (int[] direction : directions) {
                int nextCell_x = currCell[0] + direction[0];
                int nextCell_y = currCell[1] + direction[1];

                // 条件判断：该节点是否符合在边界内部，且比currCell节点的值要大，且没有visited过
                if ((nextCell_x >= 0 && nextCell_x < visited.length) &&
                    (nextCell_y >= 0 && nextCell_y < visited[0].length) &&
                    !visited[nextCell_x][nextCell_y] &&
                    matrix[nextCell_x][nextCell_y] >= matrix[currCell[0]][currCell[1]]) {

                    queue.add(new int[]{nextCell_x, nextCell_y});//将该节点加入queue中，接下来继续遍历它的周边节点 bfs
                    visited[nextCell_x][nextCell_y] = true; //标记为所中节点
                }

            }

        }
    }




    // DFS
    public List<int[]> pacificAtlantic_dfs (int[][] matrix){
        List<int[]> ret = new ArrayList<>();
        if (matrix == null || matrix.length == 0)
            return ret;

        int ROW = matrix.length;
        int COL = matrix[0].length;

        boolean[][] pacificVisited = new boolean[ROW][COL];
        boolean[][] atlanticVisited = new boolean[ROW][COL];

        // 深度优先搜索；仍然需要将四条边界作为初始的节点
        for (int i=0; i<ROW; i++) {
            //初始化value为MIN值,即边界值与MIN值相比一定得到大于，标记visited为true
            dfs(matrix, pacificVisited, Integer.MIN_VALUE, i, 0);
            dfs(matrix, atlanticVisited, Integer.MIN_VALUE, i, COL-1);
        }
        for (int i=0; i<COL; i++) {
            dfs(matrix, pacificVisited, Integer.MIN_VALUE, 0, i);
            dfs(matrix, atlanticVisited, Integer.MIN_VALUE, ROW-1, i);
        }
        for (int i=0; i<ROW; i++)
            for (int j=0; j<COL; j++)
                if (pacificVisited[i][j] && atlanticVisited[i][j])
                    ret.add(new int[]{i,j});
        return ret;
    }
    public void dfs(int[][] matrix, boolean[][] visited, int value, int x, int y) {
        //dfs边界条件判断
        // 超越边界||已经访问过||该节点的值小于上一层调用节点的值
        if (x <0 || x >= matrix.length || y < 0 || y >= matrix[0].length ||
                visited[x][y] ||
                matrix[x][y] < value)
            return;

        visited[x][y] = true;

        for (int[] direction : directions)
            dfs(matrix, visited, matrix[x][y], x+direction[0], y+direction[1]);
    }
}
