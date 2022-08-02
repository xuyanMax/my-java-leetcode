package a_OA;

public class SellStock {


    //只允许买卖一次
    public int maxProfit1(int[] prices) {
        if (prices.length == 0 || prices == null)
            return -1;
        int min = prices[0];
        int max_profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > min)
                max_profit = Math.max(max_profit, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return max_profit;

    }

    //允许买卖多次
    public int maxProfitMulti(int[] prices) {
        if (prices == null || prices.length == 0)
            return -1;
        int sum = 0;
        for (int i = 1; i < prices.length; i++)
            if (prices[i] > prices[i - 1])
                sum += prices[i] - prices[i - 1];
        return sum;

    }

    public int maxProfitMulti_DP(int[] prices) {
        //用dp[i][0]dp[i][0]dp[i][0]表示第i天不持股到该天为止的最大收益，dp[i][1]dp[i][1]dp[i][1]表示第i天持股，到该天为止的最大收益。
        int[][] dp = new int[prices.length][prices.length];
        //出示状态
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        //状态转移
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        //最后一天不持股，是最大收益，持股的状态，资产不会是最大收益
        return dp[prices.length - 1][0];
    }
    //dp[i][K][0/1]: 代表截止第i天，当前持有0/1只股票，交易(买+卖同一只股票属于一次交易)次数为K
    //交易次数及手持股票状态图 [0][0]-buy->[1][1]-sell->[1][0]-buy->[2][1]-sell->[2][0]
    public int maxProfitMaxTwoTransactions(int[] prices) {
        int[][][] dp = new int[prices.length][3][2];
        for (int i = 0; i <= 2; i++)
            dp[0][i][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][1][1] + prices[i]);
            dp[i][1][1] = Math.max(dp[i - 1][1][1], dp[i - 1][0][0] - prices[i]);
            dp[i][2][0] = Math.max(dp[i - 1][2][0], dp[i - 1][2][1] + prices[i]);
            dp[i][2][1] = Math.max(dp[i - 1][2][1], dp[i - 1][1][0] - prices[i]);
        }
        return dp[prices.length - 1][2][0];
    }

}

