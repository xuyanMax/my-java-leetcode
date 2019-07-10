package greedy;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/patching-array/#/description
// https://discuss.leetcode.com/topic/35494/solution-explanation/61

/**
 * @author xu
 * Given a sorted positive integer arr nums and an integer n,
 * add/patch elements to the arr such that any number in range [1, n] inclusive
 * can be formed by the sum of some elements in the arr.
 * Return the minimum number of patches required.
 *
 * Example 1:
 *
 * Input: nums = [1,3], n = 6
 * Output: 1
 *
 * Explanation:
 * Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
 * Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
 * Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
 * So we only need 1 patch.
 *
 * Example 2:
 *
 * Input: nums = [1,5,10], n = 20
 * Output: 2
 *
 * Explanation: The two patches can be [2, 4].
 *
 * Example 3:
 *
 * Input: nums = [1,2,2], n = 5
 * Output: 0
 */
public class PatchArray_124816 {

    public static void main(String[] args) {

        minPatch(new int[]{1, 2, 4}, 16);
//		minPatch(new int[]{1,2,3,9},20);
//		minPatch(new int[]{1,3,5,7,9},30);
    }

    public static int minPatch(int[] nums, int n) {
        long miss = 1;
        int add = 0;
        int i = 0;
        List<Long> result = new ArrayList<>();
        while (miss <= n) {
            if (i < nums.length && nums[i] <= miss) {
                miss += nums[i];
                i++;
            } else {
                result.add(miss);
                miss += miss;
                add++;
            }
        }
        for (long l : result)
            System.out.println(l);
        return add;
    }

}
