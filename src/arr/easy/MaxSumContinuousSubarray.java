package arr.easy;

/**
 * 
 * @author xu
 * 53. Maximum Subarray
 * Find the contiguous subarray within an arr (containing at least one number) which has the largest sum.

	For example, given the arr [-2,1,-3,4,-1,2,1,-5,4],
	the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 * 
 * https://leetcode.com/problems/maximum-subarray/#/description
 * 
 * refer divide and conquer package -> maxSubArray
 * 
 */
public class MaxSumContinuousSubarray {

    // dynamic[i] denotes the max sub arr that ends with nums[i]
	static int solution(int[] arr) {
		int[] dp = new int[arr.length];
		dp[0] = arr[0];
		// max = MIN 程序将报错 [-1 -2]
		int max = dp[0];
		
		for (int i=1; i<arr.length; i++) {
			dp[i] = Math.max(arr[i], arr[i] + dp[i - 1]);
			max = Math.max(dp[i], max);
		}
	
		return max;
		
	}
	
	// time complexity - O(n)
	public int KadaneAlgorithm(int[] nums, int n) {
		int maxSoFar = Integer.MIN_VALUE;
		int maxEndingHere = 0;
		for (int i=0; i<n; i++) {
			maxEndingHere = maxEndingHere + nums[i];
			if (maxSoFar < maxEndingHere) 
				maxSoFar = maxEndingHere;
			if (maxEndingHere < 0)
				maxEndingHere = 0;
			} 
		return maxSoFar;
	}
	// optimize the above algorithm: We compare maxSoFar and maxEndHere only if maxEndHere is larger than 0
	public int KadaneAlgorithm2(int[] nums, int n) {
		int max_so_far = 0, max_end_here = 0;
		for (int i=0; i<n; i++) {
			max_end_here = max_end_here + nums[i];
			if (max_end_here < 0)
				max_end_here = 0 ;
			else {
				if (max_end_here > max_so_far)
					max_so_far = max_end_here;
			}
		}
		return max_so_far;
	}

	
	
}
