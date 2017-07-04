package array;

import java.util.Arrays;

/**
 * 
 * @author xu
 * 
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

	For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
	the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 * 
 * https://leetcode.com/problems/maximum-subarray/#/description
 * http://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
 * 
 * refer divide and conquer package -> maxSubArray
 * 
 */
public class FindMaxSubarr {

	public static void main(String[] args) {
		
		int[] arr =  new int[]{1,2,3,-3,2};
		System.out.println(solution(arr));
		System.out.println(kadaneAlgorithm(arr, arr.length));
		kadaneAlgorithm3(arr, arr.length);

	}
    // sub-problem: dp[i] denotes the max sub array that ends with nums[i]
    // the relation between the two steps is: dp[i] = nums[i] + Math.max(0, dp[i-1 ]);
    // Use parameter globalMax to store the maximum value along the whole process.
    // or we could Arrays.sort(dp[0-n-1]) and return the dp[n-1];
	
	// time complexity best : O(n) if don't use sort algorithm
	static int solution(int[] arr) {
		int[] max_end_here_dp = new int[arr.length];
		max_end_here_dp[0] = arr[0];
		
		for (int i=1; i<arr.length; i++) 
			max_end_here_dp[i] =  Math.max(arr[i], arr[i] + max_end_here_dp[i-1]);
		
		Arrays.sort(max_end_here_dp);
	
		return max_end_here_dp[max_end_here_dp.length-1];
		
	}
	
	// time complexity - O(n)

	public static int kadaneAlgorithm(int[] nums, int n) {
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
	public static int kadaneAlgorithm2(int[] nums, int n) {
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
	// keep indices of the range
	// 两个参数 max_so_far and max_end_here. 一个代表至今为止最大的连续字串， 一个代表以此截止的最大字串
	// left and right pointer : right pointer 每当max_so_far 更新时候，更新。
	// left pointer 更新需要借助变量 left_tmp, 每当 max_end_here < 0, 更新left_tmp = i + 1
	public static void kadaneAlgorithm3(int[] nums, int n) {
		int max_so_far = Integer.MIN_VALUE, max_end_here = 0;
		
		int left = 0, right = 0, left_tmp = 0;
		
		for (int i=0; i<n; i++) {
			
			max_end_here = max_end_here + nums[i];
			
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
