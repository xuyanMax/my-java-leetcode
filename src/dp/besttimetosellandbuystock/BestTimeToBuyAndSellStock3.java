package dp.besttimetosellandbuystock;

/**
 * Created by xu on 30/12/2017.
 * at most 2 transactions
 */
public class BestTimeToBuyAndSellStock3 {

    //dp[i][K][0/1]: 代表截止第i天，当前持有0/1只股票，交易(买+卖同一只股票属于一次交易)次数为K
    //交易次数及手持股票状态图 [0][0]-buy->[1][1]-sell->[1][0]-buy->[2][1]-sell->[2][0]

    // dp[i][k][0/1]: max profit at end of day i with at most k transactions and with 0/1 transaction in hand
    // dp[i][1][0] = Math.max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
    // dp[i][1][1] = Math.max(dp[i-1][1][1], dp[i-1][0][0] - prices[i]);
    // dp[i][2][0] = Math.max(dp[i-1][2][0], dp[i-1][2][1] - prices[i]);
    // dp[i][2][1] = Math.max(dp[i-1][2][1], dp[i-1][1][0] - prices[i]);
    public int maxProfit(int[] prices) {
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
}
