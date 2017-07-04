package binarySearch;

/**
 * Created by xu on 2017/6/20.
 */

/*Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

You may assume no duplicate exists in the array.

*/
public class FindMinInRotatedSortedArray {

    /*二分查找，
    如果最左小于最右，则返回最左，即为最小值 // left刚好在最小值
    否则，如果mid大于最右（right值），则rotation在mid+1 -- right之间，否则在left -- mid之间 //left 在两段之间
    */
    //5 6 1 2 3 4
    //4 5 6 7 2 3
    // 4 5 6 1 2 3
    public int findMin(int[] nums) {
        int left = 0, right = nums.length-1;

//        if (nums[left] < nums[right])
//            return nums[left];

        while (left < right) {
            if (nums[left] < nums[right])
                return nums[left];

            int mid = left + (right - left)/2;

            if (nums[mid] > nums[right]) {
                left = mid + 1;

            } else {
                right = mid;

            }

        }
        return nums[left];


    }
}
