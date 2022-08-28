package graph.tree.bfs.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by xu on 07/08/2017.
 * <p>
 * 542.
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
 * <p>
 * The distance between two adjacent cells is 1.
 * Example 1:
 * Input:
 * <p>
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * <p>
 * Output:
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * <p>
 * Example 2:
 * Input:
 * <p>
 * 0 0 0
 * 0 1 0
 * 1 1 1
 * <p>
 * Output:
 * 0 0 0
 * 0 1 0
 * 1 2 1
 * <p>
 * Note:
 * The number of elements of the given matrix will not exceed 10,000.
 * There are at least one 0 in the given matrix.
 * The cells are adjacent in only four directions: up, down, left and right.
 */
public class _01Matrix_bfs {
    int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int[][] updateMatrix(int[][] matrix) {
        int[][] ret = new int[matrix.length][matrix[0].length];
        if (matrix == null || matrix.length == 0)
            return ret;
        int ROW = matrix.length;
        int COL = matrix[0].length;

        Queue<int[]> queue = new LinkedList<>();

        // 将0的位置加入队列，将1的位置设置为MAX值，意味着到最近0的距离为无限大
        for (int i = 0; i < ROW; i++)
            for (int j = 0; j < COL; j++)
                if (matrix[i][j] == 0)
                    queue.add(new int[]{i, j});
                else
                    matrix[i][j] = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int[] currCell = queue.poll();
            for (int[] direc : directions) {
                int row = currCell[0] + direc[0];
                int col = currCell[1] + direc[1];
                //如果新cell的距离值大于当前cell的值+1（因为两个cell距离间隔为1）
                // 那么更新新cell的距离值
                // 否则，继续测试下一个方向值
                if ((row >= 0 && row < ROW) && (col >= 0 && col < COL)
                        && matrix[row][col] > matrix[currCell[0]][currCell[1]] + 1) {
                    //未来有可能需要更新的位置，加入队列中
                    queue.add(new int[]{row, col});
                    matrix[row][col] = matrix[currCell[0]][currCell[1]] + 1;
                }
                // 或者如下
//                if (row < 0 || row >= ROW|| col < 0 || col >= COL)
//                    continue;
//
//                if (matrix[row][col] > matrix[currCell[0]][currCell[1]] + 1) {
//                    //未来有可能需要更新的位置，加入队列中
//                    queue.add(new int[]{row, col});
//                    matrix[row][col] = matrix[currCell[0]][currCell[1]] + 1;
//                }
            }

        }
        return matrix;
    }
}
