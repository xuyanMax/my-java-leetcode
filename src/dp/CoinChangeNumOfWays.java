package dp;

import java.util.Arrays;

/**
 * Given a total and coins of certain denominations find number of ways total
 * can be formed from coins assuming INFINITY supply of coins
 * Example 1:
 * <p>
 * Input: amount = 5, coins = [1, 2, 5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * Example 2:
 * <p>
 * Input: amount = 3, coins = [2]
 * Output: 0
 * Explanation: the amount of 3 cannot be made up just with coins of 2.
 * Example 3:
 * <p>
 * Input: amount = 10, coins = [10]
 * Output: 1
 */
public class CoinChangeNumOfWays {

    public int getTotalWays(int[] coins, int N) {
        if (N != 0 && (coins == null || coins.length == 0))
            return 0;
        if (N == 0) return 1;

        Arrays.sort(coins);

        int[][] dp = new int[coins.length + 1][N + 1];
        // initialize
        for (int i = 0; i < dp.length; i++)
            dp[i][0] = 1;
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (j >= coins[i - 1]) {
                    // 相加，而不是取max
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[coins.length][N];

    }

    public int getTotalWays2(int N, int[] coins) {
        if (N != 0 && (coins == null || coins.length == 0))
            return 0;
        if (N == 0) return 1;
        int[] dp = new int[N + 1];
        dp[0] = 1;

        Arrays.sort(coins);

        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= N; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[N];
    }

}
