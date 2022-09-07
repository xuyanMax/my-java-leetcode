package dp.besttimetosellandbuystock;

/**
 * Created by xu on 30/12/2017.
 * at most 2 transactions
 */
public class BestTimeToBuyAndSellStock3 {
    //k的定义并不是「已进行的交易次数」，而是「最大交易次数的上限限制」。
    //如果确定今天进行一次交易，且要保证截至今天最大交易次数上限为k，那么昨天的最大交易次数上限必须是k - 1

    //dp[i][K][0/1]: 代表截止第i天，当前持有0/1只股票，最大交易(买+卖同一只股票属于一次交易)次数为K
    //交易次数及手持股票状态图 [0][0]-buy->[1][1]-sell->[1][0]-buy->[2][1]-sell->[2][0]

    // dp[i][k][0/1]: max profit at end of day i with at most k transactions and with 0/1 transaction in hand
    // dp[i][1][0] = Math.max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
    // dp[i][1][1] = Math.max(dp[i-1][1][1], dp[i-1][0][0] - prices[i]);
    // dp[i][2][0] = Math.max(dp[i-1][2][0], dp[i-1][2][1] - prices[i]);
    // dp[i][2][1] = Math.max(dp[i-1][2][1], dp[i-1][1][0] - prices[i]);
    public int maxProfit_k_is_2_simple(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int ti10 = 0;
        int ti11 = Integer.MIN_VALUE;
        int ti20 = 0;
        int ti21 = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            ti20 = Math.max(ti20, ti21 + prices[i]);
            ti21 = Math.max(ti21, ti10 - prices[i]);
            ti10 = Math.max(ti10, ti11 + prices[i]);
            ti11 = Math.max(ti11, -prices[i]);
        }
        return ti20;
    }

    int maxProfit_k_is_2(int[] prices) {
        int max_k = 2, n = prices.length;
        int[][][] dp = new int[n][max_k + 1][2];
        // k = 0 时的 base case
        for (int i = 0; i < n; i++) {
            dp[i][0][1] = Integer.MIN_VALUE;
            dp[i][0][0] = 0;
        }
        for (int i = 0; i < n; i++) {
            //dp[i][k]不会依赖dp[i][k - 1]，而是依赖dp[i - 1][k - 1]，对于dp[i - 1][...]，都是已经计算出来的。
            //所以不管你是k = max_k, k--，还是k = 1, k++，都是可以得出正确答案的。
            for (int k = max_k; k >= 1; k--) {
//                if (i - 1 == -1) {
//                    // 处理 base case
//                    dp[i][k][0] = 0;
//                    dp[i][k][1] = -prices[i];
//                    continue;
//                }
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }
        // 穷举了 n × max_k × 2 个状态，正确。
        return dp[n - 1][max_k][0];
    }

    int maxProfit_k_any(int max_k, int[] prices) {
        int n = prices.length;
        if (n <= 0) {
            return 0;
        }
        if (max_k > n / 2) {
            // 交易次数 k 没有限制的情况
            return BestTimeToBuyAndSellStock2.maxProfit_K_is_infinity(prices);
        }

        // base case：
        // dp[-1][...][0] = dp[...][0][0] = 0
        // dp[-1][...][1] = dp[...][0][1] = -infinity
        int[][][] dp = new int[n][max_k + 1][2];
        // k = 0 时的 base case
        for (int i = 0; i < n; i++) {
            dp[i][0][1] = -prices[0];
            dp[i][0][0] = 0;
        }

        for (int i = 0; i < n; i++)
            for (int k = max_k; k >= 1; k--) {
//                if (i - 1 == -1) {
//                    // 处理 i = -1 时的 base case
//                    dp[i][k][0] = 0;
//                    dp[i][k][1] = -prices[i];
//                    continue;
//                }
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        return dp[n - 1][max_k][0];
    }
}
