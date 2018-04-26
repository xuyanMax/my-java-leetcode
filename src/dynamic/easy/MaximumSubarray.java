package dynamic.easy;

/*
*
53. Maximum Subarray
Find the contiguous subarray within an arr (containing at least one number) which has the largest sum.

For example, given the arr [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6.


More practice:
If you have figured out the O(n) solution, try coding another solution using the
divide and conquer approach, which is more subtle.

*/
public class MaximumSubarray {

    public int dp (int[] array) {
        int n = array.length;
        // dynamic[i]代表以array[i]结尾的数组时，最大子序列和
        // dynamic[n-1] 为以array[n-1]结尾的数组时，最大子序列和
        // 题目要求的最大连续子序列和必定是以array[i]其中一个元素结尾的最大子序列和

        //  递推条件
        // dynamic[i]等于nums[i]本身或者当以nums[i-1]结尾的最大和大于0时，加上dp[i-1]
        // dynamic[i] = nums[i] + dynamic[i-1]>0 ? dynamic[i-1] : 0;
        int[] dp = new int[n];

        dp[0] = array[0];
        int max = dp[0];
        for (int i=1; i<n; i++) {
            //一定要加括号
            dp[i] = array[i] + (dp[i-1] <= 0 ? 0 : dp[i-1]);
            max = Math.max(dp[i], max);
        }
        return max;
    }
    // Kadane's Algorithm
}
