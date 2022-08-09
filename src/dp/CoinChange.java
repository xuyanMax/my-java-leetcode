package dp;

import java.util.Arrays;

//https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/CoinChangingMinimumCoin.java
// Given a total and coins of certain denomination with INFINITY supply,
// what is the minimum number of coins it takes to form this total.
// 322. Coin Change
//      Constraints:
//        1 <= coins.length <= 12
//        1 <= coins[i] <= 231 - 1
//        0 <= amount <= 104
public class CoinChange {

    // 递归算法求解
    //dfs(amount) - 代表当前的目标金额是amount，至少需要dfs(amount)个硬币凑出该金额
    //时间复杂度 O(n^k * k); n=amount
    public int dfs_bruteforce(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;

        int ret = amount + 1;
        for (int coin : coins) {
            int sub_ret = dfs_bruteforce(coins, amount - coin);
            if (sub_ret == -1) continue;
            ret = Math.min(sub_ret + 1, ret);
        }
        return ret != amount + 1 ? ret : -1;
    }

    //递归算法 + memo备忘录, 消除重叠子问题
    int[] memo;

    //时间复杂度=子问题个数*子问题处理时间=O(n*k)
    public int dfs_memo(int[] coins, int amount) {
        memo = new int[amount + 1];
        return dfs_memo_helper(coins, amount);
    }

    public int dfs_memo_helper(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        int res = amount + 1;
        for (int i = 0; i < coins.length; i++) {
            int sub_ret = dfs_memo_helper(coins, amount - coins[i]);
            if (sub_ret == -1) continue;
            res = Math.min(res, sub_ret);
        }
        memo[amount] = res == amount + 1 ? -1 : res;
        return memo[amount];

    }

    // 动态规划数组
    //coins are sorted list.
    public int coinChange1d(int[] coins, int amount) {
        if (coins == null || coins.length == 0) return -1;
        if (amount == 0) return 0;
        if (amount < 0) return -1;

        // 对于总数amount，最多需要amount个1元组成，初始化成amount+1,相当于正无穷，便于后续取最小值
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);

        dp[0] = 0;
        //Arrays.sort(coins);
        for (int i = 0; i < dp.length; i++)
            for (int j = coins[i]; j <= amount; j++)
                dp[j] = Math.min(dp[j - coins[i]] + 1, dp[j]);


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
        Arrays.sort(coins);
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j >= coins[i - 1]) {//
                    dp[i][j] = Math.min(dp[i][j - coins[i - 1]] + 1, dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];//很重要
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
