package dp.easy;
/**
 * 279. Perfect Squares
 * Given a positive integer n,
 * find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 * Example 2:
 * <p>
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.min;

public class PerfectSquares {

    // static dp solution
    static List<Integer> res = new ArrayList<>();

    public int numSquares(int n) {
        if (res.size() == 0)
            res.add(0);

        while (res.size() <= n) {
            int m = res.size();
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= m; j++)
                min = min(min, res.get(m - j * j) + 1);
            res.add(min);
        }
        return res.get(n);
    }

    //完全背包，恰好装满背包的
    public int numSquaresSol2(int n) {
        int[] dp = new int[n + 1];// dp[i]: least number of squares to sum to i;

        //初始值的设定
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;

        for (int i = 1; i <= n; i++)//物品1, 2
            for (int j = 1; j * j <= i; j++)//容量为W
                dp[i] = min(dp[i], dp[i - j * j] + 1);

        return dp[n];
    }
}
