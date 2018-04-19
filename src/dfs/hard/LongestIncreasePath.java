package dfs.hard;

/**
 * Created by xu on 10/08/2017.

Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down.
You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

nums = [
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
Return 4
The longest increasing path is [1, 2, 6, 9].

Example 2:

nums = [
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
Return 4
The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
*/
public class LongestIncreasePath {

    // To get the max length of increasing sequence
    //  1. Do DFS to every cell
    //  2. Compare every 4 directions and skip cells that are out of boundary or smaller
    //  3. Get matrix's max from every cell's max
    //  4. The key is to CACHE the max length of every cell, because it is highly possible to
    //     revisit a cell and to avoid repetition.
    int[][] dirs = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return 0;
        int max = 1;
        int[][]cache = new int[matrix.length][matrix[0].length];

        for (int i=0; i<matrix.length; i++)
            for (int j=0; j<matrix[0].length; j++) {
                    int len = dfs(matrix, i, j, cache);
                    max = Math.max(len, max);
            }
        return max;
    }
    public int dfs(int[][] matrix, int x, int y, int[][] cache) {

        // return memoization
        if (cache[x][y]!=0)
            return cache[x][y];
        int ROW = matrix.length;
        int COL = matrix[0].length;

        int max = 1;
        for (int[] dir : dirs) {
            int r = dir[0] + x;
            int c = dir[1] + y;
            if (r < 0 || r >= ROW || c < 0 || c >= COL || matrix[r][c] <= matrix[x][y])
                continue;

            // len >= 2
            int len = 1 + dfs(matrix, r, c, cache);
            max = Math.max(len, max);

        }
        cache[x][y] = max;

        return max;
    }
}
