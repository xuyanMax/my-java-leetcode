package arr.medium;

/**
 * 152. Maximum Product Subarray
 * Find the contiguous sub-arr within an arr (containing at least one number) which has the largest product.
 * <p>
 * For example, given the arr [2,3,-2,4],
 * <p>
 * the contiguous sub-arr [2,3] has the largest product = 6.
 * <p>
 * https://leetcode.com/problems/maximum-product-subarray/#/description
 */
public class MaxProductSubarr {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, -2, 4, -3,};
        System.out.println(solution(arr));

    }

    /**
     * require two dp[] tables to store both max_product_subarr and min_product_subarr ending with arr[i]
     * loop through the arr[], and
     */
    static int solution(int[] arr) {
        int[] dp_max = new int[arr.length];
        int[] dp_min = new int[arr.length];

        dp_max[0] = dp_min[0] = arr[0];
        int globalMax = Integer.MIN_VALUE;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] >= 0) {
                dp_max[i] = Math.max(dp_max[i - 1] * arr[i], arr[i]);
                dp_min[i] = Math.min(dp_min[i - 1] * arr[i], arr[i]);
            } else {
                dp_max[i] = Math.max(dp_min[i - 1] * arr[i], arr[i]);
                dp_min[i] = Math.min(dp_max[i - 1] * arr[i], arr[i]);
            }
            if (globalMax < dp_max[i])
                globalMax = dp_max[i];

        }
        return globalMax;

    }

}
