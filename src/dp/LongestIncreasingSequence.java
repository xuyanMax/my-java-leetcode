package dp;

import java.util.Arrays;

/**
 * @author xu
 * <p>
 * Given an arr, find the sequence from which the subsequence' element is sorted in increasing order,
 * and in which the subsequence is as long as possible.
 * <p>
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/LongestIncreasingSubsequence.java
 */
public class LongestIncreasingSequence {

    public static void main(String[] args) {

        LongestIncreasingSequence lISequence = new LongestIncreasingSequence();
        int max = lISequence.dp_solution(new int[]{3, 4, -1, 0, 6, 2, 3});
        System.out.println(max);

    }

    /**
     * DP solution:
     * initially dp[i] = 1;
     * if (nums[i] > nums[j])
     *  - dp[i] = max(dp[i], dp[j] + 1) where j in [0, i-1]
     *
     * time complexity : O(N^2)
     *
     * @return max length of LIS
     */
    public int dp_solution(int[] nums) {
        int[] dp = new int[nums.length];

        // store index of the next smaller number before current
        int[] traceback = new int[nums.length];
        Arrays.fill(dp, 1);

        Arrays.fill(traceback, -1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        traceback[i] = j;
                    }
                }
            }
        }
        // find the max length
        int maxLen = Integer.MIN_VALUE;
        int index = -1;
        for (int n = 0; n < dp.length; n++)
            if (dp[n] > maxLen) {
                maxLen = dp[n];
                index = n;
            }

        while (index != -1) {
            System.out.print(nums[index] + " ");
            index = traceback[index];
        }
        return maxLen;
    }
}
