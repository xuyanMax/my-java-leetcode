package dfs;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by xu on 07/08/2017.
 * <p>
 * Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has,
 * please find out a way you can make one square by using up all those matchsticks. You should not break any stick,
 * but you can link them up, and each matchstick must be used exactly one time.
 * <p>
 * Your input will be several matchsticks the girl has, represented with their stick length. Your output will either be
 * true or false, to represent whether you could make one square using ALL the matchsticks the little match girl has.
 * <p>
 * Example 1:
 * Input: [1,1,2,2,2]
 * Output: true
 * <p>
 * Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
 * Example 2:
 * Input: [3,3,3,3,4]
 * Output: false
 * <p>
 * Explanation: You cannot find a way to form a square with all the matchsticks.
 * Note:
 * The length sum of the given matchsticks is in the range of 0 to 10^9.
 * The length of the given matchstick arr will not exceed 15.
 */
public class MatchSticks {

    // It is a partition problem, the partition problem is to decide
    // whether a given multiset S of positive
    // integers can be partitioned into two subsets S1 and S2 such that the
    // sum of the numbers is S1 equals that of S2. The problem is NP-Complete
    // Time complexity - O(4^N)
    public boolean makeSquare(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length < 4)
            return false;

        int sum = 0;
        for (int num : nums) sum += num;

        if (sum % 4 != 0)
            return false;
        //通过倒排nums，可以起到优化的作用，加快排除不可能的情况
        //逆序不可以使用以下方法，最好手动实现，先调用顺排，再手动逆序
//        Arrays.sort(nums, Collections.reverse());
        Arrays.sort(nums);reverseArray(nums);
        return dfs(nums, sum / 4, new int[4], 0);
    }

    // sums[] 代表每个边的长度和
    public boolean dfs(int[] nums, int target, int[] sidesSum, int depth) {
        //边界判断-使用全部的matchsticks
        if (depth == nums.length) {
            // 判断所有边是否相等
            if (sidesSum[0] == target && sidesSum[1] == target
                    && sidesSum[2] == target && sidesSum[3] == target) {
                return true;
            }
            // 对应下面的if条件判断语句，说明该情况无解，返回后测试其他可能
            return false;
        }
        for (int side = 0; side < 4; side++) {
            if (sidesSum[side] + nums[depth] > target)
                continue;
            //else
            sidesSum[side] += nums[depth];

            // 使用条件判断来对应 boolean递归方法
            if (dfs(nums, target, sidesSum, depth + 1))
                return true;
            // unmake
            sidesSum[side] -= nums[depth];
        }
        // 如果上述四种主要情况及对应的自情况无解，则返回false
        return false;
    }

    public void reverseArray(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int tmp = nums[right];
            nums[right] = nums[left];
            nums[left] = tmp;
            left++;
            right--;
        }
    }
}
