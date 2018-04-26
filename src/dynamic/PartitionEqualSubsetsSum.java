package dynamic;

/**
 * Created by xu on 11/08/2017.
 */
/*
416.

Given a non-empty arr containing only positive integers, find if the arr can be partitioned into
two subsets such that the sum of elements in both subsets is equal.

Note:
Each of the arr element will not exceed 100.
The arr size will not exceed 200.

Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The arr can be partitioned as [1, 5, 5] and [11].

Example 2:

Input: [1, 2, 3, 5]

Output: false

Explanation: The arr cannot be partitioned into equal sum subsets.

*/
public class PartitionEqualSubsetsSum {

    // 这个问题的实质是0/1背包问题，即该集合内，是否存在一些数字的和为指定的target值
    // dynamic[nums.length][target]; dynamic[i][j]表示，是否能够得到：和为"j" - 从前i个数字中取得。如果可以，则标记true，否则false
    // 初始化
    // relation

    // dynamic[i][j] = dynamic[i-1][j] || dynamic[i-1][j - nums[i]]

    //  1. 如果不选择nums[i], 则dp[i][j] = dynamic[i-1][j], meaning the first i-1 elements would make j or not
    //  2. 如果选择nums[i], 则dp[i][j] = dynamic[i-1][j-nums[i]], meaning first i-1 elements would make j-nums[i] or not
    public boolean canPartition_knapsack(int[] nums){
        if (nums == null || nums.length == 0)
            return false;
        int sum = 0;
        for (int num:nums)
            sum += num;
        if (sum % 2 != 0)
            return false;

        int target = sum/2;
        boolean[][] dp = new boolean[nums.length+1][target+1];

        //初始化两个边界部分
        dp[0][0] = true;

        //从 0 个数中选择组合为i（1:n），false
        for (int i=1; i<=target; i++)
            dp[0][i] = false;

        // 从前i个数中选择组合的和为0，true，一个都不选
        for (int i=1; i<=nums.length; i++)
            dp[i][0] = true;

        //填写dp 表格
        for (int i=1; i<nums.length; i++){
            for (int j=1; j<target; j++) {

                if (j >= nums[i-1]) //注意；[i-1]为对应的元素索引
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
                else
                    dp[i][j] = dp[i-1][j];
            }
        }
        return dp[nums.length][target];

    }
    // （cache） + visit
    // sum = sum(nums); target = sum/2;
    //
    public boolean canPartition_dp(int[] nums) {
        if (nums == null || nums.length == 0)
            return false;

        int sum = 0;
        for (int num:nums)
            sum += num;
        if (sum % 2 != 0)
            return false;

        int target = sum/2;

        //
        int[][] dp = new int[nums.length][target];

        return helper(nums, dp, 0, 0, target);
    }
    public boolean helper(int[]nums, int[][]dp, int presum, int index, int target) {

        // 超过 target，或者数组越界时，返回false
        if ( presum > target || index >= nums.length) {
            return false;
        }
        // 刚好partial sum = target时，返回true
        if (presum == target)
            return true;

        // 通过存储的dp[][]来避免重复计算
        // 存储'1' for true
        if (dp[index][presum] == 1)
            return true;
        // 存储'2' for false
        if (dp[index][presum] == 2)
            return false;
        //else 试图找到target by 选择这个元素nums[index]或者不选择这个元素
        boolean b1 = helper(nums, dp, presum + nums[index], index + 1, target);
        boolean b2 = helper(nums, dp, presum, index + 1, target);

        // 存储返回的结果到这个index对应的presum
        dp[index][presum] = (b1 || b1) ? 1 : 2;

        return b1 || b2;
    }
}
