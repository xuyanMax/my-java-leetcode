package array.binarysearch.medium;

/**
 * Created by xu on 15/07/2017.
 * <p>
 * 81. Search in Rotated Sorted Array II
 * Follow up for "Search in Rotated Sorted Array":
 * What if duplicates are allowed?
 * <p>
 * Would this affect the run-time complexity? How and why?
 * Suppose an arr sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * <p>
 * Write a function to determine if a given target is in the arr.
 * <p>
 * The arr may contain duplicates.
 * <p>
 * https://discuss.leetcode.com/topic/310/when-there-are-duplicates-the-worst-case-is-o-n-could-we-do-better/28
 */
public class SearchInRotatedArray2 {

    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return false;
        int left = 0, right = nums.length - 1;
        //一定是分为：一侧有序，一侧翻转
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target)//检查mid是否等于target
                return true;

            if (nums[left] < nums[mid]) { //如果左侧有序
                if (target >= nums[left] && target <= nums[mid]) // target在 [LEFT, MID]之间
                    right = mid - 1;
                else
                    left = mid + 1; //target在右侧
            } else if (nums[left] > nums[mid]) { //右侧有序 【4,5,6,7,0,1,2,3】 ->【0,1,2,3】 target 5
                if (nums[mid] <= target && target <= nums[right]) {// target在 [mid, RIGHT]之间
                    left = mid + 1;
                } else
                    right = mid - 1; //在左侧
            } else
                //duplicates 111112 target 2; O(n) time complexity
                // nums[left] == nums[mid] left move one step towards right
                left++;
        }
        return false;
    }
}
