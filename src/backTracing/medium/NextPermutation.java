package backTracing.medium;

/**
 * 31. Next Permutation
 * <p>
 * Implement next permutation, which rearranges numbers into the lexicographically next greater
 * permutation of numbers.
 * <p>
 * If such arrangement is not possible, it must rearrange it as the lowest possible order
 * (ie, sorted in ascending order).
 * <p>
 * The replacement must be in-place, do not allocate extra memory.
 * <p>
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are
 * in the right-hand column.
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * <p>
 * 14253
 * 14325
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        if (nums.length <= 1)
            return;

        int i = nums.length - 1;
        for (; i >= 1; i--)
            if (nums[i - 1] < nums[i]) break;

        // 第一个数字是nums[i-1],需要与i:end中从后向前数第一个大于nums[i-1]的元素交换
        if (i != 0) {
            int j = nums.length - 1;
            for (; j >= i; j--)
                if (nums[j] > nums[i - 1])
                    break;

            swap(nums, i - 1, j);
        }

        reverse(nums, i, nums.length - 1);// i-n做一次最小数变换
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public void reverse(int[] nums, int lo, int high) {
        while (lo < high) {
            swap(nums, lo++, high--);
        }
    }
}
