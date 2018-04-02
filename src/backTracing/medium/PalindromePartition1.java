package backTracing.medium;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author xu
 * 
 * Given a string s, partition s such that every substring of the partition is a palindrome.

	Return all possible palindrome partitioning of s.
	
	For example, given s = "aab",
	Return
	
	[
	  ["aa","b"],
	  ["a","a","b"]
	]
	
 * https://leetcode.com/problems/palindrome-partitioning/#/description
 * 
 */

public class PalindromePartition1 {
	
	static List<List<String>> partition(String str) {
		
		List<List<String>> resultList = new ArrayList<>();
	
		partitionUtil(resultList, new ArrayList<>(), str, 0);
		return resultList;
	}
	
	static void partitionUtil(List<List<String>> res, List<String> aList, String str, int pos) {

		if (pos == str.length()) {
			res.add(new ArrayList<>(aList));
			return;
		}
		
		for (int ipos = pos; ipos < str.length(); ipos++) {
			
			String builder = str.substring(pos, ipos + 1);
			
			if (isPalindrome(builder)) {
				
				aList.add(new String(builder));
				
				partitionUtil(res, aList, str, ipos + 1);
				
				aList.remove(aList.size() - 1);
				
			}
		}
	}
	
	static boolean isPalindrome(String str) {
		int left = 0, right = str.length() - 1;
		
		if (left == right) return true;
		
		while (left < right) {
			if (str.charAt(left) == str.charAt(right)) {
				left++;
				right--;
			} else 
				return false;
		}
		
		return true;
	}
}
