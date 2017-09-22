package hashtable;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author xu
 * Given an array of integers and an integer k, find out whether there are 
 * 
 * two distinct indices i and j in the array such that nums[i] = nums[j] 
 * 
 * and the absolute difference between i and j is at most k.
 * 
 * https://leetcode.com/problems/contains-duplicate-ii/#/description
 * 
 */
public class ContainsDuplicates_2 {

	public static void main(String[] args) {

			
			
	}
/**
 * iterates over the arr using a sliding window. The front of the window is RIGHT, the rear of window is k steps back.
 * The element to be removed from the window is right-k-1 if right > k;
 * The elements within the window are maintained using Set. While adding new element to the set, if add() fails, it means
 * found a duplicate, return true;
 *
 */
	static boolean solution(int[] nums, int k) {
		
		Set<Integer> set = new HashSet<>();
		int right = 0;
		while (right < nums.length) {
			if (right > k)
				set.remove(nums[right - k - 1]);
			
			if (!set.add(nums[right]))
				return true;
			right++;
		}
		
		return false;
	}
}
