package array;

/**
 * 
 * @author xu
 * 
 * Follow up for "Unique Paths": 
 * 
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 * 
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 *
 * https://leetcode.com/problems/unique-paths-ii/#/description
 * 
 */
public class uniquePaths2 {

	public static void main(String[] args) {
		

	}
	
	static int solution2D(int[][]arr, int m, int n) {
        
		
		int[][] dp = new int[m][n];
		
		//flip upper left cell (the start cell): 1 => 0 or 0 => 1
		dp[0][0] ^= 0;

		//first column: if 1, then 0; otherwise, left cell
        for(int i=0;i<m;i++) dp[i][0]= dp[i][0] == 1 ? 0 : 1;
        
        for(int j=0;j<n;j++) dp[0][j]= dp[0][j] == 1 ? 0 : 1;
        
        for (int i=1;i<m;i++)
            for(int j=1;j<n;j++) 
                dp[i][j] = arr[i][j] == 1 ? 0 : dp[i][j-1] + dp[i-1][j];
 
        return dp[m-1][n-1];
	}
	
	// https://discuss.leetcode.com/topic/10974/short-java-solution/17
	// 1d dp table
	/**
	 * 0 0 0
	 * 0 1 0
	 * 0 0 0
	 * 
	 * dp:
	 * 1 1+0 1+0
	 * ----------
	 * 1 0   1+0
	 * ----------
	 * 1 1+0 1+1 = 2
	 * ----------
	 * 
	 */
	static int solution1D(int[][] arr) {
		
		int[] dp = new int[arr[0].length];
		dp[0] = 1;
		for (int[] row : arr) {
			for (int i=0; i<row.length; i++) {
				if (row[i] == 1)
					dp[i] = 0;
				else if (i > 0)
					dp[i] += dp[i-1];
			}
		}
		return dp[dp.length-1];
	}
	
	

}
