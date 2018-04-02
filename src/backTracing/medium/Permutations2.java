package backTracing.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * 
 * @author xu
 * 47. Permutations II
 * Given a collection of numbers that might contain DUPLICATES, return all possible UNIQUE permutations.
 * 
 * for example [1 , 1, 2] A33 / A22 = 3*2*1 / (2*1) = 3
 * 	1 1 2
 * 	1 2 1
 * 	2 1 1
 * 
 * https://leetcode.com/problems/permutations-ii/#/description
 * 
 * answer is the same in backTracing -> StringCombination
 * 
 */
public class Permutations2 {

	/**
	 * Use an extra boolean arr " boolean[] used" to indicate whether the value is added to list since
	 * we may have duplicate input key
	 *
	 * so Collections.contains() does not work 
	 * 
	 * Sort the arr "int[] nums" to make sure we can skip the same value.
	 * 
	 * when a number has the same value with its previous, we can use this number only if his previous is used
	 */
	static List<List<Integer>> permuteUnique(int[] nums) {
	    
		List<List<Integer>> list = new ArrayList<>();

	    Arrays.sort(nums);
	    
	    backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
	    
	    return list;
	}

	static void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean [] used){
	    
		if(tempList.size() == nums.length){
	        list.add(new ArrayList<>(tempList));
	        return;
	    }
        for(int pos = 0; pos < nums.length; pos++){

            if (used[pos])
                continue;

            if(pos > 0 && nums[pos] == nums[pos-1] && !used[pos - 1])
                continue;

            used[pos] = true;

            tempList.add(nums[pos]);

            backtrack(list, tempList, nums, used);

            used[pos] = false;

            tempList.remove(tempList.size() - 1);
        }
	}
}
