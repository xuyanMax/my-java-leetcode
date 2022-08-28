package array.binarysearch.medium;

/**
 * Created by xu on 15/07/2017.
 * 33. Search in Rotated Sorted Array
 * Suppose an arr sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * <p>
 * You are given a target value to search. If found in the arr return its index, otherwise return -1.
 * <p>
 * You may assume NO DUPLICATE exists in the arr.
 */
public class SearchInRotatedArray1 {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        // EXACTLY same: Find minimum index in a rotated sorted array
        int minIndex = findMinIndex(nums);
        int len = nums.length;

        //通过target与数组左边界／右边界大小对比，确定target所在索引范围
        int left = (target > nums[len - 1]) ? 0 : minIndex;
        int right = target > nums[len - 1] ? minIndex - 1 : len - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target)
                left = mid + 1;
            else if (nums[mid] > target)
                right = mid - 1;
            else
                return mid;
        }
        return -1;
    }

    public int findMinIndex(int[] nums) {
        int left = 0, right = nums.length - 1;

        //没有等于
        while (left < right) {
            //如果左边界<右边界，返回左边界索引
            if (nums[left] < nums[right])
                return left;

            int mid = left + (right - left) / 2;

            //如果中值>右边界，说明最小值在中值右侧
            if (nums[mid] > nums[right])
                left = mid + 1;
            else
                right = mid;
        }
        return left;


    }
}
