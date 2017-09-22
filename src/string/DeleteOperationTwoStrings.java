package string;

/**
 * Created by xu on 05/09/2017.
 */
/*

583. Delete Operation for Two Strings

Given two words word1 and word2, find the minimum number of steps required to make word1 and word2
the same, where in each step you can delete one character in either string.

Example 1:
Input: "sea", "eat"

Output: 2

Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".

Note:
The length of given words won't exceed 500.
Characters in given words can only be lower-case letters.

*/
public class DeleteOperationTwoStrings {
    //反向思考，找到最长连续子序列，然后用两个字符串长度减去子序列长度的两倍，即是答案
    public int minDistance(String word1, String word2) {
        int[][]dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i=1; i<=word1.length(); i++) {
            for(int j=1; j<=word2.length(); j++) {

                if (word1.charAt(i-1) == word2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1] + 1;
                else
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);

            }
        }

        int max = dp[word1.length()][word2.length()];
        return word1.length() + word2.length() - 2*max;
    }
}
