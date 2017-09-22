package hashtable;

import java.util.TreeSet;

/*
Given an array of integers, find out whether there are two distinct indices i and j in the
array such that the absolute difference between nums[i] and nums[j] is at most t and the
absolute difference between i and j is at most k.

*/
public class ContainsDuplicates_3 {

	public static void main(String[] args) {
	
		TreeSet<Integer> treeSet = new TreeSet<>();
		treeSet.first();
		treeSet.floor(1);
		treeSet.ceiling(1);
		
	}
/**
 * This problem requires to maintain a window of size k of the previous values that can be queried for value ranges.
 *
 * The best data structure to do that is Binary Search Tree. As a result maintaining the tree of size k will
 *
 * result in time complexity O(N lg K). In order to check if there exists any value of range abs(nums[i] - nums[j]) to
 *
 * simple queries can be executed both of time complexity O(lg K)
 *
 * Here is the whole solution using TreeMap.
 */
	public boolean containsNearbyAtmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return false;
        }

        final TreeSet<Integer> set = new TreeSet<>();
        for (int ind = 0; ind < nums.length; ind++) {

            final Integer floor = set.floor(nums[ind] + t);
            final Integer ceil = set.ceiling(nums[ind] - t);
            if ((floor != null && floor >= nums[ind])
                    || (ceil != null && ceil <= nums[ind])) {
                return true;
            }

            set.add(nums[ind]);

            if (ind >= k) {
                set.remove(nums[ind - k]);
            }
        }

        return false;
    }
}
