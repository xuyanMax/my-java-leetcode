package dfs;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xu on 06/08/2017.
 * <p>
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S.
 * Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
 * <p>
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 * <p>
 * Example 1:
 * Input: nums is [1, 1, 1, 1, 1], S is 3.
 * Output: 5
 * Explanation:
 * <p>
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * <p>
 * There are 5 ways to assign symbols to make the sum of nums be target 3.
 * Note:
 * The length of the given arr is positive and will not exceed 20.
 * The sum of elements in the given arr will not exceed 1000.
 * Your output answer is guaranteed to be fitted in a 32-bit integer.
 */
public class TargetSumMemoize {
    // use visit (preOrder idea) and there will be overlaps of course,
    // so we use a memoization to record the intermediate results
    // key: "level->preSum"
    // label: number of possibilities
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0)
            return 0;
        return dfs(nums, 0, 0, S, new HashMap<>());
    }

    public int dfs(int[] nums, int level, int preSum, int S, Map<String, Integer> maps) {
        // create unique key for every unique node in each level
        String key = level + "->" + preSum;

        // 检查是否已经计算过在该层拥有该数值节点对应的count
        if (maps.containsKey(key))
            return maps.get(key);

        // 边界条件，叶节点
        if (level == nums.length) {
            if (preSum == S) //如果在叶子节点，该序列的和恰好为S，返回个数1
                return 1;
            else return 0;// 否则返回0
        }

        // 以上为逻辑
        // 进入下一层二选一判断，
        int add = dfs(nums, level + 1, preSum + nums[level], S, maps);   // 左节点
        int minus = dfs(nums, level + 1, preSum - nums[level], S, maps); // 右节点

        //记录从当前层，当前preSum，节点开始和为targetSum的个数
        maps.put(key, add + minus);

        //返回当前节点累计的 count 值当上一层
        return add + minus;
    }

    /**
     * Recursion is slow, since its runtime is exponential
     * 改变原来的命题
     * 找到一个数组nums中的正数集合，剩余的数归入负数集合，使得他们的和是target
     * P - N = target
     * <p>
     * AND we know that
     * <p>
     * P + N = sum(nums)
     * <p>
     * So, 2*P = target + sum(nums)
     * P = (target + sum(nums))/2;
     * 因此任务变成从数组中找到一个子集，使得和等于(target + sum(nums))/2（我们用动态规划来做）
     */
    public int findTargetSumWays_dp(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return 0;
        int sum = 0;
        for (int num : nums)
            sum += num;
        if (sum < target)
            return 0;
        if ((target + sum) % 2 != 0)
            return 0;
        else
            return helper(nums, (target + sum) / 2);
    }

    public int helper(int[] nums, int S) {
        int[] dp = new int[S + 1];
        dp[0] = 1;//初始化
        // dp[i][s] = dp[i-1][s] + dp[i][s-nums[i]]
        for (int num : nums) {
            for (int j = S; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        return dp[S];
    }

}
