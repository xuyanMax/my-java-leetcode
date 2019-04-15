package binarysearch.medium;

/**
 * @Author: xyx
 * @Date: 2019-02-09 12:07
 * @Version 1.0
 * <p>
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 * <p>
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * <p>
 * If the target is not found in the array, return [-1, -1].
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 */
public class SearchRange {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return new int[]{-1, -1};
        int start = bs(nums, target);

        if (start == -1 || start == nums.length || nums[start] != target)
            return new int[]{-1, -1};

        int end = bs(nums, target + 1);

        return new int[]{start, end - 1};
    }

    public int bs(int[] nums, int target) {
        if (nums[0] > target)
            return -1;
        if (nums[nums.length - 1] < target)
            return nums.length;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // left <= mid < right
            if (nums[mid] < target)
                left = mid + 1;
            else
                //should not be mid -1 when nums[mid] = target
                //could be mid when nums[mid]>target, because right > mid
                right = mid;
        }
        return left;
    }
}
