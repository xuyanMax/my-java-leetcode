package dp.besttimetosellandbuystock;

/**
 * Created by xu on 30/12/2017.
 */
public class BestTimeToBuyAndSellStock2 {

    // simple one pass
    // A+B+C=D
    public int maxProfit(int[] prices) {
        int maxSumSoFar = 0;
        for (int i = 1; i < prices.length; i++)
            maxSumSoFar += Math.max(0, prices[i] - prices[i - 1]);

        return maxSumSoFar;
    }

    //peak valley approach
    // A+B > C
    //totalProfits=sum(peak_i - valley_i)
    public int maxProfit_pv(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int i = 0;
        int peak = prices[0];
        int valley = prices[0];
        int totalProfit = 0;
        for (; i < prices.length - 1; i++) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1])
                i++;
            valley = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1])
                i++;
            peak = prices[i];
            totalProfit += peak - valley;
        }
        return totalProfit;
    }

    /**
     * https://discuss.leetcode.com/topic/107998/most-consistent-ways-of-dealing-with-the-series-of-stock-problems
     * t[i][k][0] = max(t[i-1][k][0], t[i-1][k][1] + prices[i]);
     * t[i][k][1] = max(t[i-1][k][1], t[i-1][k-1][0] - prices[i]);
     * t[i][k][0]：max profit at the end of i-th day with at most k trans and with 0 stock in hand
     * t[i][k][1]: max profit at the end of i-th day with at most k trans and with 1 stock in hand
     * k :-+ infinity so k-1 == k
     *
     * @param prices
     * @return
     */

    public static int maxProfit_K_is_infinity(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int tik0 = 0, tik1 = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            int tmp = tik0;
            tik0 = Math.max(tik0, tik1 + prices[i]);
            tik1 = Math.max(tik1, tmp - prices[i]);
        }
        return tik0;
    }

    // 改进：tmp其实不需要
    // 因tik0被更新后有两种可能
    // case 1 tik0
    // case 2 tik1 + prices[i]
    //如果是第一种，那么cache or not does not matter
    //如果是第二种，tik0 = tik1 + prices => tik1 > tik0 - prices
    //因此，不论cache 与否 tik1最终会被赋值tik1
    //结论：不需要cache

}
