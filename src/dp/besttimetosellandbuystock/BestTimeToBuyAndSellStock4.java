package dp.besttimetosellandbuystock;
// Say you have an arr for which the ith element is the price of a given stock on day i.
// Design an algorithm to find the maximum profit. You may complete at most k transactions.
public class BestTimeToBuyAndSellStock4 {

    // dp[k][j] = Math.max(dp[k][j-1], dp[k-1][m]+Math.max(prices[j] - prices[m]) where i in 0:j-1;
    public int maxProfit(int [] prices, int K){
        if(prices == null || prices.length == 0) return 0;

        if (K>=prices.length/2) return quickSolve(prices);

        int[][] dp = new int[K+1][prices.length];

        int diff = 0;
        for (int i=1; i<=K; i++) {
            diff = dp[i-1][0] - prices[0];
            for (int j = 1; j < prices.length; j++) {
                dp[i][j] = Math.max(dp[i][j-1], prices[j] + diff);
                diff = Math.max(diff, dp[i-1][j] - prices[j]);
            }
        }
        return dp[K][prices.length-1];
    }

    //deal with corner cases
    private int quickSolve(int[] prices) {
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++)
            // as long as there is a price gap, we gain a profit.
            if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
        return profit;
    }
    //改进空间复杂度
    //K临界值:n/2，如果大于该值，则等同于K=infinity问题
    // dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k-1][1] + prices[i])
    // dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
    public int maxProfit2(int[] prices, int K) {
        if (prices == null || prices.length == 0) return 0;
        if (K>=prices.length>>>1) {
            int res = 0;
            for (int i=1; i<prices.length; i++)
                res += Math.max(0, prices[i]-prices[i-1]);
            return res;
        }else {

        }
        return  1;
    }


}
