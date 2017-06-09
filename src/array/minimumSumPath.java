package array;

/**
 * 
 * @author xu
 * 
 * https://leetcode.com/problems/minimum-path-sum/#/description
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of
 * 
 * all numbers along its path. Note: You can only move either down or right at any point in time.
 * 
 */
public class minimumSumPath {

	public static void main(String[] args) {
		

	}
	static int solution(int[][] grid) {
		
		int col = grid[0].length;
		int row = grid.length;
		int[][] dp = new int[row][col];
		
		dp[0][0] = grid[0][0];
		
		for (int i=1; i<col; i++) 
			dp[0][i] += dp[0][i-1];
		for (int i=1; i<col; i++) 
			dp[i][0] += dp[i][0];
		for (int i=1; i<row; i++) 
			for (int j=1; j<col; j++) 
				dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
	
		return dp[row - 1][col - 1];
	}
	
	static int solution2(int[][] grid) {
		int col = grid[0].length;
		int[] dp = new int[col];
		
		dp[0] = grid[0][0];
		for (int i=1; i<col; i++)
			dp[i] += dp[i-1];
		
		for (int[] row : grid) {
			for (int i=0; i<col; i++) {
				if (i == 0)
					dp[i] = dp[i] + row[i];
				else 
					dp[i] = Math.min(dp[i-1], dp[i]) + row[i];
			}
		}
		
		return dp[col - 1];
	}

}
