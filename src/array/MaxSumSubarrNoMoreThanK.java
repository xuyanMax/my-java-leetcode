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
		int culSum = 0;
		
		int nextLarger = 0;
		/*
		 * sum[i,j] = sum [i] - sum[j]
		 * sum[i,j] is target sub-array that needs to have sum <= K
		 * sum[i] is know cumulative sum and we will use binary search to find sum[j] s.t. K >= sum[i]-sum[j]
		 * sum[j] >= sum[i] - K.
		 *   
		 */
		for (int ele : arr) {
			culSum += ele;
			// we use subtraction culSum - sum[in tree set] <= K
			// That is we need to look for the next smaller number num of K, num = culSum - presum <= K => culSum - k <= presum  
			Integer presum = set.ceiling(culSum - K); //寻找大于等于culSum - K 的元素
			
			if (presum != null) 
				nextLarger = Math.max(nextLarger, culSum - presum); // culSum - presum <= K
			set.add(culSum); 
			
		}
		return nextLarger;
	
	}
}
