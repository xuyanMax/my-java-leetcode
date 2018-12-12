package dp.besttimetosellandbuystock;

import java.util.Deque;
import java.util.LinkedList;

// https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/StockBuySellKTransactions.java
// https://www.youtube.com/watch?v=oDhu5uGq_ic&t=5s

public class StockBuySellKTransactions {

    /**
     * This is a slow solution but easier to understand
     * <p>
     * Time complexity - O(K * number of days^2)
     * <p>
     * T[i][j] = Math.max(T[i][j-1], Math.max(prices[j] - prices[m] + T[i-1][m])) where m is 0..j-1
     * not accepted by leetcode
     */
    public static int maxProfitSlowSolution(int[] prices, int K) {

        int[][] T = new int[K + 1][prices.length];

        for (int k = 1; k < K + 1; k++) {
            for (int j = 1; j < prices.length; j++) {
                int maxVal = 0;
                for (int m = 0; m < j; m++) {
                    int sum = prices[j] - prices[m] + T[k - 1][m];
                    maxVal = Math.max(maxVal, sum);
                }
                T[k][j] = Math.max(T[k][j - 1], maxVal);
            }
        }

        return T[K][prices.length - 1];

    }
    // This is a faster method which optimizes on slower method
    // Time complexity O(K * number of days)
    // T[i][j] = max(T[i][j-1], prices[j] + maxDiff)

    // prices[j] - prices[m] + T[i-1][m]

    // maxDiff = max(maxDiff, T[i-1][j]-prices[j])
    // not accepted by leetcode

    public static int maxProfitFastSolution(int[] prices, int K) {

        int[][] T = new int[K + 1][prices.length];

        if (K >= prices.length / 2)
            return quickSolve(prices);

        for (int k = 1; k < K + 1; k++) {
            int maxDiff = T[k - 1][0] - prices[0];//T[0][0]-prices[0]
            for (int j = 1; j < prices.length; j++) {
                T[k][j] = Math.max(T[k][j - 1], maxDiff + prices[j]);
                maxDiff = Math.max(maxDiff, T[k - 1][j] - prices[j]);
            }
        }
        return T[K][prices.length - 1];
    }

    public static void printActualSolution(int[][] T, int[] prices) {
        int i = T.length - 1;
        int j = T[0].length - 1;
        Deque<Integer> stack = new LinkedList<Integer>();
        while (true) {
            if (i == 0 || j == 0)
                break;
            if (T[i][j] == T[i][j - 1]) {
                j--;
            } else {
                stack.addFirst(j);
                int maxDiff = T[i][j] - prices[j];

                for (int k = j - 1; k >= 0; k--) {
                    if (maxDiff == T[i - 1][k] - prices[k]) {
                        i = i - 1;
                        j = k;
                        stack.add(k);
                        break;
                    }
                }
            }
        }
        while (!stack.isEmpty()) {
            System.out.println("Buy at " + stack.pollFirst());
            System.out.println("Sell at " + stack.pollFirst());
        }
    }

    //deal with corner cases
    private static int quickSolve(int[] prices) {
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++)
            // as long as there is a price gap, we gain a profit.
            if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
        return profit;
    }
}
