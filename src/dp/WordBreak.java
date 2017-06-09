package dp;

import java.util.List;

/**
 * Created by xu on 2017/6/8.
 *
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words. You may assume the dictionary does not contain duplicate words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".
 */
public class WordBreak {


    /*dp[]:表示wordDict中是否存在-这样的substring
    * 存在 true
    * 不存在 false
    *
    * dp[0] = false;
    * str.substring(0,i+1);
    *
    * formula
    * dp[i+1] = dp[i+1-j] && wordDict.contains(str.substring(j,i+1)) where j in 0:i
    *
    * 返回
    * do[str.length];
    * */
    public boolean wordBreak(String str, List<String> wordDict) {

        boolean[] dp = new boolean[str.length() + 1];

        // dp[i] - whether substring [0:i] contains in wordDict List or not
        dp[0] = true; //empty string

        // There are only N - 1 ways of dividing a string into two parts, with N the length of the string.
        for (int i=0; i<str.length(); i++) {

            // check for the substring from [0:i]
            for (int j=0; j<i; j++) {// i, j的取值比较重要
                // both str[0:j-1] exist && str[j:i] exist then dp[i + 1] can be true;
                if (dp[j]==true && wordDict.contains(str.substring(j, i + 1))) {
                    dp[i + 1] = true;
                    break;
                }
            }
        }

        return dp[str.length()];
    }
    /*time complexity:
    * O(n*k)
    * */
    public boolean wordBreak_sol2(String str, List<String> wordDict) {
        boolean[] dp = new boolean[str.length() + 1];
        dp[0] = true;

        for (int i=1; i<=str.length(); i++) { // actual sub-length
            for (String seg : wordDict) {
                if (seg.length() <= i) {
                    if (dp[i - seg.length()] == true && str.substring(i-seg.length(), i).equals(seg)) {

                        dp[i] = true;
                        break;

                    }
                }
            }
        }
        return dp[str.length()];


    }
}
