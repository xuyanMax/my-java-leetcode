package dp.besttimetosellandbuystock;

/**
 * Created by xu on 30/12/2017.
 * Buy MANY transactions but you cant buy on ith day if you sell on i-1 day
 */
public class BestTimeToBuyAndSellStockWithCoolDown {
    public int maxProfit_k_is_inf_with_cooldown(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        // tik0_pre
        // updateHighestHeightBtwLR tik0_pre
        int tik0 = 0, tik1 = -prices[0], tik0_pre = 0;
        for (int i = 1; i < prices.length; i++) {
            int tmp = tik0;
            tik0 = Math.max(tik0, tik1 + prices[i]);
            tik1 = Math.max(tik1, tik0_pre - prices[i]);
            tik0_pre = tmp;
        }
        return tik0;
    }

    /**
     * On any i-th day, we can buy, sell or cooldown
     * <p>
     * buy[i]: The maximum profit can be made if the first i days end with buy or wait.
     * E.g "buy, sell, buy" or "buy, cooldown, cooldown"
     * <p>
     * sell[i]: The maximum profit can be made if the first i days end with sell or wait.
     * E.g "buy, sell, buy, sell" or "buy, sell, cooldown, cooldown"
     * <p>
     * price: prices[i - 1], which is the stock price of the i-th day
     * <p>
     * To calculate sell[i]:
     * <p>
     * If we sell on the i-th day, the maximum profit is buy[i - 1] + price,
     * because we have to buy before we can sell.
     * <p>
     * If we cooldown on the i-th day, the maximum profit is same as sell[i - 1]
     * since we did not do anything on the i-th day.
     * So sell[i] is the larger one of (buy[i - 1] + price, sell[i - 1])
     * <p>
     * <p>
     * To calculate buy[i]:
     * If we buy on the i-th day, the maximum profit is sell[i - 2] - price,
     * because on the (i-1)th day we can only cooldown.
     * <p>
     * If we cooldown on the i-th day, the maximum profit is same as buy[i - 1]
     * since we did not do anything on the i-th day.
     * So sell[i] is the larger one of (sell[i - 2] - price, buy[i - 1])
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 1) {
            return 0;
        }

        int length = prices.length;
        // buy[i]: max profit if the first "i" days end with a "buy" day
        int[] buy = new int[length + 1];
        // buy[i]: max profit if the first "i" days end with a "sell" day
        int[] sell = new int[length + 1];

        buy[1] = -prices[0];

        for (int i = 2; i <= length; i++) {
            int price = prices[i - 1];
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - price);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + price);
        }

        // sell[length] >= buy[length]
        return sell[length];
    }
}
