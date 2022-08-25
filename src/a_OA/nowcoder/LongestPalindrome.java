package a_OA.nowcoder;

/**
 * 对于一个字符串，请设计一个高效算法，计算其中最长回文子串的长度。
 * <p>
 * 给定字符串A以及它的长度n，请返回最长回文子串的长度。
 * <p>
 * 测试样例：
 * "abc1234321ab",12
 * 返回：7
 */
public class LongestPalindrome {
    public int getLongestPalindrome(String A) {
        int[][] dp = new int[A.length()][A.length()];
        for (int i = 0; i < A.length(); i++)
            dp[i][i] = 1;
        int max = Integer.MIN_VALUE;
        String result = null;
        for (int LEN = 2; LEN <= A.length(); LEN++) {
            for (int i = 0; i <= A.length() - LEN; i++) {
                int j = i + LEN - 1;
                if (LEN == 2 && A.charAt(i) == A.charAt(j)) {
                    dp[i][j] = 2;
                } else if (A.charAt(i) == A.charAt(j)) {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
                if (max < dp[i][j]) {
                    max = dp[i][j];
                    result = new String(A.substring(i, j + 1));
                }
            }
        }
        return dp[0][A.length() - 1];
    }


}
