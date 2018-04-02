package dp.medium;
/*
* 64. Minimum Path Sum

Given a m x n grid filled with non-negative numbers,
find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example 1:
[[1,3,1],
 [1,5,1],
 [4,2,1]]
Given the above grid map, return 7. Because the path 1→3→1→1→1 minimizes the sum.
* */
public class MinimumPathSum {

    public int minPathSum(int[][] grid) {
        if (grid==null || grid.length==0) return 0;
        int col = grid[0].length;
        int row = grid.length;
        int [][]dp = new int[row][col];
        dp[0][0] = grid[0][0];
        for (int i=1;i<row;i++)
            dp[i][0] = grid[i][0] + dp[i-1][0];

        for (int i=1;i<col;i++)
            dp[0][i] = grid[0][i]+dp[0][i-1];

        for (int i=1;i<row;i++) {
            for(int j=1;j<col;j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1])+grid[i][j];
            }
        }
        return dp[row-1][col-1];
    }
    public int minPathSum1d(int[][] grid) {
        if (grid==null || grid.length==0) return 0;
        int[]dp = new int[grid[0].length];

        // initialize
        dp[0] = grid[0][0];
        for (int j=1; j<grid[0].length; j++)
            dp[j] = dp[j-1]+grid[0][j];

        for (int i=1; i<grid.length; i++){
            for (int j=0; j<grid[0].length; j++){
                if (j==0)
                    dp[j] = dp[j] + grid[i][j];
                else
                    dp[j] = Math.min(dp[j], dp[j-1]) + grid[i][j];
            }
        }
        return dp[grid[0].length-1];
    }
}
