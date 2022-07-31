package algorithm;

public class Kadane {
    // keep indices of the range
    // 两个参数 max_so_far and max_end_here. 一个代表至今为止最大的连续字符串， 一个代表以此截止的最大字串
    // left and right pointer : right pointer 每当max_so_far 更新时候，更新。
    // left pointer 更新需要借助变量 left_tmp, 每当 max_end_here < 0, 更新left_tmp = i + 1
    public static void kadaneAlgorithm3(int[] nums, int n) {
        int max_so_far = Integer.MIN_VALUE, max_end_here = 0;

        int left = 0, right = 0, left_tmp = 0;

        for (int i = 0; i < n; i++) {

            max_end_here += nums[i];

            if (max_so_far < max_end_here) {
                max_so_far = max_end_here;
                left = left_tmp;
                right = i;
            }
            if (max_end_here < 0) {
                max_end_here = 0;
                left_tmp = i + 1;
            }
        }
        System.out.println("max : " + max_so_far + "\n" + "range indices " + left + " " + right);
    }
}
