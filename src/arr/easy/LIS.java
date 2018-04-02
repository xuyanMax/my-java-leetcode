package arr.easy;

import java.util.Arrays;

/**
 * Created by xu on 16/09/2017.
 */
/*
300. Longest Increasing Subsequence
Given an unsorted arr of integers, find the length of longest increasing subsequence.
*/
public class LIS {

    // TLE
    // O(2^n) size of recursion tree 2^n
    // O(n^2) memo arr size n*n
    // taken or nottaken
    public int lengthOfLIS_dfs(int[] nums) {
        return dfs(nums, Integer.MIN_VALUE, 0);
    }
    public int dfs(int[] nums, int pre, int curPos){
        if (curPos == nums.length)
            return 0;

        int taken = 0;

        if (nums[curPos] > pre) {
            taken =  1 + dfs(nums, nums[curPos], curPos + 1);
        }
        int nottaken = dfs(nums, pre, curPos + 1);

        return Math.max(nottaken, taken);
    }

    // O(n^2)
    // O(n)
    public int lengthOfLIS_dp(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp,1);
        int max = 1;

        for (int i=1; i<nums.length; i++) {
            for (int j=0; j<i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }
    // O(nlgn)
    // 树上dp
    private static int[][] memo;
    public int lengthOfLIS_dfs_with_memo(int[] nums) {

        memo = new int[nums.length+1][nums.length];
        for (int[] row:memo)
            Arrays.fill(row, -1);

        return dfs_memo(nums, -1, 0);
    }
    public int dfs_memo(int[]nums, int preindex, int currpos){
        if (currpos == nums.length)
            return 0;
        if (memo[preindex + 1][currpos] != -1)
            return memo[preindex][currpos];

        int taken=0;

        if (preindex < 0 || nums[currpos] > nums[preindex])
            taken = 1 + dfs_memo(nums, currpos, currpos + 1);

        int nottaken = dfs_memo(nums, preindex, currpos + 1);

        memo[preindex + 1][currpos] = Math.max(taken, nottaken);

        return memo[preindex + 1][currpos];
    }

    // 参考package -> binarysearch->LIS
    public int lengthOfLIS_bs(int[] nums){
        if (nums == null || nums.length == 0)
            return 0;
        int n = nums.length;
        int len = 0;//返回值
        int[] dp = new int[n];
        dp[0] = nums[0];

        for(int num:nums) {
            if (num < dp[0]){
                dp[0] = num;
            }else if (num > dp[0] && num < dp[len-1]){
                int pos = nextLarger(nums, len, num);
                dp[pos] = num;
            }else if (num > dp[len-1]) {
                dp[len++] = num;
            }
        }
        return len;
    }

    public int nextLarger(int[] nums, int size, int target){
        int left = 0, right = size-1;
        if (target < nums[left] || target > nums[right])
            return -1;
        while (left <= right) {
            int mid = left + (right - left)/2;
            if (nums[mid] < target) {
                left = mid+1;
            }else if (nums[mid] > target) {
                if (nums[mid-1] < target)
                    return mid;
                right = mid-1;
            } else
                return mid;
        }
        return left;
    }
    // 时间复杂度：O(nlgn)
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int maxLen = 0;
        for (int num:nums) {
            int i = Arrays.binarySearch(dp, 0, maxLen, num);
            i = i<0?~i:i;
            dp[i] = num;
            if (i == maxLen) maxLen++;
        }
        return maxLen;
    }







}
