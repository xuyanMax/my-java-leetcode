package dp.medium;

/**
 * @Author: xyx
 * @Date: 2019-01-20 19:07
 * @Version 1.0
 *
 * https://leetcode.com/problems/2-keys-keyboard/
 */
public class _2KeysKeyboard {
    public int minSteps(int n) {

        if (n == 0 || n == 1) return 0;
        int[] dp = new int[n + 1];

        // dp[i] = dp[j] + i/j;// 从j个到i和，需复制j（i/j-1）次，但是copyAll需要+1次，因此需要i/j次
        for (int i = 2; i <= n; i++) {
            dp[i] = i;// 单次复制‘A’需要复制i次得到i个A
            for (int j = 2; j <= n / 2; j++) {
                if (i % j == 0)
                    dp[i] = Math.min(dp[i], dp[j] + i / j);//min steps 是选取满足i%j==0，且j尽可能大的情形；因此如果逆序j=n/2:2
            }
        }
        return dp[n];


    }
}
