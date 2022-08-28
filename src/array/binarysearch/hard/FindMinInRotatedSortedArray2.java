package array.binarysearch.hard;

/*
 * 154. Find Minimum in Rotated Sorted Array II
 *
 * May contain duplicates
 * 12234567
 * 45671223
 * 22345671
 * */
public class FindMinInRotatedSortedArray2 {
    public static int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            // mid和right比较
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right])
                left = mid + 1;
            else if (nums[mid] < nums[right])
                right = mid;
            else // nums[mid] == nums[right]
                right--;
        }
        return nums[left];
    }

    //FindMaxInRotatedSortedArray
    public int findMax(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            // mid和left比较
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[left])
                right = mid - 1;
            else if (nums[mid] > nums[left])
                left = mid;
            else left++;
        }
        return nums[left];
    }

}
