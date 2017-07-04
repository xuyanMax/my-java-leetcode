package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * 
 * You may assume that the array is non-empty and the majority element always exist in the array.
 * 
 * @author xu
 *
 * https://leetcode.com/problems/majority-element/#/description
 * 
 * https://discuss.leetcode.com/topic/17446/6-suggested-solutions-in-c-with-explanations
 * 	
 * randomization
 * hash
 * divide & conquer
 * 
 */
public class MajorityElement_1 {

	public static void main(String[] args) {

		int[] nums = new int[] {1,2,1,3,1,4,1,5,1,6,1};
		System.out.println(solutionTrick(nums));
		System.out.println(majorityVote(nums));
		System.out.println(majorityHash(nums));
		System.out.println(majorityRandom(nums));
		System.out.println(majorityDivideAndConquer(nums));
	}
	
	/* Since always has the majority element we could do this..*/
	static int solutionTrick(int[] arr) {
		Arrays.sort(arr);
		return arr[arr.length/2];
		
		
	}
	
	/* A linear time majority vote algorithm
	 * it uses majority and counter
	 * initially, majority = nums[0] and counter = 1 
	 * 
	 * refer: http://www.cs.utexas.edu/~moore/best-ideas/mjrty/index.html
	 * 
	 * */
	static int majorityVote(int[] arr) {
		int majority = arr[0];
		int counter = 1;
		for (int i=1; i<arr.length; i++) {
			if (counter==0) {
				counter = 1;
				majority = arr[i];
			} 
			else if (arr[i] == majority) {
				counter++;
			}  
			else 
				counter--;
		}
		return majority;
		
	}
	// maintain a hash table, mapping from integers to their #occurrences
	public static int majorityHash(int[] nums) {
		
		Map<Integer, Integer> maps = new HashMap<>();
		for (int n : nums) {
			maps.put(n, maps.getOrDefault(n, 0) + 1);
			if (maps.get(n) > nums.length/2)
				return n;
		} 
			return 0;
	}
	
	// randomization
	public static int majorityRandom (int[] nums) {
		
		int len = nums.length;
		Random random = new Random();
		
		
		while (true) {
			int index = random.nextInt(len);
			int candidate = nums[index];
			int count = 0;
			for (int n : nums)
				if(candidate == n)
					count++;
			if (count > len/2)
				return candidate;
		}
	}
	public static int majorityDivideAndConquer (int[] nums) {
		if (nums.length == 0)
			return 0;
		
		return majorityDivideAndConquerUtil(nums, 0, nums.length-1);
	}
	
	/*
	 * This idea is very algorithmic. However, the implementation of it requires 
	 * some careful thought about the base cases of the recursion. 
	 * 
	 * The base case is that when the array has only one element, then it is the majority one. 
	 */
	public static int majorityDivideAndConquerUtil (int[] nums, int left, int right) {
			
			if (left == right) // base case: when the arr has only one element, it is the MAJORITY
				return nums[left];
			int mid = (left + right) / 2;
			int leftMajority = majorityDivideAndConquerUtil(nums, left, mid);
			int rightMajority = majorityDivideAndConquerUtil(nums, mid+1, right);
			// if equal return
			if (leftMajority == rightMajority)
				return leftMajority;

			// if not equal, compare
			int leftMajorityCount = helperCountFrequency(nums, leftMajority, left, right);
			int rightMajorityCount = helperCountFrequency(nums, rightMajority, left, right);
			
			return leftMajorityCount >= rightMajorityCount ? leftMajorityCount : rightMajorityCount;
			
		}
	

	public static int helperCountFrequency (int[] nums, int target, int left, int right) {
		int count = 0;
		for (int i=left; i<=right; i++) {
			if (nums[i] == target)
				count++;
		}
		return count;
	}
	
	

}
