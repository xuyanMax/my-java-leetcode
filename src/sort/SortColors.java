package sort;

/**
 * @Author: xyx
 * @Date: 2019-02-09 11:32
 * @Version 1.0
 * Sort Colors
 * Go to Discuss
 * Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
 * <p>
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * <p>
 * Note: You are not suppose to use the library's sort function for this problem.
 * <p>
 * Example:
 * <p>
 * Input: [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * Follow up:
 * <p>
 * A rather straight forward solution is a two-pass algorithm using counting sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
 * Could you come up with a one-pass algorithm using only constant space?
 */
public class SortColors {

    /**
     * Two pass
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        int[] count = new int[3];
        for (int n : nums)
            count[n]++;

        int ind = 0;
        for (int i = 0; i < nums.length; ) {
            if (count[ind]-- > 0)
                nums[i++] = ind;
            else
                ind++;
        }

    }

    // one pass in place solution
    void sortColors(int A[], int n) {
        int n0 = -1, n1 = -1, n2 = -1;
        for (int i = 0; i < n; ++i) {
            if (A[i] == 0) {
                A[++n2] = 2;
                A[++n1] = 1;
                A[++n0] = 0;
            } else if (A[i] == 1) {
                A[++n2] = 2;
                A[++n1] = 1;
            } else if (A[i] == 2) {
                A[++n2] = 2;
            }
        }
    }

    // one pass in place solution
    public void sortColors2(int A[], int n) {
        int j = 0, k = n - 1;
        for (int i = 0; i <= k; i++) {
            if (A[i] == 0)
                swap(A[i], A[j++]);
            else if (A[i] == 2)
                swap(A[i--], A[k--]);
        }
    }

    public void swap(int num, int nums2) {

    }

}
