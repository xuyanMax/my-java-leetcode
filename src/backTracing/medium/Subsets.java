package backTracing.medium;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author xu
 * 78. Subsets
 * Given a set of distinct integers, nums, return all possible subsets.

	Note: The solution set must not contain duplicate subsets.
	
	For example,
	If nums = [1,2,3], a solution is:
	
	[
	  [3],
	  [1],
	  [2],
	  [1,2,3],
	  [1,3],
	  [2,3], 
	  [1,2],
	  []
	]
 * 
 * https://leetcode.com/problems/subsets/#/description
 */

public class Subsets {
	
	static List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> resultList = new ArrayList<>();
		
		subsetsUtil(resultList, new ArrayList<>(), nums, 0);
		return resultList;
	}
	
	static void subsetsUtil(List<List<Integer>> resultList, List<Integer> aResult, int[] nums, int pos ) {
		
		// subset
		resultList.add(new ArrayList<>(aResult));	
		
		for (int ipos = pos; ipos < nums.length; ipos++) {

			// add elements to a results list
			aResult.add(nums[ipos]);
			
			subsetsUtil(resultList, aResult, nums, ipos + 1);
			
			aResult.remove(aResult.size() - 1);//
		}
	}
}
