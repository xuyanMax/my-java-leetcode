package twoPointers;

import java.util.Map;

/**
 * Created by xu on 28/08/2017.
 */
/*
75. Sort Colors

Given an arr with n objects colored red, white or blue, sort them so that objects of the same color
are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.

click to show follow up.

Follow up:
A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the arr counting number of 0's, 1's, and 2's, then overwrite arr with total number of
0's, then 1's and followed by 2's.

Could you come up with an one-pass algorithm using only constant space?

*/
public class SortColors {
    // two pass algorithm + bucket sort
    private static final int MAX = 3;
    public void sortColors(int[] nums) {
        int[] count = new int[MAX];
        for (int n:nums)
            count[n]++;

        int ind = 0;
        for (int i=0; i<nums.length;) {
            if (count[ind]-- > 0)
                nums[i++] = ind;
            else
                ind++;
        }
        //下列同为赋值-循环语句
//        for (int i=0, val=0; val<MAX; val++) {
//            for (int c = 0; c < count[i]; c++)
//                nums[i++] = val;
//        }
    }

    // one pass
    public void sortColors_2 (int[] nums) {
        int n0 = -1, n1 = -1, n2 = -1;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == 0)
            {
                nums[++n2] = 2; nums[++n1] = 1; nums[++n0] = 0;
            }
            else if (nums[i] == 1)
            {
                nums[++n2] = 2; nums[++n1] = 1;
            }
            else if (nums[i] == 2)
            {
                nums[++n2] = 2;
            }
        }
    }

}
