package bfs.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PacificWaterFlow {

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
}
