package array.twosum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18. 4Sum
 * Given an array S of n integers, are there elements a, b, c, and d in S
 * such that a + b + c + d = target?
 * Find all unique quadruplets in the array which gives the sum of target.
 * <p>
 * Note: The solution set must not contain duplicate quadruplets.
 * <p>
 * For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.
 * <p>
 * A solution set is:
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 */
public class Four4Sum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ret = new ArrayList<>();

        int len = nums.length;
        if (nums == null | len < 4)
            return ret;

        Arrays.sort(nums);
        int max = nums[len - 1];
        if (4 * nums[0] > target | 4 * max < target)
            return ret;
        int i, z;
        for (i = 0; i < len; i++) {
            z = nums[i];
            if (i > 0 && nums[i - 1] == z)
                continue;// avoid duplicates
            if (z + 3 * max < target)
                continue;// z is too small
            if (4 * z > target)
                break;// z is too large
            if (4 * z == target) {// z is the boundary
                if (i + 3 < len && z == nums[i + 3])
                    ret.add(Arrays.asList(z, z, z, z));
                break;
            }
            threeSumForFourSum(ret, target - z, i + 1, len - 1, z, nums);
        }

        return ret;
    }

    public void threeSumForFourSum(List<List<Integer>> fourSumList, int target,
                                   int low, int high, int z1, int[] nums) {
        if (low + 1 >= high) {// low mid high
            return;
        }
        int max = nums[high];
        if (3 * max < target | 3 * nums[low] > target)
            return;
        int i, z;
        for (i = low; i < high - 1; i++) {
            z = nums[low];
            if (i > low && z == nums[i - 1])
                continue;// avoid duplicates
            if (z + 2 * max < target)
                continue;// z too small
            if (3 * z > target)
                break; // z too large
            if (z * 3 == target) { // z is the boundary
                if (i + 2 <= high && z == nums[i + 2])
                    fourSumList.add(Arrays.asList(z1, z, z, z));
                break;
            }
            twoSumForFourSum(fourSumList, target - z, i + 1, high, z1, z, nums);
        }

    }

    public void twoSumForFourSum(List<List<Integer>> fourSumList, int target, int low, int high, int z1, int z2, int[] nums) {

        if (low >= high)
            return;
        if (2 * nums[high] < target | 2 * nums[low] > target)
            return;
        int i = low, j = high, sum, z;
        while (i < j) {
            sum = nums[i] + nums[j];
            if (sum < target)
                i++;
            else if (sum > target)
                j--;
            else { // =
                fourSumList.add(Arrays.asList(z1, z2, nums[i], nums[j]));
                z = nums[i];
                while (i < j && z == nums[i]) // avoid duplicates nums[i]
                    i++;

                z = nums[j];
                while (i < j && z == nums[j]) // avoid duplicates nums[j]
                    j--;
            }
        }
        return;
    }
}
