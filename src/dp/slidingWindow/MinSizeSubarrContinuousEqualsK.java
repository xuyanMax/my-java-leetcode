package dp.slidingWindow;

/**
 * @author xu
 * <p>
 * 209. Minimum Size Subarray Sum
 * Given an arr of n positive integers and a positive integer s,
 * find the minimal length of a CONTIGUOUS subarray
 * of which the sum ≥ s. If there isn't one, return 0 instead.
 * <p>
 * For example, given the arr [2,3,1,2,4,3] and s = 7,
 * the subarray [4,3] has the minimal length under the problem constraint.
 * <p>
 * <p>
 * https://leetcode.com/problems/minimum-size-subarray-sum/#/description
 * <p>
 * Array + two pointers + binary search
 */
public class MinSizeSubarrContinuousEqualsK {

    public static void main(String[] args) {

        int[] nums = new int[]{2, 3, 1, 2, 2, 4, 3};

        System.out.println(minSubArrayLen(nums, 7));
        System.out.println(solveNLogN(7, nums));
    }

    /* O(n) time complexity

     * two pointers
     *
     */
    public static int minSubArrayLen(int[] nums, int s) {
        int minLen = Integer.MAX_VALUE;

        int sum = 0;
        int left = 0, right = 0;
        while (right < nums.length) {
            sum += nums[right]; // modify sum here
            right++;            // increase right pointer

            while (sum >= s) { // sum condition
                minLen = Math.min(minLen, right - left); // updateHighestHeightBtwLR minLen here is finding minimum
                sum -= nums[left]; // increase left to MAKE it invalid
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    public static int minSubArrayLen_exactSum(int[] nums, int s) {
        int min = Integer.MAX_VALUE;

        int sum = 0;
        int left = 0, right = 0;
        while (right < nums.length) {
            sum += nums[right];
            right++;

            while (sum >= s) {
                if (sum > s) {
                    sum -= nums[left];
                    left++;
                }

                if (sum == s)
                    min = Math.min(min, right - left);

            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    /**
     * O(N*LOGN)
     * As it reminds you of binary search. But you cannot sort the arr in this case as the current order
     * matters. How to get an ordered arr then? We need an accumulated sum arr,
     * since the integers are positive, the order is ascendent.
     * arr [2,3,1,2,4,3]
     * sums[0,2,5,6,12,15] with length = arr.length + 1
     */
    private static int solveNLogN(int s, int[] nums) {

        int[] sums = new int[nums.length + 1];

        for (int i = 1; i < sums.length; i++) sums[i] = sums[i - 1] + nums[i - 1];

        int minLen = Integer.MAX_VALUE;

        for (int i = 0; i < sums.length; i++) {

            int end = binarySearchEqOrNextLarger(i + 1, sums.length - 1, sums[i] + s, sums);

            if (end == sums.length) break; // 索引越界 must reserve this line

            if (end - i < minLen) minLen = end - i;

//	        	System.out.println(minLen);
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    private static int binarySearchEqOrNextLarger(int lo, int hi, int key, int[] sums) {
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (sums[mid] >= key) { // equal or next larger
                if (sums[mid - 1] < key)
                    return mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }


}
