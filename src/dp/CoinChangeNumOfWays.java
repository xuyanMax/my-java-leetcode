package dp;

public class CoinChangeNumOfWays {

	public static void main(String[] args) {
		

	}
	/*
	 * Keep the coins sorted
	 * 1,2,3 - total 5
	 */
	public static int getTotalWays(int[] coins, int N) {
		int[][] dp = new int[coins.length][N+1]; 
		
		// initialize 
		for (int i=0; i<coins.length; i++) 
			dp[i][0] = 1;
		for (int j=0; j<N+1; j++)
			dp[0][j] = 1;
		
		for (int i=0; i<coins.length; i++){
			for (int j=1; j<N+1; j++) {
				if (j >= coins[i]) {
					dp[i][j] = dp[i-1][j] + dp[i][j - coins[i]]; 
				}else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		return dp[coins.length-1][N];
		
	}

}
