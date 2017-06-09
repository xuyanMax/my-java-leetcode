package greedy;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/patching-array/#/description
// https://discuss.leetcode.com/topic/35494/solution-explanation/61
/**
 * 
 * @author xu
 * Given a sorted positive integer array nums and an integer n, 
 * add/patch elements to the array such that any number in range [1, n] inclusive 
 * can be formed by the sum of some elements in the array. 
 * Return the minimum number of patches required.
 * 
 */
public class PatchArray_124816 {

	public static void main(String[] args) {
		
		minPatch(new int[]{1,2,4,13},44);
//		minPatch(new int[]{1,2,3,9},20);
//		minPatch(new int[]{1,3,5,7,9},30);
	}
	
	public static int minPatch(int[] nums, int n){
		long miss = 1;
		int add = 0;
		int i=0;
		List<Long> result = new ArrayList<>();
		while (miss <= n) {
			if (i < nums.length && nums[i] <= miss) {
				miss += nums[i];
				i++;
			} else {
				result.add(miss);
				miss += miss;
				add++;
			}
		}
		for (long l : result)
			System.out.println(l);
		return add;
	}

}
