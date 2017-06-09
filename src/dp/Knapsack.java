package dp;

/**
 * Date 04/04/2014
 * @author Tushar Roy
 *
 * 0/1 Knapsack Problem - Given items of certain weights/values and maximum allowed weight
 * how to pick items to pick items from this set to maximize sum of value of items such that
 * sum of weights is less than or equal to maximum allowed weight.
 *
 * Time complexity - O(W*total items)
 *
 * Youtube link
 * Topdown DP - https://youtu.be/149WSzQ4E1g
 * Bottomup DP - https://youtu.be/8LusJS5-AGo
 *
 * References -
 * http://www.geeksforgeeks.org/dynamic-programming-set-10-0-1-knapsack-problem/
 * https://en.wikipedia.org/wiki/Knapsack_problem
 * 
 * 
 */
public class Knapsack {

	public static void main(String[] args) {
			Knapsack k = new Knapsack();
	        int val[] = {12,12,20};
	        int wt[] = {1,2,3};
	        int r = k.bottomUpDP(val, wt, 3);
	      //  int r1 = k.topDownRecursive(val, wt, 30);
	        System.out.println(r);
	      //  System.out.println(r1);
		

	}
	/**
	 * 
	 * Solve 0/1 knapsack in bottom up dynamic programming
	 */
	public int bottomUpDP(int[] val, int[] wt, int W) {
		// pad FIRST col and rows with zeros 
		int dp[][] = new int[wt.length + 1][W + 1];
		
		for(int i = 0;i <= val.length; i++) {
			for(int j = 0; j <= W; j++) {
				if(i == 0 || j == 0) { 
					dp[i][j]=0;
					continue;
				}
				
				if(j >= wt[i-1]) // i = 1 --> w[0] due to zeros padding 
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-wt[i-1]] + val[i-1]);
				 else 
					dp[i][j] = dp[i-1][j];
				
				
			}
		}
		for (int[] rows: dp) {
			for (int row : rows)
				System.out.print(row + " ");
			System.out.println("");
		}
		return dp[val.length][W]; 	
	}

}
