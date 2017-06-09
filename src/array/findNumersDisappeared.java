package array;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author xu
 * 
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 * Find all the elements of [1, n] inclusive that do not appear in this array.
 * 
 * Do it without extra space and in O(n) runtime
 */

public class findNumersDisappeared {

	public static void main(String[] args) {
		

	}
	/**
	 * 
	 * negate each element and pick out the positive ones as the disappeared integers
	 * 
	 */
	static List<Integer> solution(int[] arr) {
		
		List<Integer> disappeared = new ArrayList<>();
		for (int i=0; i<arr.length; i++) {
			int index = Math.abs(arr[i]) - 1; 
			if (arr[index] > 0)
				arr[index] = - arr[index];
		}
		
		for (int j=0; j<arr.length; j++) 
			if (arr[j] > 0 )
				disappeared.add(j + 1);
		
		
		return disappeared;
	}

}
