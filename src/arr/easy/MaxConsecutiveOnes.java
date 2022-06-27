package arr.easy;

/**
 * Created by xu on 16/09/2017.
 * 485
 Given a binary array, find the maximum number of consecutive 1s in this array.

 Example 1:
 Input: [1,1,0,1,1,1]
 Output: 3
 Explanation: The first two digits or the last three digits are consecutive 1s.
 The maximum number of consecutive 1s is 3.
 */
public class MaxConsecutiveOnes {

    // 时间复杂度O(n)
    // 额外空间复杂读O(n)
    public int findMaxConsecutiveOnes(int[] nums) {
        int[] dp = new int[nums.length];//以i为结尾的最长连续1个数
        dp[0] = nums[0];

        for (int i=1; i< nums.length; i++) {
            if (nums[i] == 1)
                dp[i] = Math.max(dp[i-1] + 1, 1);

        }
        int max = Integer.MIN_VALUE;
        for (int n:dp)
            max = Math.max(max,n);
        return max;

    }
    // 时间复杂度O(n)
    //额外空间复杂读O(1)
    public int findMaxConsecutiveOnes_2(int[] nums) {
        int count = 0;
        int max = 0;
        for (int num:nums) {
            if(num == 1) {
                count++;
                max = Math.max(count, max);
            }else{
                count =  0;
            }
        }
        return max;
    }

}
