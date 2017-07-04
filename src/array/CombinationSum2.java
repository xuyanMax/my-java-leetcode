package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author xu
 * 
 * Given a collection of candidate numbers (C) with duplicates and a target number (T), \
 * 
 * find all UNIQUE combinations in C where the candidate numbers sums to T.
 * 
 * Each number in C may ONLY be used ONCE in the combination.
 * 
 * Note: 
 * 
 * All numbers (including target) will be positive integers. 
 * The solution set must not contain duplicate combinations.
 * 
 * For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, 
 * 
 * sorted set [1,1,2,5,6,7,10]
 * 
 * A solution set is: 
	[
	  [1, 7],
	  [1, 2, 5],
	  [2, 6],
	  [1, 1, 6]
	]

 * 
 * 
 * https://leetcode.com/problems/combination-sum-ii/#/description
 * 
 */
public class CombinationSum2 {

	public static void main(String[] args) {


	}
	static List<List<Integer>> combination2(int[] nums, int target) {
	    List<List<Integer>> list = new ArrayList<>();
	    Arrays.sort(nums);
	    backtrack(list, new ArrayList<>(), nums, target, 0);
	    return list;
	    
	}

	static void backtrack(List<List<Integer>> list, List<Integer> aResultList, int [] nums, int targetRemain, int left){
	    
		if(targetRemain < 0) 
			return;
	    
	    else if(targetRemain == 0) 
	    	list.add(new ArrayList<>(aResultList));
	    
	    else{
	    	
	        for(int pos = left; pos < nums.length; pos++){
	            
	        	if(pos > left && nums[pos] == nums[pos-1]) 
	        		continue; // skip duplicates
	            
	        	aResultList.add(nums[pos]);
	            
	        	backtrack(list, aResultList, nums, targetRemain - nums[pos], pos + 1);// pos + 1
	            
	        	aResultList.remove(aResultList.size() - 1); 
	        }
	    }
	} 

}
