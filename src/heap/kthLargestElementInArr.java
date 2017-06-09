package heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 
 * @author xu
 * 
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

	For example,
	Given [3,2,1,5,6,4] and k = 2, return 5.
	
	Note: 
	You may assume k is always valid, 1 ≤ k ≤ arr
	
 *
 */
public class kthLargestElementInArr {

	public static void main(String[] args) {
		

	}
	// use built-in sort algorithm and return the kth 
	// use any sort algorithm sorting in descending order.
	public static int findKthLargestSort(int[] nums, int k) {
		Arrays.sort(nums);
		return nums[nums.length - k];
	}
	
	public static int findKthLargestPriorityQueue (int[] nums, int k) {
		
		PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare (Integer a, Integer b) {
				
				return Integer.compare(b, a);
				
			}
		});
		for (int n : nums) {
			queue.add(n);
			if (queue.size() > k) 
				queue.poll();
			
		}
		return queue.peek();
		
	}
	
	
}
