package dp;

//https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/LongestPalindromicSubsequence.java
/**
 * Given a string find longest palindromic subsequence in this string.
 */
public class LongestPalindromeSubsequence {

	public static void main(String[] args) {
		int len = getLPS("agbdba");
		System.out.println(len);
		
	}
	// bottom up dp 
	// dp[i][j]: lgst palindromic substring of string str(i:j)
	// time complexity: O(n^2)
	// space complexityl: O(n^2)
	public static int getLPS(String str){
		int[][] dp = new int[str.length()][str.length()];
		char[] chars = str.toCharArray();
		
		// initialize dp[][] with substr-length 1 as 1 
		// one char is a length 1 palindrome
		
		for (int i=0; i<str.length();i++)
			dp[i][i] = 1;

		for (int L=2;L<=str.length();L++){
			for (int i=0; i<str.length() - L + 1; i++) {
				int j = i + L -1;
				if (L == 2 && chars[i] == chars[j]) {
					dp[i][j] = 2;
				}
				else if (chars[i] == chars[j]) {
					dp[i][j] = 2 + dp[i+1][j-1];
				}
				else {
					dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
				}
			}
		}
        return dp[0][str.length()-1];
		
	}
	public static void printResult(int[][] dp, char[] chars){
		int[] result = new int[dp[0][dp.length-1]];
		int i=0, j = dp[0][dp.length-1];
		int k=0;
	
		
	}
	

}
