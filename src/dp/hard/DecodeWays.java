package dp.hard;

/**
 * @Author: xyx
 * @Date: 2019-04-23 21:10
 * @Version 1.0
 * <p>
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 * <p>
 * Example 1:
 * <p>
 * Input: "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 * Example 2:
 * <p>
 * Input: "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 */
public class DecodeWays {
    // dp[n+1]
    // dp[i]: number of ways to decode s.sub(i,end+1);
    // dp[i] = dp[i+1]+dp[i+1] if s.charAt(i)!='0';
    // dp[n] = 1; empty string only 1 decoding way
    // dp[n-1] = s.charAt(n-1)=='0'?0:1;如果最后一位是0，那么不能产生任何答案，如果非0，一种答案
    public int numDecodings(String s) {
        if (s.length() == 0) return 0;
        int[] dp = new int[s.length() + 1];
        dp[s.length()] = 1;
        dp[s.length() - 1] = s.charAt(s.length() - 1) == '0' ? 0 : 1;
        for (int i = s.length() - 2; i >= 0; i--) {
            if (s.charAt(i) == '0') continue;
            if (Integer.parseInt(s.substring(i, i + 2)) <= 26)
                dp[i] = dp[i + 1] + dp[i + 2];// 23*** = "2" + "3**" 或者"23" + "***"
            else dp[i] = dp[i + 1];
        }
        return dp[0];

    }
}
