package bit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xu on 2017/5/29.
 *
 * Given a set of DISTINCT integers, nums, return all possible subsets.
 * Note: The solution set must not contain duplicate subsets.
 *
 * For example, If nums = [1,2,3], a solution is:
 * [
     [3],
     [1],
     [2],
     [1,2,3],
     [1,3],
     [2,3],
     [1,2],
     []
    ]

 */
public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        List<Integer> result = new ArrayList<>();
        susetUtil(results, result, 0, nums);

        return results;
    }
    public void susetUtil(List<List<Integer>> results, List<Integer> result, int pos, int[] nums){

//        results.add(result);// this way results in all empty lists....
        results.add(new ArrayList<>(result));
        for (int ipos=pos; ipos<nums.length; ipos++) {

            result.add(nums[ipos]);
            susetUtil(results, result, ipos + 1, nums);
            result.remove(result.size()-1);// unmake
        }
    }

    // reference
    // https://discuss.leetcode.com/topic/2764/my-solution-using-bit-manipulation/48
    // time complexity: O(n*2^n)
    public List<List<Integer>> subsets2(int[] nums) {
        Arrays.sort(nums);
        int totalNumberOfSols = 1 << nums.length; // 2^n
        List<List<Integer>> results = new ArrayList<List<Integer>>(totalNumberOfSols);
        for (int i=0; i<totalNumberOfSols; i++) {
            List<Integer> aresult = new ArrayList<>();
            for (int j=0; j<nums.length; j++) {
                if ( (i & (1 << j)) != 0)
                    aresult.add(nums[j]);
            }
            results.add(aresult);
        }
        return  results;
    }
    public List<List<Integer>> subsets3(int[] nums) {
        Arrays.sort(nums);
        int totalNumberOfSols = 1 << nums.length; // 2^n
        List<List<Integer>> results = new ArrayList<List<Integer>>(totalNumberOfSols);
        for (int i=0; i<nums.length; i++) {
            for (int j=0; j<totalNumberOfSols; j++) {
                if ( (1 & (j >> i)) == 1) {
                    if (results.get(j) == null) {
                        results.add(new ArrayList<>());
                    }else {
                        results.get(j).add(nums[i]);
                    }
                }
            }
        }
        return  results;
    }
}
