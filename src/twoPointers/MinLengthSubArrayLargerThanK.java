package twoPointers;

import java.util.Arrays;

/**
 * 
 * @author xu
 * 
 * package Array -> minSizeSubarrEqualK.java
 * 
 */
public class MinLengthSubArrayLargerThanK {

	public static void main(String[] args) {
		
		int[] nums = new int[] {2,3,1,2,2,4, 5,6,8, 9};
		// 测试 binary search to find the next smaller value of a key 
		Arrays.sort(nums);
		System.out.println(binarySearchNextSmaller(0, nums.length-1, 7, nums));
		System.out.println(binarySearchNextLarger(0, nums.length-1, 7, nums));
		int[] nums2 = new int[] {1,2,3,4,5}; // 0 1 3 6 10 15
		int[] nums3 = new int[] {2,3,1,2,4,3}; // 0 2 5 6 8 12 15
//		System.out.println(minSubArrayLen(nums2, 7));
//		System.out.println(minSubArrayLenNlogN(nums 3, 7));
	}
	
	/* O(n) time complexity
	
	* two pointers 
	* 
	*/
	public static int minSubArrayLen (int[] nums, int s) {
		int minLen = Integer.MAX_VALUE;
		
		int sum = 0;
		int left = 0, right = 0;
		while (right < nums.length) {
			sum += nums[right]; // modify sum here
			right++;			// increase right pointer
			
			while (sum >= s) { // sum condition
				
				minLen = Math.min(minLen, right - left); // update minLen here is finding minimum
				sum -= nums[left]; // increase left to MAKE it invalid
				left++;
				
			}
		}
		return minLen == Integer.MAX_VALUE ? 0 : minLen;
	}
	/**
	 * O(n*lgn) complexity algorithm using binary search 
	 */
	public static int minSubArrayLenNlogN(int[] arr, int key) {
		
		int n = arr.length;
		int[] sums = new int[n + 1];
		for (int i=1; i<=n; i++) {
			sums[i] = sums[i-1] + arr[i - 1];//0 1 3 6 10 15
		}
		int minSize = Integer.MAX_VALUE;
		for (int i=0; i<n; i++) {
			int index = binarySearchNextLarger(i + 1, n, sums[i] + key, sums);
			
			if (index == - 1) continue;
//			if (index == sums.length) break; // 无匹配项 且 i+1此时等于 n
			
			minSize = Math.min(minSize, index - i);	
		}
		return minSize;
		
	}
	private static int binarySearchNextLarger (int lo, int hi, int key, int[] arr) {
		
		if (arr[hi] < key) return -1;
		
		while (lo <= hi) {
			int mid = lo + (hi - lo)/2;
			if (arr[mid] >= key) {
				hi = mid - 1;
			}
			else 
				lo = mid + 1;
//			System.out.println(lo + " "+ hi);
		}
		return lo;
	}
	 private static int binarySearchNextSmaller (int lo, int hi, int key, int[] arr) {
	   
	    	while (lo <= hi) {
	    		int mid = lo + (hi - lo)/2;
	    		if (arr[mid] >= key) {    			
	    			hi = mid - 1;
	    		}
	    		else
	    			lo = mid + 1;
//	    		System.out.println(lo + " "+ hi);
	    	}
	    	
	    	return arr[hi];
	    }
}
