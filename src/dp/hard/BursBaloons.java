package dp.hard;
//312. Burst Balloons
//https://www.youtube.com/watch?v=IFNibRVgFBo&t=1s
// 解法有点像Longest Palindrome
public class BursBaloons {
    public int maxCoins(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        // dp[i][j]：代表在数组nums[i:j]内最大的burst值
        int[][] dp = new int[nums.length][nums.length];

        // 注意两个for的判断条件 index out of range
        for (int len = 1; len <= nums.length; len++) {
            for (int i = 0; i <= nums.length - len; i++) {
                int j = i + len -1;
                for (int k=i; k<=j; k++){// k代表当前subarray[i:j]中最后一个burst的元素
                    int leftValue = 1;
                    int rightValue = 1;
                    if (i != 0)//即不是array的最左侧元素
                        leftValue = nums[i-1];
                    if (j != nums.length-1) //不是array最右侧元素
                        rightValue = nums[j+1];

                    int before = 0, after = 0;
                    if (i != k) //不是subarray最左侧元素，最后一个burst
                        before = dp[i][k-1];
                    if (k != j)
                        after = dp[k+1][j];

                    // 通过在subarray[i:j]之中尝试每一个k，最为最后burst的元素，求其中的max
                    // leftValue = 1 if i = 0
                    // rightValue = 1 if j = nums.length-1
                    // 否则nums[k]作为subarray[i:j]中最后burst的元素，
                    // 所得值 = leftValue * rightValue * nums[k]
                    //         + 两侧数组的最大值即 dp[i][k-1] + dp[k+1][j]
                    dp[i][j] = Math.max(dp[i][j],
                            leftValue*rightValue*nums[k] + before + after);
                }
            }
        }
        return dp[0][nums.length-1];
    }
}
