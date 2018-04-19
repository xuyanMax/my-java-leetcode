package backTracing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author xu
 * 
 * Generate all permutations of string in lexicographically sorted order where repetitions of 
 * characters is possible in string
 * 
 * references:
 * 
 * https://www.youtube.com/watch?v=nYFd7VHKyWQ&list=PLrmLmBdmIlpslxZUHHWmfOzNn6cA7jvyh
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/recursion/StringPermutation.java
 *
 */
public class StringPermutation {

	public static void main(String[] args) {
		permute("aabc".toCharArray());
	}
	/**
	 * recursive method 
	 * 
	 * CountMap: stores <Character, Integer>, e.g., AABBCC, [A:2,B:2,C:2];
	 * char[] stores all characters without repetition
	 * count[] stores the number of time a character appears in the string
	 *
	 */
	
	static List<String> permute(char[] input) {
		List<String> results = new ArrayList<>();
		
		Map<Character, Integer> countMap = new HashMap<Character, Integer>();

		// String s="edfba" -> abdef
		// s.toCharArray()
		// Arrays.sort();
		
		for (Character c: input){
			countMap.put(c, countMap.getOrDefault(c, 0) + 1);
		}

		char[] chars = new char[countMap.size()];
		int[] counts = new int[countMap.size()];
		int index=0;

		for (Map.Entry<Character, Integer> entry:countMap.entrySet()) {
			chars[index] = entry.getKey();
			counts[index] = entry.getValue();
			index++;
		}
		List<String> resultList = new ArrayList<>();

		// n: index of the to be built string [0-input.length-1] 
		int n = 0;
		char[] result = new char[input.length];
		permuteUtil(resultList, chars, counts, n, result);

		return results;
		
	}
	static void permuteUtil(List<String> resultList, char[] chars, int[]counts, int ind, char[] result){
		
				if (ind == result.length) {
					resultList.add(new String(result));
					return;// 退出递归，返回上一层
				}
				
				for (int i=0; i<chars.length;i++) {
					if(counts[i] != 0) {
						counts[i]--;
						result[ind] = chars[i];// result[indexResult++] will cause exception
						permuteUtil(resultList, chars, counts, ind+1, result);
						counts[i]++;// 从下层循环退出后，需要将已经减去的字母个数➕回来
					}
					else continue;
				}						
	}
}