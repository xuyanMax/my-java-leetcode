package array.medium;

import java.util.Arrays;

/**
 * Created by xu on 22/07/2017.
 * <p>
 * Given an arr S of n integers, are there elements a, b, c in S such that a + b + c = 0?
 * Find all unique triplets in the arr which gives the sum of zero.
 * <p>
 * Note: The solution set must not contain duplicate triplets.
 * <p>
 * For example, given arr S = [-1, 0, 1, 2, -1, -4],
 * <p>
 * A solution set is:
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 */
public class Three3SumClosest {

    // 先把序列排序后，然后利用sum3算法中采用的双指针方法，
    // 同时记录ret 与每一轮sum的值，比较target-sum的绝对值 与 target - ret大小，如果小，更新ret
    // 如果 sum值 小于 target 左指针右移
    // 否则，右指针左移

    public int solution(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int ret = 0;
        if (n <= 3) {
            for (int num : nums)
                ret += num;
            return ret;
        }
        //初始化返回值
        ret = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                //更新比较的sum值
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(target - sum) <= Math.abs(target - ret)) { //包含等于
                    ret = sum;
                    if (ret == target)
                        return ret;
                }
                if (sum > target)
                    right--;
                else if (sum <= target)
                    left++;
            }
        }
        return ret;
    }
}
