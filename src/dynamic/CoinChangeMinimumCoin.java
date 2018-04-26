package dynamic;

import java.util.Arrays;

//https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/CoinChangingMinimumCoin.java
public class CoinChangeMinimumCoin {

	public static void main(String[] args) {
		int num = getMinCoins(new int[]{7,2,3,6}, 13);
		System.out.println("\n"+num);
	}
	/**
	 * Bottom up way of solving this problem
	 * 
	 */
	
	public static int getMinCoins(int[] coins, int Total) {
		
		int[] dp = new int[Total + 1];
		int[] R = new int[Total + 1]; //index

		// initialize 
		Arrays.fill(R, -1);
		Arrays.fill(dp, Integer.MAX_VALUE - 1);
		dp[0] = 0;
		R[0] = 0;

		for (int j=0; j<coins.length; j++) {
			for (int i=1; i<= Total;i++) {
				if (i >= coins[j]) {
					if (dp[i] > dp[i - coins[j]] + 1) {
						dp[i] = dp[i - coins[j]] + 1;
						R[i] = j; // record coins[j] helps to form valued i with dynamic[i] number of coins
					}
				}
			}
			
		}
//		for (int n : R)
//			System.out.println(n);
		printCoinCombination(R, coins);
		
		return dp[Total];
	}
	public static void printCoinCombination(int[] R, int[] coins){
		
		int start = R.length - 1;
		if (R[start] == -1) {
			System.out.println("No solution");
			return;
		}
			
		
		while (start != 0) {
			int j = R[start];
			System.out.print(coins[j] +" ");
			start = start - coins[j];
		}
	}
}
