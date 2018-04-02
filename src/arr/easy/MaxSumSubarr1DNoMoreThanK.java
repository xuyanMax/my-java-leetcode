package arr.easy;

import java.util.TreeSet;
/*
 * 363. Max Sum of Rectangle No Larger Than K
 *
Given a non-empty 2D matrix matrix and an integer k,
find the max sum of a rectangle in the matrix such that its sum is no larger than k.

Example:
Given matrix = [
  [1,  0, 1],
  [0, -2, 3]
]
k = 2
The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2
and 2 is the max number no larger than k (k = 2).

Note:
The rectangle inside the matrix must have an area > 0.
What if the number of rows is much larger than the number of columns?
 */
public class MaxSumSubarr1DNoMoreThanK {

	public static void main(String[] args) {

		MaxSumSubarr1DNoMoreThanK ins = new MaxSumSubarr1DNoMoreThanK();
		int[] arr = new int[]{2,3,4,5,6};
		int K = 13;
		
		System.out.println(ins.best_cumulative_sum(arr, K));
	}
	
	public int best_cumulative_sum(int[] arr, int K){
	
		TreeSet<Integer> set = new TreeSet<>();//to help us find the sub-arr maxSum <= k with O(logN) time
		set.add(0);// add 0 to cover single row case
		int sum_j = 0;
		
		int maxLen = 0;
		/*
		 * Cumulative sum[i] - sum of first i elements
		 *
		 * sum[i,j] = sum [j] - sum[i]
		 * sum[i,j] is target sub-arr that needs to have sum <= K
		 * sum[j] is now cumulative sum and we will use binary search to FIND sum[i] s.t. K >= sum[j]-sum[i]
		 * =>=> sum[i](我们要求的未知数) >= sum[j](当前的累计和) - K.
		 *   
		 */
		for (int ele : arr) {
			sum_j += ele;
			Integer sum_i = set.ceiling(sum_j - K); //寻找大于等于culSum - K 的元素
			
			if (sum_i != null)
				maxLen = Math.max(maxLen, sum_j - sum_i); // culSum - presum <= K
			set.add(sum_j);
		}
		return maxLen;
	
	}
}
