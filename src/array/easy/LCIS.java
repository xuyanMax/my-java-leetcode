package array.easy;

import java.util.Arrays;

/**
 * Created by xu on 16/09/2017.
 */
/*
674. Longest Continuous Increasing Subsequence
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
