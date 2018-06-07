package dp.besttimetosellandbuystock;

/**
 * Created by xu on 30/12/2017.
 */
/*
* Buy MANY transactions but you cant buy on ith day if you sell on i-1 day
* */
public class BestTimeToBuyAndSellStockWithCooldown {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        // tik0_pre
        // updateHighestHeightBtwLR tik0_pre
        int tik0 = 0, tik1 = prices[0], tik0_pre = 0;
        for (int i=1; i<prices.length; i++) {
            int tmp = tik0;
            tik0 = Math.max(tik0, tik1 + prices[i]);
            tik1 = Math.max(tik1, tik0_pre - prices[i]);
            tik0_pre = tmp;
        }
        return tik0;
    }
}
