package dp;

import java.util.HashMap;

/**
 * Date 04/04/2014
 * @author Tushar Roy
 *
 * 0/1 Knapsack Problem - Given items of certain weights/values and maximum allowed weight
 * how to pick items to pick items from this set to maximize sum of value of items such that
 * sum of weights is less than or equal to maximum allowed weight.
 *
 * 每一种item数量就一个
 *
 * Time complexity - O(W*total items)
 *
 * References -
 * http://www.geeksforgeeks.org/dynamic-programming-set-10-0-1-knapsack-problem/
 * 
 * 
 */
public class Knapsack01 {

	public static void main(String[] args) {
        HashMap map = new HashMap();
	}

	//空间复杂度 O(N*M)
	public int solution(int[] val, int[] wt, int W) {
		// pad FIRST col and rows with zeros 
		int dp[][] = new int[wt.length + 1][W + 1];

		for(int i = 1;i <= val.length; i++)  //物品个数
			for(int j = wt[i-1]; j <= W; j++)    //背包容量
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-wt[i-1]] + val[i-1]);

		return dp[val.length][W]; 	
	}

	// 空间复杂度O(W)
	public int solution2(int[] val, int[]wt, int W) {
		int[] dp = new int[W+1];
        int M = val.length;
		for (int i=1; i<=W; i++)
		    for (int j=M; j>=wt[i-1]; i--)
		            dp[j] = Math.max(dp[j], dp[j - wt[i-1]] + val[i-1]);
		return dp[W]; //输出最优解
	}

	//dd大牛模版
	public int algorithm (int[] wgt, int[] val, int W, int n) {
		int[] dp = new int[W+1];
//		dp[0] = 1;
		for (int i=1; i<=n; i++)
			zeroOnePack(wgt, val, W, n, dp, i);
		return dp[W];

	}
	public void zeroOnePack(int[] wgt, int[] val, int W, int n, int[]dp, int i){
		for (int j=W; j>=wgt[i-1]; j--)
			dp[j] = Math.max(dp[j], dp[j - wgt[i-1]] + val[i-1]);
	}

}
