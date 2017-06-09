package array;

/**
 * 
 * @author xu
 * 
 * https://leetcode.com/problems/unique-paths/#/description
 * 
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * 
 * The robot can only move either DOWN or RIGHT at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * 
 * How many possible unique paths are there?
 * 
 */
public class uniquePaths {

	public static void main(String[] args) {
	

	}
	
	static int solution(int m, int n) {
        
        int[][] dp = new int[m][n];
        for(int i=0;i<m;i++) dp[i][0]=1;
        for(int j=0;j<n;j++) dp[0][j]=1;
        
        for (int i=1;i<m;i++){
            for(int j=1;j<n;j++) {
                dp[i][j] = dp[i][j-1] + dp[i-1][j];
            }
        }
        
        return dp[m-1][n-1];
	}

}
