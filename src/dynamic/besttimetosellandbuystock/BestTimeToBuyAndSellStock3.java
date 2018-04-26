package dynamic.besttimetosellandbuystock;

/**
 * Created by xu on 30/12/2017.
 */
//at most 2 transactions
public class BestTimeToBuyAndSellStock3 {
    // dynamic[i][k][0/1]: max profit at end of day i with atmost k transactions and with 0/1 transaction in hand
    // dynamic[i][1][0] = Math.max(dynamic[i-1][1][0], dynamic[i-1][1][1] + prices[i])
    // dynamic[i][2][0] = Math.max(dynamic[i-1][2][0], dynamic[i-1][1][1] - prices[i]);
    // dynamic[i][1][1] = Math.max(dynamic[i-1][1][1], dynamic[i-1][0][0] - prices[i]);
    // dynamic[i][2][1] = Math.max(dynamic[i-1][2][1], dynamic[i-1][1][0] - prices[i]);
    public int maxProfit(int[] prices) {
        if (prices==null||prices.length==0) return 0;
        int ti10 = 0;
        int ti11 = Integer.MIN_VALUE;
        int ti20 = 0;
        int ti21 = Integer.MIN_VALUE;
        for (int i=0; i<prices.length; i++) {
            ti20 = Math.max(ti20, ti21 + prices[i]);
            ti21 = Math.max(ti21, ti10 - prices[i]);
            ti10 = Math.max(ti10, ti11 + prices[i]);
            ti11 = Math.max(ti11, 0 - prices[i]);
        }
        return ti20;
    }
}
