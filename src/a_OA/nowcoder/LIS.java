package a_OA.nowcoder;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * <p>
 * Example:
 * <p>
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Note:
 * <p>
 * There may be more than one LIS combination, it is only necessary for you to return the length.
 * Your algorithm should run in O(n2) complexity.
 * Follow up: Could you improve it to O(n log n) time complexity?
 */
public class LIS {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        // dp[i]: lis ending with nums[i]
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        int max = Integer.MIN_VALUE;
        for (int num : dp)
            max = Math.max(num, max);
        return max;
    }

    public ArrayList<Integer> lengthOfLIS_backtrace(int[] nums) {
        if (nums == null || nums.length == 0)
            return new ArrayList<>();
        // dp[i]: lis ending with nums[i]
        ArrayList<Integer> res = new ArrayList<>();

        int[] dp = new int[nums.length];
        int[] backtrace = new int[dp.length];

        Arrays.fill(dp, 1);
        Arrays.fill(backtrace, -1);

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        backtrace[i] = j;
                    }
                }
            }
        }
        int max = Integer.MIN_VALUE;
        int index = -1;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > max) {
                max = dp[i];
                index = i;
            }
        }
        while (index != -1) {
            res.add(nums[index]);
            index = backtrace[index];
        }

        return res;
    }

    public int nlgn(int[] arr) {
        int[] sortedArray = new int[arr.length];
        int maxLen = 0;
        for (int num : arr) {
            int ind = Arrays.binarySearch(sortedArray, 0, maxLen, num);
            ind = ind < 0 ? ~ind : ind;
            sortedArray[ind] = num;
            if (ind == maxLen) maxLen++;
        }
        return maxLen;

    }
}
