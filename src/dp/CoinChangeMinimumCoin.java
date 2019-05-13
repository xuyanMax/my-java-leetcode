package dp;

import java.util.Arrays;

//https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/CoinChangingMinimumCoin.java
// Given a total and coins of certain denomination with INFINITY supply,
// what is the minimum number of coins it takes to form this total.

public class CoinChangeMinimumCoin {

    public int coinChange1d(int[] coins, int amount) {
        if (coins == null || coins.length == 0) return -1;
        if (amount == 0) return 0;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);

        dp[0] = 0;

        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j >= coins[i]) {
                    dp[j] = Math.min(dp[j - coins[i]] + 1, dp[j]);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public int coinChange2d(int[] coins, int amount) {
        if (coins == null || coins.length == 0) return -1;
        if (amount == 0) return 0;

        int[][] dp = new int[coins.length + 1][amount + 1];

        //初始化base很重要
        for (int i = 0; i <= amount; i++)
            dp[0][i] = amount + 1;
        //归零很重要
        dp[0][0] = 0;
        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j >= coins[i]) {// i+1包含很重要
                    dp[i + 1][j] = Math.min(dp[i + 1][j - coins[i]] + 1, dp[i][j]);
                } else {
                    dp[i + 1][j] = dp[i][j];//很重要
                }
            }
        }
        return dp[coins.length][amount] > amount ? -1 : dp[coins.length][amount];
    }


    public static int getMinCoins(int[] coins, int Total) {

        int[] dp = new int[Total + 1];
        int[] R = new int[Total + 1]; //index

        // initialize
        Arrays.fill(R, -1);
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;
        R[0] = 0;

        for (int j = 0; j < coins.length; j++) {
            for (int i = 1; i <= Total; i++) {
                if (i >= coins[j]) {
                    if (dp[i] > dp[i - coins[j]] + 1) {
                        dp[i] = dp[i - coins[j]] + 1;
                        R[i] = j; // record coins[j] helps to form valued i with dp[i] number of coins
                    }
                }
            }

        }
        printCoinCombination(R, coins);
        return dp[Total];
    }

    public static void printCoinCombination(int[] R, int[] coins) {

        int start = R.length - 1;
        if (R[start] == -1) {
            System.out.println("No solution");
            return;
        }
        while (start != 0) {
            int j = R[start];
            System.out.print(coins[j] + " ");
            start = start - coins[j];
        }
    }
}
