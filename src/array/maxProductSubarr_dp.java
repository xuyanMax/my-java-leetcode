package array;

/**
 * 
 * Find the contiguous sub-array within an array (containing at least one number) which has the largest product.
 * 
 * For example, given the array [2,3,-2,4],
 * 
 * the contiguous sub-array [2,3] has the largest product = 6.
 * 
 * https://leetcode.com/problems/maximum-product-subarray/#/description
 * 
 */
public class maxProductSubarr_dp {

	public static void main(String[] args) {
		int[] arr = new int[] {2,3,-2,4,-3,};
		System.out.println(solution(arr));

	}
	/**
	 * 
	 * require two dp[] tables to store both max_product_subarr and min_product_subarr ending with arr[i]
	 * loop through the arr[], and 
	 */
	static int solution(int[] arr) {
		int[] dp_max = new int[arr.length];
		int[] dp_min = new int[arr.length];
		
		dp_max[0] = dp_min[0] = arr[0];
		int globalMax = Integer.MIN_VALUE;
		for (int i=1; i<arr.length; i++) {
			if (arr[i]>=0) { 
				dp_max[i] =  Math.max(dp_max[i-1] * arr[i], arr[i]);
				dp_min[i] =  Math.min(dp_min[i-1] * arr[i], arr[i]);
			}
			else {
				dp_max[i] =  Math.max(dp_min[i-1] * arr[i], arr[i]);
				dp_min[i] =  Math.min(dp_max[i-1] * arr[i], arr[i]);
			}
			if (globalMax < dp_max[i])
				globalMax = dp_max[i];
				
		}
		return globalMax;
		
	}

}
