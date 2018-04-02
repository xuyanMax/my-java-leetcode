package backTracing.medium;

import java.util.Arrays;

/*
* 377. Combination Sum IV
* Given an integer array with all positive numbers and no duplicates,
* find the number of possible combinations that add up to a positive integer target.

Example:

nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.
Follow up:
What if negative numbers are allowed in the given array?
How does it change the problem?
What limitation we need to add to the question to allow negative numbers?
* */
public class CombinationSum4 {

    // dp top-down
    public int sol1(int[] nums, int target) {
        int[] dp = new int[target+1];
        Arrays.fill(dp, -1);
        dp[0] = 1;
        Arrays.sort(nums);
        return helper(dp, target, nums);

    }
    public int helper(int[] dp, int target,  int[] nums){
        if (dp[target] != -1) return dp[target];
        int res = 0;
        for (int i=0; i<nums.length; i++)
            if (target >= nums[i])
                res += helper(dp,target-nums[i], nums);
        dp[target] = res;
        return res;
    }
    //dp bottom up
    public int sol2(int[]nums, int target) {
        int[] dp = new int[target+1];
        dp[0] = 1;
        Arrays.sort(nums);
        for(int t=1; t<=target; t++) {
            for(int j=0; j<nums.length; j++) {
                if(t >= nums[j])
                    dp[t] += dp[t-nums[j]];
                else break;
            }
        }
        return dp[target];
    }

}
