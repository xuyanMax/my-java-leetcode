package hashtable;

import java.util.HashMap;
import java.util.Map;

/*
 * https://leetcode.com/problems/subarray-sum-equals-k/#/description
 *
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
	
	Example 1:
	Input:nums = [1,1,1], k = 2
	Output: 2
 *	
 */
public class NumOfSubarraysSumEqualsK {

	public static void main(String[] args) {
	

	}
	/*
	 * calculate the current sum and save number of all seen PreSum to a HashMap
	 */
	public int sol(int[] arr, int K){
		int presum = 0;
		int result = 0;
		
		Map<Integer, Integer> maps = new HashMap<>();
		maps.put(0,1);
		
		for (int i=0; i<arr.length; i++) {
			presum += arr[i];
			if (maps.containsKey(presum - K)) 
				result += maps.get(presum - K);
			
			maps.put(presum, maps.getOrDefault(presum, 0) + 1);
		}
		return result;
	}
}
