package dp.besttimetosellandbuystock;

/**
 * Created by xu on 30/12/2017.
 */
/*
Your are given an arr of integers prices, for which the i-th element is the price of a given stock on day i;
and a non-negative integer fee representing a transaction fee.

You may complete as MANY transactions as you like, but you need to pay the transaction fee for each transaction. You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)

Return the maximum profit you can make.

Example 1:
Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
Output: 8
Explanation: The maximum profit can be achieved by:
Buying at prices[0] = 1
Selling at prices[3] = 8
Buying at prices[4] = 4
Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
Note:

0 < prices.length <= 50000.
0 < prices[i] < 50000.
0 <= fee < 50000.

*/
public class BestTimeToBuyAndSellStockWithTransactionFee {

    // dp[k][j] = Math.max(dp[k][j-1], dp[k][m] + prices[j] - prices[m] - fee)
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0) return 0;
        int tik0 = 0, tik1 = prices[0];
        for (int i=0;i<prices.length; i++) {
            int tmp = tik0;
            tik0 = Math.max(tik0, tik1 + prices[i]);
            tik1 = Math.max(tik1, tmp - prices[i] - fee);
        }

        return tik0;
    }
}
