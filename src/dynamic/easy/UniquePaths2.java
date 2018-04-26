package dynamic.easy;

/**
 * 
 * @author xu
 * 63. Unique Paths II
 * Follow up for "Unique Paths": 
 * 
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 * 
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 *
 * https://leetcode.com/problems/unique-paths-ii/#/description
 * 
 */
public class UniquePaths2 {

	public static int solution2D(int[][]arr) {

        if (arr==null || arr.length==0) return 0;
        int m = arr.length, n = arr[0].length;
        int[][] dp = new int[m][n];

        if (arr[0][0] == 1) return 0;
        dp[0][0] = arr[0][0]==0?1:0;

        //first column: if 1, then 0; otherwise, left cell
        for(int i=1;i<m;i++) dp[i][0]= arr[i][0] == 1 ? 0 : dp[i-1][0];

        for(int j=1;j<n;j++) dp[0][j]= arr[0][j] == 1 ? 0 : dp[0][j-1];

        for (int i=1;i<m;i++)
            for(int j=1;j<n;j++)
                dp[i][j] = arr[i][j] == 1 ? 0 : dp[i][j-1] + dp[i-1][j];

        return dp[m-1][n-1];
	}
	
	// https://discuss.leetcode.com/topic/10974/short-java-solution/17
	// 1d dynamic table
	/**
	 * 0 0 0
	 * 0 1 0
	 * 0 0 0
	 * 
	 * dynamic:
	 * 1 1+0 1+0
	 * ----------
	 * 1 0   1+0
	 * ----------
	 * 1 1+0 1+1 = 2
	 * ----------
	 * 
	 */
	public static int solution1D(int[][] arr) {
		if (arr==null || arr.length==0) return 0;
		if (arr[0][0] == 1) return 0;
		int[] dp = new int[arr[0].length];//cols
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
