package sort;

import java.util.Arrays;

/**
 * Created by xu on 28/08/2017.
 * 324.
 * Given an unsorted arr nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 * <p>
 * Example:
 * (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
 * (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
 * <p>
 * Note:
 * You may assume all input has valid answer.
 * <p>
 * Follow Up:
 * Can you do it in O(n) time and/or in-place with O(1) extra space?
 * https://leetcode.com/explore/interview/card/top-interview-questions-hard/120/sorting-and-searching/857/discuss/77682/Step-by-step-explanation-of-index-mapping-in-Java
 */
public class WiggleSort_2 {

    //M L S
    public void wiggleSort(int[] nums) {
        // Find a median.
        int median = findKthLargest(nums, (nums.length + 1) / 2);
        int n = nums.length;

        int left = 0, i = 0, right = n - 1;

        // 3-way-partition-to-wiggly in O(n) time with O(1) space.
        while (i <= right) {

            if (nums[newIndex(i, n)] > median) {
                swap(nums, newIndex(left++, n), newIndex(i++, n));
            } else if (nums[newIndex(i, n)] < median) {
                swap(nums, newIndex(right--, n), newIndex(i, n));
            } else {
                i++;
            }
        }
    }

    public int findKthLargest(int[] arr, int k) {
        Arrays.sort(arr);
        return arr[arr.length - k];
    }

    // Index-rewiring.
    // #define A(i) nums[(1+2*(i)) % (n|1)]
    private int newIndex(int index, int n) {
        return (1 + 2 * index) % (n | 1);
    }

    public void swap(int[] nums, int ind1, int ind2) {
        int tmp = nums[ind1];
        nums[ind1] = nums[ind2];
        nums[ind2] = tmp;
    }

}
