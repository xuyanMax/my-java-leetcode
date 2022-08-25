package dp.knapsack;

import java.util.Arrays;

/**
 * Date 04/04/2014
 *
 * @author Tushar Roy
 * <p>
 * 0/1 Knapsack Problem - Given items of certain weights/values and maximum allowed weight
 * how to pick items from this set to maximize sum of value of items such that
 * sum of weights is less than or equal to maximum allowed weight.
 * <p>
 * 每一种item数量就一个
 * <p>
 * Time complexity - O(W*total items)
 * <p>
 * References -
 * http://www.geeksforgeeks.org/dynamic-programming-set-10-0-1-knapsack-problem/
 */
public class Knapsack01 {

    //空间复杂度 O(N*M)
    public int solution(int[] val, int[] wt, int W) {
        // pad FIRST col and rows with zeros
        int dp[][] = new int[wt.length + 1][W + 1];

        for (int i = 1; i <= val.length; i++)  //物品个数
            for (int j = wt[i - 1]; j <= W; j++)    //背包容量
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - wt[i - 1]] + val[i - 1]);

        return dp[val.length][W];
    }

    // 空间复杂度O(W)

    /**
     * 状态定义：dp1[i]表示不考虑背包是否装满，在容量为i的情况下，最多装多大价值的物品。
     * 状态转移：遍历所有的物品，要么选择当前物品，要么不选，取价值最大的，并且通过这种方式跟新所有情况的状态。
     * 即dp1[j]=Math.max(dp1[j−v[i]]+w[i],dp1[j])
     *
     * @return
     */
    public int solution2(int[] val, int[] wt, int W) {
        int[] dp = new int[W + 1];
        int M = val.length;
        for (int i = 1; i <= M; i++)
            for (int j = W; j >= wt[i - 1]; j--)
                dp[j] = Math.max(dp[j], dp[j - wt[i - 1]] + val[i - 1]);
        return dp[W]; //输出最优解
    }

    /**
     * 状态定义：dp2[i]表示背包恰好装满时，在容量为i的情况下，最多装多大价值的物品。
     * 状态初始化：将背包容量为0的情况设置价值为0，其它情况设置为最小的Integer型整数，表示不可达状态。后续所有的状态都需要从为0的状态转移过去。
     */
    public int solution_exact(int[] weight, int[] val, int W) {
        int[] dp = new int[W + 1];
        //初始化不同 - 恰好装满和不强调恰好装满，区别在初始化。如果装不满，那么初始值设置为不可达，负无穷。
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= weight.length; i++)
            for (int j = W; j >= weight[i]; j--)
                dp[j] = Math.max(dp[j], dp[j - weight[i - 1]] + val[i - 1]);
        return dp[W];
    }

    //dd大牛模版
    public int algorithm(int[] wgt, int[] val, int W, int n) {
        int[] dp = new int[W + 1];
//		dp[0] = 1;
        for (int i = 1; i <= n; i++)
            zeroOnePack(wgt, val, W, n, dp, i);
        return dp[W];

    }

    public void zeroOnePack(int[] wgt, int[] val, int W, int n, int[] dp, int i) {
        for (int j = W; j >= wgt[i - 1]; j--)
            dp[j] = Math.max(dp[j], dp[j - wgt[i - 1]] + val[i - 1]);
    }


}
