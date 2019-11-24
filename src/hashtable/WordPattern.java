package hashtable;

/**
 * 
 * https://leetcode.com/problems/word-pattern/#/description
 * 
 * https://discuss.leetcode.com/topic/26339/8-lines-simple-java
 */
 
import java.util.HashMap;
import java.util.Map;

/**
 *
 Discuss (601)
 290. Word Pattern

 Given a pattern and a string str, find if str follows the same pattern.

 Here follow means a full match, such that there is a bijection between a letter in pattern
 and a non-empty word in str.

 Example 1:

 Input: pattern = "abba", str = "dog cat cat dog"
 Output: true
 Example 2:

 Input:pattern = "abba", str = "dog cat cat fish"
 Output: false
 Example 3:

 Input: pattern = "aaaa", str = "dog cat cat dog"
 Output: false
 Example 4:

 Input: pattern = "abba", str = "dog dog dog dog"
 Output: false
 Notes:
 You may assume pattern contains only lowercase letters, and str contains lowercase letters that may be separated by a single space.
 */
public class WordPattern {

	static boolean solution(String pattern, String str) {

		Map<Object, Integer> map = new HashMap<>();

		String[] strings = str.split(" ");

		if (strings.length != pattern.length())
			return false;

		for (Integer i = 0; i < strings.length; i++) {

			/*
			 * go through the pattern letters and words in parallel
			 * and compare the indexes where they last appeared.
			 */
			if (map.put(strings[i], i) != map.put(pattern.charAt(i), i)) {
				return false;
			}
		}

		return true;
	}

}
