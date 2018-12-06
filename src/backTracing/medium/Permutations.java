package backTracing.medium;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author xu
 * 46. Permutations
 * Given a collection of DISTINCT numbers, return all possible permutations.
 * <p>
 * For example,
 * [1,2,3] have the following permutations:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 * https://leetcode.com/problems/permutations/#/description
 */
public class Permutations {

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        // Arrays.sort(nums); // not necessary
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }

    private static void backtrack(List<List<Integer>> lists, List<Integer> aList, int[] nums) {

        if (aList.size() == nums.length) {
            lists.add(new ArrayList<>(aList));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            /* if does not include this contain check we would get
             * 1 1 1
             * 1 1 2
             * 1 1 3
             * 1 2 1
             * 1 2 2
             * 1 2 3
             * ....
             * .... #27
             * */
            if (aList.contains(nums[i]))
                continue; // element already exists, skip

            aList.add(nums[i]);

            backtrack(lists, aList, nums);

            aList.remove(aList.size() - 1);

        }
    }
}
