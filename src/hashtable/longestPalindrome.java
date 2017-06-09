package hashtable;

import java.util.HashSet;
import java.util.Set;
/**
 * 
 * @author xu
 *
 * https://leetcode.com/problems/longest-palindrome/#/description
 */
public class longestPalindrome {

	public static void main(String[] args) {
		

	}
	static int solution(String s){
		Set<Character> set = new HashSet<>();
		int count = 0;
		for (char c : s.toCharArray()) {
			if (set.contains(c)) {
				count++;
				set.remove(c);
			}
			else 
				set.add(c);
				
		}
		if (!set.isEmpty()) return count * 2 + 1;
		else 
			return count*2;
		
		
	}

}
