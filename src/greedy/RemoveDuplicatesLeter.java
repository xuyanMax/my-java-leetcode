package greedy;

import java.util.HashMap;
import java.util.Map;

// https://discuss.leetcode.com/topic/31413/easy-to-understand-iterative-java-solution/16
// https://leetcode.com/problems/remove-duplicate-letters/#/description
/**
 * Given a string which contains only lowercase letters, 
 * remove duplicate letters so that every letter appear once and only once. 
 * You must make sure your result is the smallest in lexicographical order among all possible results.
 * 
 * 
 */
public class RemoveDuplicatesLeter {

	public static void main(String[] args) {
		
		
	}
	public static String solution(String s) {
		Map<Character, Integer> maps = new HashMap<>();
		// Store character's last appearance index
		for (int i = 0; i<s.length(); i++) 
			maps.put(s.charAt(i), i);	
		
		int n = maps.size();
		char[] result = new char[n];
		int right = findMinIndex(maps);
		int left = 0;
		for (int i=0; i<n; i++) {// #n characters in the result
			char minChar = 'z' + 1; // initially set it as the max lowercase character
			for (int j=left; j <= right; i++) {
				if (s.charAt(j) < minChar) { // find the min char within the left and right range
					left = j + 1;
					minChar = s.charAt(j);
				}
			}
			result[i] = minChar;
			
			/* remove the min from the maps so that we won't duplicate a character */
			maps.remove(minChar);
			
			/* The s.charAt(end) will be included
			 * If s.charAt(end) = minChar means it is included
			 * we further the next end boundary = findMinIndex(maps); 
			 * */
			
			if (s.charAt(right) == minChar) 
				right = findMinIndex(maps);
		}
		return "";
		
	}
	private static int findMinIndex(Map<Character, Integer> maps) {
		if (maps == null || maps.isEmpty())
			return -1;
		int max = 'z' + 1;
		for (int val : maps.values()) {
			if (val < max)
				max = val;
		}
		return max;
	}
	
	

}
