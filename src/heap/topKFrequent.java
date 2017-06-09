package heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author xu
 * ven a non-empty array of integers, return the k most frequent elements.

	For example,
	Given [1,1,1,2,2,3] and k = 2, return [1,2].
	
	Note: 
	You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
	Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
	
	https://leetcode.com/problems/top-k-frequent-elements/#/description
 */

public class topKFrequent {

	public static void main(String[] args) {
		
		int[] nums = new int[] {1,2,3,4,5,5,6,6,6,6};
		int k = 3;
		System.out.println(topKFrequent(nums, k));
	}
	
	// O(n) time complexity
	public static List<Integer> topKFrequent(int[] nums, int k) {
		List<Integer> result = new ArrayList<>();
		Map<Integer, Integer> maps = new HashMap<>();
		int max = 0;
		for (int n : nums) { 
			maps.put(n, maps.getOrDefault(n, 0) + 1); 
			max = Math.max(max, maps.get(n));
		}
		
		List<Integer>[] listArr = new List[max + 1];
		
		for (Map.Entry<Integer, Integer> entry : maps.entrySet()) {
			if (listArr[entry.getValue()] == null) {
				listArr[entry.getValue()] = new ArrayList<>();
			}
			listArr[entry.getValue()].add(entry.getKey());
	
		}
		for (int i=listArr.length-1; i>=1 && result.size() < k; i--) {
			if (listArr[i] != null)
				result.addAll(listArr[i]);
		}
		
		
		return result.subList(0, k);
//		return result;
	}

}
