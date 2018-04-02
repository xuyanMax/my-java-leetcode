package arr.easy;

/**
 * 
 * 
 * @author xu
 * 26. Remove Duplicates from Sorted Array
 * Given a sorted arr, remove the duplicates "in place" such that each element appear only "once"
 * and return the new length.
 * 
 * Do not allocate extra space for another arr, you must do this in place with constant memory.
 * 
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/#/description
 */
public class RemoveDuplicatesSortedArray1 {

	
	/*
	 * sorted arr in ascending or descending order
	 * two pointers 
	 * 
	 * */
	static int solution(int[] arr) {
		int pos = 0;
		for (int j = 1; j<arr.length; j++)
			if (arr[j] != arr[j-1]) 
				arr[++pos] = arr[j];
		return pos + 1;
	}
}
