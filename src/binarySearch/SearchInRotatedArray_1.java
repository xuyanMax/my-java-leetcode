package binarySearch;

/**
 * Created by xu on 15/07/2017.
 */
/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

*/
public class SearchInRotatedArray_1 {
    public int search(int[] nums, int target) {
        if (nums==null || nums.length == 0)
            return -1;
        int minIndex = findMin(nums);
        int len = nums.length;
        int left = (target > nums[len-1])?0:minIndex;
        int right = target > nums[len-1]?minIndex-1:len-1;

        while(left <= right) {
            int mid = left + (right-left)/2;
            if (nums[mid] < target)
                left = mid + 1;
            else if (nums[mid] > target)
                right = mid - 1;
            else
                return mid;
        }
        return -1;
    }
    public int findMin(int[] nums) {
        int left = 0, right = nums.length-1;

        //没有等于
        while(left < right) {
            if (nums[left] < nums[right])
                return left;
            int mid = left + (right-left)/2;
            if (nums[mid] > nums[right])
                left = mid+1;
            else
                right = mid;
        }
        return left;

    }
}
