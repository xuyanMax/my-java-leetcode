package greedy;

/**
 * Given an arr of non-negative integers, you are initially positioned at the first index of the arr.
 * <p>
 * Each element in the arr represents your maximum jump length at that position.
 * <p>
 * Determine if you are able to reach the last index.
 * <p>
 * For example:
 * A = [2,3,1,1,4], return true.
 * <p>
 * A = [3,2,1,0,4], return false.
 */

public class JumpGame {

    public static void main(String[] args) {
        System.out.println(jumpGame1(new int[]{2, 5, 0, 0}));
        System.out.println(jumpGame1(new int[]{0, 1}));
    }

    public static boolean jumpGame1(int[] nums) {

        int maxReachSoFar = 0;

        // O(n) time complexity
        for (int i = 0; i < nums.length && maxReachSoFar < nums.length - 1; i++) {
            if (i <= maxReachSoFar) {
                maxReachSoFar = Math.max(i + nums[i], maxReachSoFar);
                if (maxReachSoFar >= nums.length - 1)
                    return true;
            }
        }
        return false;
    }

    public static boolean jumGame2(int[] nums, int n) {
        int i = 0;
        for (int reach = 0; i < n && i <= reach; ++i)
            reach = Math.max(i + nums[i], reach);
        return i >= n;
    }


}
