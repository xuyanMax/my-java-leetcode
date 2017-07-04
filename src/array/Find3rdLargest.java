package array;

import java.util.function.IntPredicate;

/**
 * 
 * @author xu
 * Given a non-empty array of integers, return the third maximum number in this array. 
 * If it does not exist, return the maximum number. The time complexity must be in O(n).
 * 
 */
public class Find3rdLargest {

	public static void main(String[] args) {
	
		int[] arr = new int[]{1,2,3,4,5,6};
		System.out.println(solution(arr));
	}
	static int solution(int[] arr) {
		long max = Long.MIN_VALUE;
		long min = max, mid = max;
		
		for (int n : arr) {
			if (n > max) {
				max = n;
				mid = max;
				min = mid;				
			}
			else if ( n < max && n > mid)
				mid = n;
			else if (n > min && n < mid)
				min = n;
		}
		return (int) (min != Long.MIN_VALUE ? min : max);
	}
 }
