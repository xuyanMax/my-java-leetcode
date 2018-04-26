package dynamic;
/*
5. Longest Palindromic Substring
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length
of s is 1000.

Example:

Input: "babad"

Output: "bab"

Note: "aba" is also a valid answer.
Example:

Input: "cbbd"

Output: "bb"

详解:
https://leetcode.com/problems/longest-palindromic-substring/solution/

*/
public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        if( s == null || s.length() == 0)
            return null;
        boolean[][] dp = new boolean[s.length()][s.length()];
        String ret = "";
        for (int i=s.length()-1; i>=0; i--) {
            for (int j=i; j<s.length(); j++) {

                dp[i][j] = s.charAt(i) == s.charAt(j) && ((j - i) < 3 || dp[i+1][j-1]);

                if (dp[i][j] && (ret == null || ret.length() < (j-i+1)) )
                    ret = s.substring(i, j+1);

            }
        }
        return ret;
    }

    // https://leetcode.com/problems/longest-palindromic-substring/solution/#approach-3-dynamic-programming-accepted
    public String longestPalindrome_2(String s) {
        if (s == null || s.length() == 0)
            return null;

        int start = 0, end = 0;//字符串索引

        for (int i=0; i<s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);// odd length;
            int len2 = expandAroundCenter(s, i, i + 1);// even length
            int max = Math.max(len1, len2);//回文的长度
            if (max > (end - start + 1)) {
                end = i + max/2;//end: 加一半回文长度
                start = i - (max-1)/2;// start: max为奇数，减少 (max/2)即可; 偶数，减(max/2)再加1
            }
        }
        return s.substring(start, end + 1);

    }
    private int expandAroundCenter(String s, int left, int right) {

        int L = left, R = right;
        while (L>=0 && R<s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        //恢复正常的位置
        L++;
        R--;
        return R - L +1;
    }
    }
