package dp;

/**
 * 5. Longest Palindromic Substring
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length
 * of s is 1000.
 * <p>
 * Example:
 * <p>
 * Input: "babad"
 * <p>
 * Output: "bab"
 * <p>
 * Note: "aba" is also a valid answer.
 * Example:
 * <p>
 * Input: "cbbd"
 * <p>
 * Output: "bb"
 * <p>
 * 详解:
 * https://leetcode.com/problems/longest-palindromic-substring/solution/
 */
public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0)
            return null;
        boolean[][] dp = new boolean[s.length()][s.length()];
        //dp初始化
        for (int i = 0; i < s.length(); i++)
            dp[i][i] = true;
        for (int i = 0; i < s.length() - 1; i++)
            dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
        int maxLen = 1;
        int start = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && ((j - i) < 3 || dp[i + 1][j - 1]);
                if (dp[i][j] && (maxLen < (j - i + 1))) {
                    start = i;
                    maxLen = j - i + 1;
                }
            }
        }
        return s.substring(start, maxLen + 1);
    }

    // https://leetcode.com/problems/longest-palindromic-substring/solution/#approach-3-dynamic-programming-accepted
    public String longestPalindrome_2(String s) {
        if (s == null || s.length() == 0)
            return null;

        int start = 0, end = 0;//字符串索引

        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);// odd length;
            int len2 = expandAroundCenter(s, i, i + 1);// even length
            int max = Math.max(len1, len2);//回文的长度
            if (max > (end - start + 1)) {
                end = i + max / 2;//end: 加一半回文长度
                start = i - (max - 1) / 2;// start: max为奇数，减少 (max/2)即可; 偶数，减(max/2)再加1
            }
        }
        return s.substring(start, end + 1);

    }

    private int expandAroundCenter(String s, int left, int right) {

        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        //恢复正常的位置
        L++;
        R--;
        return R - L + 1;
    }

    // bottom up dp
    // dp[i][j]: lgst palindromic sub-sequence of string str(i:j)
    // time complexity: O(n^2)
    // space complexity: O(n^2)
    public static int getLPS(String str) {
        int[][] dp = new int[str.length()][str.length()];
        char[] chars = str.toCharArray();

        // initialize dp[][] with substr-length 1 as 1
        // one char is a length 1 palindrome

        for (int i = 0; i < str.length(); i++)
            dp[i][i] = 1;

        for (int L = 2; L <= str.length(); L++) {
            for (int i = 0; i < str.length() - L + 1; i++) {
                int j = i + L - 1;
                if (L == 2 && chars[i] == chars[j]) {
                    dp[i][j] = 2;
                } else if (chars[i] == chars[j]) {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][str.length() - 1];

    }

}
