package array;

import java.util.TreeSet;
/*
 * reference:
 * 
 * https://discuss.leetcode.com/topic/48875/accepted-c-codes-with-explanation-and-references/44
 * https://www.quora.com/Given-an-array-of-integers-A-and-an-integer-k-find-a-subarray-that-contains-the-largest-sum-subject-to-a-constraint-that-the-sum-is-less-than-k
 */
public class MaxSumSubarrNoMoreThanK {

	public static void main(String[] args) {

		MaxSumSubarrNoMoreThanK ins = new MaxSumSubarrNoMoreThanK();
		int[] arr = new int[]{2,3,4,5,6};
		int K = 13;
		
		System.out.println(ins.best_cumulative_sum(arr, K));
	}
	
	public int best_cumulative_sum(int[] arr, int K){
	
		TreeSet<Integer> set = new TreeSet<>();//to help us find the sub-array maxSum <= k with O(logN) time
		set.add(0);// add 0 to cover single row case
		int sum_j = 0;
		
		int maxLen = 0;
		/*
		 * Cumulative sum[i] - sum of first i elements
		 *
		 * sum[i,j] = sum [j] - sum[i]
		 * sum[i,j] is target sub-array that needs to have sum <= K
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
