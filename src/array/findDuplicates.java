package array;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author xu
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 * Find all the elements that appear twice in this array.
 * Could you do it without extra space and in O(n) runtime?
 * 
 * Input:
	[4,3,2,7,8,2,3,1]
	
	Output:
	[2,3]
 * 
 * https://leetcode.com/problems/find-all-duplicates-in-an-array/#/description
 */
public class findDuplicates {

	public static void main(String[] args) {
		

	}
	/**
	 * 
	 * When loop through the array, flip the number at index i-1 to negative
	 * if the number at position i-1 is already negative, then i is the number that occurs twice
	 * 
	 */
	static List<Integer> solution(int[] arr) {
		List<Integer> duplicates = new ArrayList<>();
		for (int i=0; i<arr.length; i++) {
			int index = Math.abs(arr[i]) - 1;
			if (arr[index] < 0)
				duplicates.add(index + 1);
			
			arr[index] = -arr[index];
		}
		return duplicates;	
	}
	
	/**
	 * use hash[] to store the count of each characters
	 */
}
