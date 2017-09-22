package BackTracing;

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

public class PalindromePartition {

	public static void main(String[] args) {
		
		List<List<String>> result = partition("aab");
		
		printListList(result);
		
			
	}
	
	static List<List<String>> partition(String s) {
		
		List<List<String>> resultList = new ArrayList<>();
	
		partitionUtil(resultList, new ArrayList<>(), s, 0);
		return resultList;
	}
	
	static void partitionUtil(List<List<String>> resultList, List<String> aResult, String str, int pos) {
	
		if (pos == str.length()) {
			addToList(resultList, aResult);
			return;
		}
		
		for (int ipos = pos; ipos < str.length(); ipos++) {
			
			String builder = str.substring(pos, ipos + 1);
			
			if (isPalindrome(builder)) {
				
				aResult.add(new String(builder));
				
				partitionUtil(resultList, aResult, str, ipos + 1);
				
				aResult.remove(aResult.size() - 1);
				
			}
			else 
				continue;
		
		}
	}
	static void addToList(List<List<String>> resultList, List<String> aResult) {
		
		resultList.add(new ArrayList<>(aResult));
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
	
	static void printListList(List<List<String>> result) {

		Iterator<List<String>> it = result.iterator();
		while (it.hasNext()) {
			List<String> tmp = (List<String>) it.next();
			for (String s: tmp)
				System.out.print(s+" ");
			System.out.println("");
		}
	}

}
