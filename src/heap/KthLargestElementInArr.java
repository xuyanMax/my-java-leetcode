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
public class KthLargestElementInArr {

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
	/*快速排序算法 逆序*/
    public static int quickSort(int[] arr, int k) {

        int left = 0, right = arr.length;
        while (left < right) {
            int pivot = partition(arr, left, right);

            if (pivot > k-1) right = pivot - 1;
            else if (pivot < k - 1) left = pivot + 1;
            else return arr[pivot];
        }
        return arr[left];
    }
    public static int partition(int[]arr, int left, int right) {

        int pivotKey = arr[left];

        while (left < right) {
            while (arr[right] <= pivotKey && right > left)
                right--;
            swap(arr[right], pivotKey);

            while (arr[left] >= pivotKey && right > left)
                left++;
            swap(arr[left], pivotKey);

        }
        return left;
    }
    public static void swap(int a, int b) {
        int tmp = a;
        a = b;
        b = tmp;
    }
	
	
}
