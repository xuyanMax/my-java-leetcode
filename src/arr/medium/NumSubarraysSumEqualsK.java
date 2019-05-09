package arr.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. Subarray Sum Equals K
 * Given an arr of integers and an integer k, you need to find the total number of continuous
 * subarrays whose sum equals to k.
 * <p>
 * Example 1:
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 * Note:
 * The length of the arr is in range [1, 20,000].
 * The range of numbers in the arr is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 * <p>
 * Solution 1. Brute force. We just need two loops (i, j) and test if SUM[i, j] = k.
 * Time complexity O(n^2), Space complexity O(1). I bet this solution will TLE.
 * <p>
 * Solution 2. From solution 1, we know the key to solve this problem is SUM[i, j].
 * So if we know SUM[0, i - 1] and SUM[0, j], then we can easily get SUM[i, j].
 * To achieve this, we just need to go through the arr, calculate the current sum and
 * save number of all seen PreSum to a HashMap. Time complexity O(n), Space complexity O(n).
 */
public class NumSubarraysSumEqualsK {
    // presum + hashmap
    public int solution(int[] nums, int target) {
        Map<Integer, Integer> presum = new HashMap<>();
        presum.put(0, 1);
        int sum = 0;
        int count = 0;

        for (int num : nums) {
            sum += num;
            if (presum.containsKey(sum - target))
                count = presum.get(sum - target);

            presum.put(sum, presum.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
