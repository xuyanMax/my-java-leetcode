package dp;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 * 
 * @author xu
 * YouTuBe: https://www.youtube.com/watch?v=NnD96abizww
 *
 */

public class LongestCommonSubstring {
	
	public static void main(String args[]) {
	
		LongestCommonSubstring lcs = new LongestCommonSubstring();
		String str1 = "abcdaf";
		String str2 = "abcdf";
		int result = lcs.lcsDP(str1.toCharArray(), str2.toCharArray());
		System.out.println(result);
		
		
	}
	
	// recursive
	public int lcs(char str1[],char str2[],int len1, int len2){
        
		if (len1 == 0 || len2 == 0)
			return 0;
		if (str1[len1] == str2[len2]) {
			len1--;
			len2--;
			return 1 + lcs(str1, str2, len1, len2);
		} else 
			return Math.max(lcs(str1, str2, --len1, len2), lcs(str1, str2, len1, --len2));
    }
	
	public int lcsDP(char[] str1, char[] str2) {
		 /* Following steps build dp[m+1][n+1] in bottom up fashion. Note 
	      that dp[i][j] contains length of LCS of str1[0..i-1] and str2[0..j-1] */
		int[][] dp = new int[str1.length+1][str2.length+1];
	
		for(int i=0;i<=str1.length;i++)
			dp[i][0]=0;
		for(int j=0;j<=str2.length;j++)
			dp[0][j]=0;
		
		for(int i=1;i<=str1.length;i++)
			for(int j=1;j<=str2.length;j++) {
				if(str1[i-1]==str2[j-1])
					dp[i][j] = dp[i-1][j-1] + 1;
				else 
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
			}
		
		for (int[] row : dp) {
			for (int num : row )
				System.out.print(num+" ");
			System.out.println("");
		}
		printActualLCS(dp, str1, str2);
		
		/* L[m][n] contains length of LCS for X[0..n-1] and Y[0..m-1] */
		return dp[str1.length][str2.length];
 	}
	
	public void printActualLCS(int[][] dp, char[] str1, char[] str2) {
		int i = dp.length-1;
		int j = dp[0].length-1;
		Deque<Character> st = new ArrayDeque<Character>();
		while (true) {
			if(i==0 || j==0)
				break;
			if(str1[i-1]==str2[j-1]) {
				st.addFirst(str1[i-1]);
				i--;
				j--;
			}
			else if (dp[i][j] == dp[i-1][j]) {
				i--;
			} else if (dp[i][j] == dp[i][j-1]) {
				j--;
			}
		}
		
		Iterator<Character> iterator = st.iterator();
		while(iterator.hasNext()) {
			System.out.print( String.format("%4s", iterator.next()));
		}
		System.out.println("");
	}
	
	
}
