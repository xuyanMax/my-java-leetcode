package hashtable;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author xu
 * 
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/#/description
 * https://discuss.leetcode.com/topic/64434/shortest-concise-java-o-n-sliding-window-solution
 * https://discuss.leetcode.com/topic/30941/here-is-a-10-line-template-that-can-solve-most-substring-problems/53
 */
public class FindAllAnagramsInAString {

	public static void main(String[] args) {
		String str = "cbaebabacd";
		String p = "abc";
		System.out.println(solution(str, p));

	}
	static List<Integer> solution(String s, String p) {
		if (s==null || p==null || s.length()==0 || p.length()==0)
			return null;
		List<Integer> result = new ArrayList<>();
		int left=0, right=0;
		int[] hash = new int[256];
		
		for (char c : p.toCharArray()) 
			hash[c]++;
		
		int count = p.length();
		 while (right < s.length()) {
		        //move right everytime, if the character exists in p's hash, decrease the count
		        //current hash value >= 1 means the character is existing in p
		        if (hash[s.charAt(right)] >= 1) {
		            count--;
		        }
		        hash[s.charAt(right)]--;
		        right++;
		        
		        //when the count is down to 0, means we found the right anagram
		        //then add window's left to result list
		        if (count == 0) {
		            result.add(left);
		        }
		        //if we find the window's size equals to p, then we have to move left (narrow the window) to find the new match window
		        //++ to reset the hash because we kicked out the left
		        //only increase the count if the character is in p
		        //the count >= 0 indicate it was original in the hash, cuz it won't go below 0
		        if (right - left == p.length() ) {
		           
		            if (hash[s.charAt(left)] >= 0) {
		                count++;
		            }
		            hash[s.charAt(left)]++;
		            left++;
		        
		        }

		        
		    }
		        return result;
	}

}
