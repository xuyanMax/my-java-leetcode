package array.medium;

import java.util.Arrays;

/**
 * Created by xu on 16/09/2017.
 * 673.
 * Given an unsorted array of integers, find the number of longest increasing subsequence.
 * <p>
 * Example 1:
 * Input: [1,3,5,4,7]
 * Output: 2
 * Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
 */
public class NumberLIS {
    // 声明两个数组来存储以当前元素为队尾的LIS的长度，和具有相同LIS长度的子序列个数
    // nums[i] > nums[j] j=1-i-1
    //   -如果当前长度dp[i]小于dp[j]+1:以nums[j]结尾的子序列加上nums[i]
    //        - 则更新以nums[i]为结尾的子序列长度dp[i] = dp[j] + 1
    //        - 同时更新以nums[i]为最长子序列的count个数 count[i] = count[j];
    //  -如果当前长度dp[i]等于dp[j]+1
    //        -则累加count[i] = count[i] + count[j]
    // 同时创建一个全局变量maxLen、cnt，不断更新maxLen
    //      -当maxLen 小于 dp[i] 更新maxLen = dp[i]并且改写cnt = count[i];
    //      -当maxLen == dp[i]时，累加cnt += count[i];
    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int[] dp = new int[nums.length]; // LIS ends with nums[i]
        int[] count = new int[nums.length]; // number of LIS ends with nums[i]

        //初始化
        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);

        int max_len = 1, res_cnt = 1; // 2,2,2,2,2

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    } else if (dp[i] == dp[j] + 1) {
                        count[i] = count[i] + count[j];
                    }
                }
            }
            // updateHighestHeightBtwLR
            if (max_len < dp[i]) {
                max_len = dp[i];
                res_cnt = count[i];
            } else if (max_len == dp[i]) {
                res_cnt = res_cnt + count[i];
            }

        }
        return res_cnt;
    }
}
