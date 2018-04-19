package backTracing.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author xu
 * 
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets. // can change order

	Note: The solution set must not contain duplicate subsets.
	
	For example,
	If nums = [1,2,2], a solution is:
	
	[
	  [2],
	  [1],
	  [1,2,2],
	  [2,2],
	  [1,2],
	  []
	] 
 * 
 * https://leetcode.com/problems/subsets-ii/#/description
 * 
 * 
 */
public class Subsets2 {

	static List<List<Integer>> subsets2(int[] nums) {
		
		List<List<Integer>> resultList = new ArrayList<>();
		
		Arrays.sort(nums);// to avoid duplicates we sort the arr
		
		subsetsUtil2(resultList, new ArrayList<>(), nums, 0);
		
		return resultList;
	}
	
	static void subsetsUtil2(List<List<Integer>> resultList, List<Integer> aResult, int[] nums, int pos ) {
		
		
		resultList.add(new ArrayList<>(aResult));	
		
		for (int ipos = pos; ipos < nums.length; ipos++) {
			// key duplicate test
			if (ipos > pos && nums[ipos] == nums[ipos-1])
				continue;
			aResult.add(nums[ipos]);
			
			subsetsUtil2(resultList, aResult, nums, ipos + 1);
			
			aResult.remove(aResult.size() - 1);
		}
	}
	

}
