package dp.slidingWindow;

/**
 * Created by xu on 18/08/2017.
 * <p>
 * lc 209
 * Given an arr of n positive integers and a positive integer s,
 * find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
 * <p>
 * For example, given the arr [2,3,1,2,4,3] and s = 7,
 * the subarray [4,3] has the minimal length under the problem constraint.
 * <p>
 * More practice:
 * If you have figured out the O(n) solution,
 * try coding another solution of which the time complexity is O(n log n).
 */
public class MinSizeSubarraySumGreaterK {

    int brute_force(int[] nums, int s) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; n++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum >= s) {
                    ans = Math.min(ans, j - i + 1);
                    break;
                }
            }
        }
        return -1;
    }

    int slideWindow(int[] nums, int target) {
        int sum = 0, left = 0;
        int min_len = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= target) {
                min_len = Math.min(min_len, i - left + 1);
                sum -= nums[left++];
            }
        }
        return min_len != Integer.MAX_VALUE ? min_len : 0;
    }
}
