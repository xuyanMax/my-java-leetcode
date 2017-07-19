package binarySearch;

/**
 * Created by xu on 15/07/2017.
 */
/*
Follow up for "Search in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Write a function to determine if a given target is in the array.

The array may contain duplicates.


https://discuss.leetcode.com/topic/310/when-there-are-duplicates-the-worst-case-is-o-n-could-we-do-better/28
*/
public class SearchInRotatedARray_2 {

    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return false;
        int left = 0, right = nums.length-1;
        while (left <= right) {
            int mid = left + (right-left)/2;

            if (nums[mid] == target)//检查mid是否等于target
                return true;

            if (nums[left] < nums[mid]) { //左侧有序
                if (target >= nums[left] && target < nums[mid]) // target在 [LEFT, MID)之间
                    right = mid - 1;
                else
                    left = mid + 1; //target在右侧,不包含mid
            }
            else if (nums[left] > nums[mid]){ //右侧有序 【4,5,6,7,0,1,2,3】 ->【0,1,2,3】 target 5
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                }
                else
                    right = mid - 1; //在左侧
            } else // nums[left] == nums[mid] left move one step towards right
                left++;
        }
        return false;
    }
}
