package dfs.easy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by xu on 07/08/2017.
 */
/*
Given an integer arr, your task is to find all the different possible increasing subsequences
of the given arr, and the length of an increasing subsequence should be at least 2 .

Example:
Input: [4, 6, 7, 7]
Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
Note:

The length of the given arr will not exceed 15.
The range of integer in the given arr is [-100,100].
The given arr may contain duplicates, and two equal integers should also be considered as a
special case of increasing sequence.

*/
public class IncreasingSubsequences {
    // 利用set来记录所有结果，保证无重复
    public List<List<Integer>> findSubsequences(int[] nums) {

        List<List<Integer>> ret = new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();

        if (nums == null || nums.length == 0)
            return ret;

        dfs(set, new ArrayList<>(), 0, nums);

        ret = new ArrayList<>(set);

        return ret;
    }

    public void dfs(Set<List<Integer>> results, List<Integer> result, int depth, int[] nums) {
        if (result.size() >= 2) {
            //results.add(result);
            //必须要new
            results.add(new ArrayList<>(result));
        }

        for (int pos = depth; pos < nums.length; pos++) {
            // 如果result为空，直接加入；或者呈递增趋势
            if (result.size() == 0 || nums[pos] >= result.get(result.size() - 1)) {
                result.add(nums[pos]);
                dfs(results, result, pos + 1, nums);
                result.remove(result.size() - 1);
            }
        }

    }
}
