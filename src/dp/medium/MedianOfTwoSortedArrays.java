package dp.medium;

/**
 * @Author: xyx
 * @Date: 2018-12-11 21:09
 * @Version 1.0
 */
public class MedianOfTwoSortedArrays {
    /**
     * https://leetcode.com/problems/median-of-two-sorted-arrays/solution/
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        // make sure n > m
        // i + j == (n+m-i-j)+1
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int i = 0, j = 0, imin = 0, imax = m, half = (m + n + 1) / 2;
        double maxLeft = 0, minRight = 0;
        while (imin <= imax) {
            i = (imin + imax) / 2;
            j = half - i;
            if (j > 0 && i < m && nums2[j - 1] > nums1[i]) {//i is too small, must increase it
                imin = i + 1;
            } else if (i > 0 && j < n && nums1[i - 1] > nums2[j]) {//i is too big, must decrease it
                imax = i - 1;
            } else {//i is perfect
                if (i == 0) {// if i=0, then A[i-1] doesn’t exist, then we don’t need to check A[i-1] <= B[j].
                    maxLeft = (double) nums2[j - 1];
                } else if (j == 0) {// if j=0,
                    maxLeft = (double) nums1[i - 1];
                } else {
                    maxLeft = (double) Math.max(nums1[i - 1], nums2[j - 1]);
                }
                break;
            }
        }
        if ((m + n) % 2 == 1) { //一共奇数个元素
            return maxLeft;
        }

        /** i == m => j == 0
         *  nums1[0], nums1[2] .... nums[i-1] | ...
         *  ...                               | nums2[0], nums2[1], ... nums[j-1]
         *
         *  j == n => i == 0
         *  ...                               | nums1[0], nums1[2] .... nums[i-1]
         *  nums2[0], nums2[1], ... nums[j-1] | ...
         */
        if (i == m) { // => m==n && j=0
            minRight = (double) nums2[j];
        } else if (j == n) {// => m==n && i=0
            minRight = (double) nums1[i];
        } else {
            minRight = (double) Math.min(nums1[i], nums2[j]);
        }

        return (double) (maxLeft + minRight) / 2;
    }
}
