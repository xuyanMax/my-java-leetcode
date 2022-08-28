package array.binarysearch.hard;


//4. Median of Two Sorted Arrays

/**
 * Median of Two Sorted Arrays
 * Go to Discuss
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * <p>
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * <p>
 * You may assume nums1 and nums2 cannot be both empty.
 * <p>
 * Example 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * The median is 2.0
 * Example 2:
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * The median is (2 + 3)/2 = 2.5
 * <p>
 * ANSWER:https://leetcode.com/explore/interview/card/top-interview-questions-hard/120/sorting-and-searching/859/discuss/2481/Share-my-O(log(min(mn))-solution-with-explanation
 */
public class MedianTwoSortedArray {
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
            if (j > 0 && nums2[j - 1] > nums1[i]) {//i is too small, must increase it
                imin = i + 1;
            } else if (i > 0 && nums1[i - 1] > nums2[j]) {//i is too big, must decrease it
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
        if ((m + n) % 2 == 1) {
            return maxLeft;
        }
        if (i == m) {
            minRight = (double) nums2[j];
        } else if (j == n) {
            minRight = (double) nums1[i];
        } else {
            minRight = (double) Math.min(nums1[i], nums2[j]);
        }

        return (double) (maxLeft + minRight) / 2;
    }
}
