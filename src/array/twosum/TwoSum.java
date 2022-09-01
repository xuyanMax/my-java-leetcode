package array.twosum;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Two Sum
 * https://leetcode.com/problems/two-sum/#/description
 * <p>
 * Given an arr of integers, return indices of the two numbers such that they add up to a specific target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * @author xu
 * <p>
 * - ARRAY
 * - HASHTABLE
 */
public class TwoSum {

    /*	 * O(n^2) time complexity
     * */
    int[] bruteforce(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++)
            for (int j = i + 1; j < arr.length; j++)
                if (arr[i] + arr[j] == target)
                    return new int[]{i, j};
        return new int[]{};
    }

    /*
     * O(n) time complexity
     * return indices
     *
     * */
    public int[] hash_sol(int[] arr, int target) {
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (index.containsKey(target - arr[i]) && i != index.get(target - arr[i]))
                return new int[]{i, index.get(target - arr[i])};
            index.put(arr[i], i);
        }
        return new int[]{-1, -1};
    }

}
