package heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author xu
 * 
 * ---Array---
 * 
 * use List.get(index) as the #character occurrences
 * 
 * Given a string, sort it in decreasing order based on the frequency of characters.

	Example 1:
	
	Input:
	"tree"
	
	Output:
	"eert"
	
	Explanation:
	'e' appears twice while 'r' and 't' both appear once.
	So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
	Example 2:
	
	Input:
	"cccaaa"
	
	Output:
	"cccaaa"
	
	Explanation:
	Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
	Note that "cacaca" is incorrect, as the same characters must be together.
	Example 3:
	
	Input:
	"Aabb"
	
	Output:
	"bbAa"
	
	Explanation:
	"bbaA" is also a valid answer, but "Aabb" is incorrect.
	Note that 'A' and 'a' are treated as two different characters.
	
 * hash table 
 * heap 
 * 
 * 
 * https://leetcode.com/problems/sort-characters-by-frequency/#/description
 * 
 * https://discuss.leetcode.com/topic/65947/o-n-easy-to-understand-java-solution
 */
public class SortCharsByFrequency {

	public static void main(String[] args) {
		System.out.println(frequencySort("treestsasdf"));

	}
	
	public static String frequencySort (String s) {
	
		Map<Character, Integer> maps = new HashMap<>();
		int maxCount = 0;
		for (char c : s.toCharArray()) { 
			maps.put(c, maps.getOrDefault(c, 0) + 1); 
			maxCount = Math.max(maxCount, maps.get(c));
		}
		
		List<Character>[] listArr = buildListArray(maps, maxCount);
		
		
		return buildString(listArr);
		
	}
	
	

	private static List<Character>[] buildListArray (Map<Character, Integer>maps, int maxCount) {
		List<Character>[] listArr = new List[maxCount + 1];
		
		for (Map.Entry<Character, Integer> entry : maps.entrySet()) {
			
			if (listArr[entry.getValue()] == null) { // if the #occurrences (entry.getValue()) does not occur previously, create a new ArrayList 
				listArr[entry.getValue()] = new ArrayList<>();
//				listArr[entry.getValue()].add(entry.getKey());
			}
//			else // characters with the same #occurrences are placed in the same listArr[i]; must not recreate an ArrayList, which will cover the previous
//				listArr[entry.getValue()].add(entry.getKey());
			
			listArr[entry.getValue()].add(entry.getKey());
				
		}
			
		return listArr;
	} 
	private static String buildString (List<Character>[] listArr) {
		StringBuilder sbBuilder = new StringBuilder();
		for (int i=listArr.length-1; i>=1; i--) {
//			Character c = listArr[i].get(0);
			List<Character> tmpList = listArr[i];
			if (tmpList != null) {
				for (Character c : tmpList)
					for (int num=0; num < i; num++) 
						sbBuilder.append(c);
			}
		}
		
		return sbBuilder.toString();
	}
	
	

}
