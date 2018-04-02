package arr.easy;

import java.util.Arrays;

/**
 * Created by xu on 16/09/2017.
 */
/*
674. Longest Continuous Increasing SubSequence
Given an unsorted array of integers, find the length of longest continuous increasing subsequence (subarray).

Example 1:
Input: [1,3,5,4,7]
Output: 3
Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3.
Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4.
Example 2:
Input: [2,2,2,2,2]
Output: 1
Explanation: The longest continuous increasing subsequence is [2], its length is 1. 
*/
public class LCIS {

    // 额外空间 O(N)
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i=1; i<nums.length; i++) {
            if (nums[i] > nums[i-1])
                dp[i] = dp[i-1]+1;
        }
        int max = -1;
        for(int num:dp)
            max = Math.max(num, max);
        return max;
    }

    //额外空间O(1)
    public int findLengthOfLCIS_2(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int ret = 1;
        int count = 1;
        for (int i=1; i<nums.length; i++) {
            if (nums[i] > nums[i-1]) {
                count++;
                ret = Math.max(ret, count);
            }
            else
                count = 1;
        }
        return ret;
    }
}
