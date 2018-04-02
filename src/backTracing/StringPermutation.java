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
	 * 
	 */
	
	static List<String> permute(char[] input) {
		List<String> results = new ArrayList<>();
		
		Map<Character, Integer> countMap = new HashMap<Character, Integer>();

		// String s="edfba" -> abdef
		// s.toCharArray()
		// Arrays.sort();
		
		for (Character c: input){
			countMap.compute(c,(key,val) -> {
				if (val==null) // null if there is no current mapping
					return 1;
				else return val+1;
			});
		}
		char[] chars = new char[countMap.size()];
		int[] count = new int[countMap.size()];
		int index=0;
		for (Map.Entry<Character, Integer> entry:countMap.entrySet()) {
			chars[index] = entry.getKey();
			count[index] = entry.getValue();
			index++;
		}
		List<String> resultList = new ArrayList<>();

		// n: index of the to be built string [0-input.length-1] 
		int n = 0;
		char[] result = new char[input.length];
		permuteUtil(resultList, chars, count, n, result);				
		System.out.println(resultList);
		return results;
		
	}
	static void permuteUtil(List<String> resultList, char[] chars, int[]count, int indexResult, char[] result){
		
				if (indexResult==result.length) {
					resultList.add(new String(result));
					
					return;// 退出递归，返回上一层
				}
				
				for (int i=0; i<chars.length;i++) {
					if(count[i]!=0) {
						count[i]--;
						result[indexResult]=chars[i];// result[indexResult++] will cause exception
						
						permuteUtil(resultList, chars, count, indexResult+1, result);
						
						count[i]++;// 从下层循环退出后，需要将已经减去的字母个数➕回来
					}
					else continue;
				}						
	}
	static void printArray(char[] input) {
		for (char c:input)
			System.out.println(c);
	}

}