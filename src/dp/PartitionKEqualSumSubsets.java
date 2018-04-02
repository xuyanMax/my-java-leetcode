package dp;

/**
 * Created by xu on 28/12/2017.
 */
public class PartitionKEqualSumSubsets {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) return false;
        int target = 0;
        for (int num:nums) target += num;
        if (target % 4 != 0) return false;
        boolean[]visited = new boolean[nums.length];
        return helper(nums, k, 0, 0, 0, visited, target/4);
    }
    public boolean helper(int[] nums, int k, int index, int presum, int count, boolean[] visited, int target) {
        if (k == 1) return true;
        if (presum == target && count > 0) return helper(nums, k-1, 0, 0, 0, visited, target);
        for (int i=0; i<nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                if (helper(nums, k, i+1, presum + nums[i], count+1, visited, target))
                    return true;
                visited[i] = false;
            }
        }
        return false;
    }

}
