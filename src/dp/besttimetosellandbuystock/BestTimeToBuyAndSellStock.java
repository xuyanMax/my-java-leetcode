package dp.besttimetosellandbuystock;

//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/most-consistent-ways-of-dealing-with-the-series-of-stock-problems
public class BestTimeToBuyAndSellStock {

    // greedy algorithm
    public int maxProfit(int[] prices) {
        // minPrice is the minimum price from day 0 to day i.
        // And maxPro is the maximum profit we can get from day 0 to day i
        // How to get maxPro? Just get the larger one between current maxPro and prices[i] - minPrice.
        if (prices == null || prices.length == 0)
            return 0;
        int min = prices[0];
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(prices[i], min);
            max = Math.max(max, prices[i] - min);
        }
        return max;
    }


    // KadaneAlgorithm: max subarray problem
    // but if the interviewer twists the question slightly by giving the
    // difference arr of prices, ex: for {1, 7, 4, 11}, if he gives {0, 6, -3, 7},
    // you might end up being confused.
    // we will use the diff arr and find a contiguous sub-arr giving maximum profit

    public static int solution2(int[] prices) {
        if (prices.length == 0)
            return 0;

        int[] diff = new int[prices.length - 1];
        for (int i = 1; i < prices.length; i++)
            diff[i - 1] = prices[i] - prices[i - 1];

        int maxSoFar = 0;
        int maxEndHere = 0;

        for (int i = 0; i < diff.length; i++) {
            maxSoFar = Math.max(maxEndHere + diff[i], maxSoFar);
            maxEndHere = Math.max(0, maxEndHere + diff[i]);
        }
        return maxSoFar;

    }

}
