package dynamic.hard;
/*
* 132. Palindrome Partitioning II
DescriptionHintsSubmissionsDiscussSolution
Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
*/
public class PalindromePartition2 {
    public int minCut(String s) {
        if (s==null || s.length()==0) return 0;
        // palindrome[i][j]: string[i:j]是否为回文
        boolean[][]palindrome = new boolean[s.length()][s.length()];
        // dynamic[i]代表string[i:n-1]所需的min cut
        int[] dp = new int[s.length()];
        for (int i=s.length()-1; i>=0; i--) {
            dp[i] = s.length()-1-i;//初始化为可能的最大值
            for (int j=i; j<s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)){
                    if(j-i<=2) palindrome[i][j] = true; //xx or xyx
                    else {
                        palindrome[i][j] = palindrome[i+1][j-1];
                    }
                }
                if (palindrome[i][j]){
                    if (j == s.length()-1) dp[i] = 0;
                    else dp[i] = Math.min(dp[i], dp[j+1] + 1);
                }
            }
        }
        return dp[0];
    }
}
