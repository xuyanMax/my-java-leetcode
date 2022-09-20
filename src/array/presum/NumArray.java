package array.presum;

/**
 * Created by xu on 2022/9/20 09:19.
 * lc 303 区域和检索 - 数组不可变」，让你计算数组区间内元素的和，这是一道标准的前缀和问题：
 */
public class NumArray {
    private int[] presum;

    public NumArray(int[] nums) {
        this.presum = new int[nums.length + 1];
        //nums 3 5 2
        //presum 0 3 8 10
        for (int i = 1; i < presum.length; i++)
            presum[i] = presum[i - 1] + nums[i - 1];
    }

    public int sumRange(int left, int right) {
        // right included
        return presum[right + 1] - presum[left];
    }
}
