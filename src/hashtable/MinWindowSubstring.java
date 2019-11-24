package hashtable;

import java.util.LinkedList;

/**
 * 
 * @author xu
 * 
 * Given a string S and a string T, find the minimum window in S
 * which will contain all the characters in T in complexity O(n).

	For example,
	S = "ADOBECODEBANC"
	T = "ABC"
	Minimum window is "BANC".
	
	Note:
	If there is no such window in S that covers all characters in T, return the empty string "".
	
	If there are multiple such windows, you are guaranteed that there will always be only one unique
	minimum window in S.
	
	https://leetcode.com/problems/minimum-window-substring/#/description
	https://discuss.leetcode.com/topic/30941/here-is-a-10-line-template-that-can-solve-most-substring-problems/52
 */
public class MinWindowSubstring {

	public static void main(String[] args) {
		int[] nums = new int[]{2,3,1,2,4,3};
		System.out.println(minWindow("ADOBECODEBANC", "ABC"));
        LinkedList<String> list = new LinkedList<>();
    }
	
	/*
	 * use HASH  + two pointers to solve substring questions.
	 * 
	 * Left and Right pointers are to represent a window
	 * 
	 * move Right pointer to find a VALID window
	 * 
	 * If a valid window is found, move Left pointer to shrink the size of such a valid window
	 * 
	 * Use a global parameter minLen to hold the minimum length of a valid window
	 * 
	 * Finally, use String.substring(?, ? + minLen) to return the substring
	 * 
	 */
	
	public static String minWindow(String S, String T) {
		
		int[] maps = new int [256];
		//添加T 字符记录
		for (char c : T.toCharArray()) maps[c]++;
		
		int left = 0, right = 0, minLeft = 0, minLen = Integer.MAX_VALUE;
		int count = T.length();
		while (right < S.length()) {

			if (maps[S.charAt(right)] > 0) 
				count--;
			
			maps[S.charAt(right++)]--;

			while (count == 0) {
				if (right - left < minLen) {
					minLen = right - left;
					minLeft = left;
				}
				
				maps[S.charAt(left)]++;
				
				if (maps[S.charAt(left)] > 0) // 不是关键字的字符 会由 -1 -> 0 
					count++;
				
				left++;
			}
			
		}
		return minLen == Integer.MAX_VALUE?  "" : S.substring(minLeft, minLeft + minLen);
	}
}
