package array;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author xu
 * 
 * use hash map 
 * 
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

	For example,
	Given [100, 4, 200, 1, 3, 2],
	The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
	
	Your algorithm should run in O(n) complexity.
 * 
 * https://discuss.leetcode.com/topic/6148/my-really-simple-java-o-n-solution-accepted
 * https://leetcode.com/problems/longest-consecutive-sequence/#/description
 */
public class longestConsecutiveSequence {

	public static void main(String[] args) {
		int[] nums = new int[] {100,400,101,102,109,99,98};
		System.out.println(longestConsecutive(nums));
	}
	/**
	 * 
	 * we will use hash map. The key thing is to keep track of the sequence length and store that in the 
	 * 
	 * boundary points (left & right) of the sequence. For example, for sequence [1,2,3,4,5]. map.get(1) all the way to map.get(5)
	 * 
	 * should all return length of 5.
	 *      
	 */
	public static int longestConsecutive (int[] nums) {
		Map<Integer, Integer> map = new HashMap<>();
		int max = 0;
		
		for (int n : nums) {
			if (!map.containsKey(n)) {
				
				/*
				 * 1- see if n-1 and n+1 exist in the map, if so, it means there is an sequence next to n (right or/and left).
				 * 
				 * @left will be the length of the left sequence starting from somewhere to n-1, 
				 * @right will be the length of the right sequence starting from n+1 to somewhere else
				 * 
				 * if left == 0 || right == 0, meaning there is no sequence next to n and n will be a boundary
				 * 
				 * store (left + right + 1) to the map associated with the key = n
				 * 
				 * 2- use left and right to locate other two keys (ends of sequences wrt n ) and replace their values as the new length
				 *  
				 */
				
				int left = (map.containsKey(n-1)) ? map.get(n-1) : 0;
				
				int right = (map.containsKey(n+1)) ? map.get(n+1) : 0;
				int sum = left + right + 1;
				
				map.put(n, sum);
				max = Math.max(max, sum);
				
				// 2- 
				map.put(n - left, sum);
				map.put(n + right, sum);
				
				
			}
			else
				continue;
		}
		return max;
		
	}

}
