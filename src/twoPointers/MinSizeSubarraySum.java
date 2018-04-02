package twoPointers;

/**
 * Created by xu on 18/08/2017.
 */
/*
Given an arr of n positive integers and a positive integer s,
find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

For example, given the arr [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.

More practice:
If you have figured out the O(n) solution,
try coding another solution of which the time complexity is O(n log n).
*/
public class MinSizeSubarraySum {
    //新建一个sums[]数组，存储前i项和，数组呈现递增趋势
    // 双指针，第一个指针start_ind从索引 0 开始 扫面直到sums.length-1,第二个指针始终指向sums.length-1
    // 在start_ind - - sums.length-1之间寻找大于或者等于target的索引end_ind
    // 利用全局变量 max_len来维持最小的长度 (end_ind - start_ind)即为子序列的长度
    //利用二分查找到target索引（end_ind）范围即 end_ind - start_ind)
    public int minSubarrayLen(int[] nums, int target) {

        int[] sums = new int[nums.length + 1];
        int max_len = Integer.MAX_VALUE;

        for (int i=1; i<sums.length; i++)
            sums[i] = sums[i-1] + nums[i-1];

        for (int start_ind=0; start_ind<nums.length; start_ind++){

            int end_ind = bs_larger(nums, sums[start_ind] + target, start_ind + 1, nums.length);

            if (end_ind == -1)
                continue;

            max_len = Math.min(max_len, end_ind - start_ind);

        }
        return max_len == Integer.MAX_VALUE ? 0 : max_len;

    }
    public int bs_larger(int[]nums, int target, int left, int right) {
        // target 大于nums所在范围最大值
        if (nums[right] < target)
            return -1;
        while (left < right) {
            int mid = left + (right - left)/2;
            if (nums[mid] < target)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }
}
