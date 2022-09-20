package array.presum;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xu on 2022/9/20 18:25.
 * lc 560
 * 统计并返回数组中和为k的连续子数组的个数
 */
public class SubArraySumK {

    //o(n^2)
    int subarraySum_bf(int[] nums, int k) {
        int[] presum = new int[nums.length + 1];
        for (int i = 1; i < presum.length; i++)
            presum[i] = presum[i - 1] + nums[i];

        int cnt = 0;
        for (int i = 1; i < nums.length; i++)
            for (int j = 0; j < i; j++)
                if (presum[i] - presum[j] == k)
                    cnt++;
        return cnt;

    }

    //记录下有几个preSum[j]和preSum[i] - k相等，直接更新结果，就避免了内层的 for 循环。
    //我们可以用哈希表，在记录前缀和的同时记录该前缀和出现的次数。
    // O(n)
    int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> presum = new HashMap<>();
        int presum_i = 0, presum_j = 0;
        // base case
        presum.put(0, 1);
        int cnt = 0;
        // presum[i, j]
        for (int j = 0; j < nums.length; j++) {
            presum_j += nums[j];
            presum_i = presum_j - k;
            cnt += presum.getOrDefault(presum_i, 0);
            presum.put(presum_j, presum.getOrDefault(presum_j, 0) + 1);
        }
        return cnt;
    }
}
