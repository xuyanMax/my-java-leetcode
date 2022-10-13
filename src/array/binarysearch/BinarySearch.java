package array.binarysearch;

/**
 * Created by xu on 2022/10/13 09:06.
 */
public class BinarySearch {

    /**
     * sorted array nums without duplicate numbers
     **/
    int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] > target)
                right = mid - 1;
            else left = mid + 1;
        }
        return -1;
    }

    /**
     * sorted array nums, with duplicate numbers
     **/
    int left_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                right = mid - 1;
            else if (nums[mid] < target)
                left = mid + 1;
            else right = mid - 1;
        }
        if (left >= nums.length || nums[left] != target)
            return -1;
        return left; // == return right;
    }

    int right_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                left = mid + 1;
            else if (nums[mid] > target)
                right = mid - 1;
            else left = mid + 1;
        }
        if (right < 0 || nums[right] != target)
            return -1;
        return right;
    }
}
