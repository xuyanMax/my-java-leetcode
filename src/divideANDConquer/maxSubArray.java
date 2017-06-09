package divideANDConquer;


/**
 * 
 * 
 * @author xu
 * 
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

	For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
	the contiguous subarray [4,-1,2,1] has the largest sum = 6.
	
	If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
	
 * https://leetcode.com/problems/maximum-subarray/#/description
 * https://discuss.leetcode.com/topic/426/how-to-solve-maximum-subarray-by-using-the-divide-and-conquer-approach/54
 * 
 * refer array package -> findMaxSubarr
 * 
 */
public class maxSubArray {

	public static void main(String[] args) {
		
		int[] nums = new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4};
		System.out.println(maxSubArr(nums));
		

	}
	
	// use divide and conquer to solve such a question which can also be solved by DP
	
	public static int maxSubArr(int[] nums) {
		if (nums.length == 0)
			return 0;
		return maxSubArrUtil(nums,0,nums.length-1);
	}
	
	public static int maxSubArrUtil(int[] nums, int left, int right) {
		if (left == right) return nums[right];
		int mid = (left + right) / 2;
		
		int leftMax = maxSubArrUtil(nums, left, mid); // the max sum on the mid's left including mid
		int rightMax = maxSubArrUtil(nums, mid + 1, right); // the max sum on the mid's right excluding mid
		
		int leftSum = nums[mid];
		int rightSum = nums[mid + 1];
		
		int tmp = 0;
		for (int i=mid; i>=left; i--) {
			tmp += nums[i];
			if (tmp > leftSum) leftSum = tmp;
		}
		
		tmp = 0;
		
		for (int i=mid+1; i<=right; i++) {
			tmp += nums[i];
			if (tmp > rightSum) rightSum = tmp;
			
		}
		// leftSum  + rightSum: the max sum including the mid and expanding outwards 
		return Math.max(Math.max(leftMax, rightMax), leftSum + rightSum); 
	}
	
	
	

}
