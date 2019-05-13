package dp;

import java.util.Arrays;

/**
 * @author xu
 * <p>
 * Given an arr, find the sequence from which the subsequence' element is sorted in increasing order,
 * and in which the subsequence is as long as possible.
 * <p>
 * DP solution:
 * initially dp[i] = 1;
 * if (nums[i] > nums[j])
 * dp[i] = max(dp[i], dp[j] + 1) where j in [0, i-1]
 * <p>
 * time complexity : O(N^2)
 * <p>
 * Example:
 * 3, 4, -1, 0, 6, 2, 3
 * - 1, 1,  1, 1, 1 ,1 ,1 (Intially set as 1)
 * - 1, 2,  1, 2, 3, 3, 4
 * <p>
 * trace-back
 * i=1:n
 * j = 1:i-1
 * if(nums[i] > nums[j])
 * if (dp[i] < dp[j] + 1)
 * -1 0  -1  2  3  3  5
 * <p>
 * return 4
 * <p>
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
     * DP
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
//					dp[i] = Math.max(dp[i], dp[j] + 1);
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

        /*
         * trace back the max increasing sub-sequence
         */
//		index = traceback[index];

        while (index != -1) {
            System.out.print(nums[index] + " ");
            index = traceback[index];
        }
        System.out.println("");
        return maxLen;
    }

    // 使用二分查找+动态规划方法
//	public int lengthOfLIS(int[] nums) {
//
//    }


}
