package arr.easy;

import java.util.Arrays;

/**
 * Created by xu on 16/09/2017.
 * 628
 *
 Given an integer array, find three numbers whose product is maximum and output the maximum product.

 Example 1:
 Input: [1,2,3]
 Output: 6
 Example 2:
 Input: [1,2,3,4]
 Output: 24
 */
public class MaximumProductThree {
    public int maximumProduct(int[] nums) {
        if (nums == null || nums.length < 3)
            return 0;
        Arrays.sort(nums);
        int n = nums.length;

        int max = nums[n-1]*nums[n-2]*nums[n-3];

        if (nums[0] < 0 && nums[1] < 0)
            max = Math.max(max, nums[0]*nums[1]*nums[n-1]);
        return max;
    }
    public int maximumProduct_short(int[] nums) {
        if (nums == null || nums.length < 3)
            return 0;
        Arrays.sort(nums);
        int n = nums.length;

        return Math.max(nums[n-1]*nums[n-2]*nums[n-3], nums[0]*nums[1]*nums[n-1]);
    }

    // 维护三个最大变量以及两个最小变量，一次遍历
    // O(N), O(1)
    public int maximumProduct_2(int[] nums) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        if (nums == null || nums.length < 3)
            return 0;

        for (int num:nums) {
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            }
            else if (num > max2 && num < max1) {
                max3 = max2;
                max2 = num;
            }
            else if (num > max3 && num < max2)
                max3 = num;

            if (num < min1){
                min2 = min1;
                min1 = num;
            }else if (num > min1 && num < min2)
                min2 = num;
        }
        return Math.max(max1*max2*max3, min1*min2*max1);

    }

}
