package divide_conquer.hard;

import java.util.Arrays;

/*493. Reverse Pairs

    Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].

    You need to return the number of important reverse pairs in the given array.

    Example1:

    Input: [1,3,2,3,1]
    Output: 2
    Example2:

    Input: [2,4,3,5,1]
    Output: 3
    Note:
    The length of the given array will not exceed 50,000.
    All the numbers in the input array are in the range of 32-bit integer*/
public class ReversePairs {

    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    private int mergeSort(int[] nums, int left, int right) {
        if (left >= right) return 0;
        int mid = left + (right - left) / 2;
        int cnt = mergeSort(nums, left, mid) + mergeSort(nums, mid + 1, right);

        // two pointers i:left->mid; j:mid+1->right
        // 两指针分别指向两个subarray 双指针同时向一个方向移动
        for (int i = left, j = mid + 1; i <= mid; i++) {
            while (j <= right && nums[i] / 2.0 > nums[j])
                j++;
            cnt += j - (mid + 1);
        }
        //排序
        Arrays.sort(nums, left, right + 1);
        return cnt;
    }

}
