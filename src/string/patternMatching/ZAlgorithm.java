package string.patternMatching;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author xu
 *
 * Z algorithm to pattern matching
 *
 * Time complexity - O(n + m)
 * Space complexity - O(n + m)
 *
 * references:
 * http://www.geeksforgeeks.org/z-algorithm-linear-time-pattern-searching-algorithm/
 * http://www.utdallas.edu/~besp/demo/John2010/z-algorithm.htm
 * https://www.youtube.com/watch?v=CpZh4eF8QBw&t=359s
 * 
 */


public class ZAlgorithm {

	public static void main(String[] args) {
	
		String text = "xabcabzabc";
		String pattern = "abc";
		ZAlgorithm zAlgorithm = new ZAlgorithm();
		List<Integer> result = zAlgorithm.matchPattern(text.toCharArray(), pattern.toCharArray());
		
		for (int n:result)
			System.out.println(n);
	}
	
	public int[] calculateZArray(char[] input) {
		int[] Z = new int[input.length];

		//[left,right] make a window which matches with prefix of 
		int left=0, right=0;
		for (int k=1; k<input.length; k++) {		
			
			// if k > R nothing matches so we will calculate.
	        // Z[k] using naive way
			if (k > right) {
				left = right = k;
				
				 // R-L = 0 in starting, so it will start
	            // checking from 0'th index. For example,
	            // for "ababab" and k = 1, the value of R
	            // remains 0 (R--) and Z[k] becomes 0. For string
	            // "aaaaaa" and k = 1, Z[k] and R become 5
				while (right < input.length && input[right] == input[right-left])
					right++;
				
				Z[k] = right-left;
				right--; // go back to its right boundary
				
			} else {
				 // k1 = k-L so k1 corresponds to number which
	            // matches in [L,R] interval.
				// we are operating inside box
				int k1 = k - left;
				
				// if Z[k1] is less than remaining interval
	            // then Z[k] will be equal to Z[k1].
				if (Z[k1] + k < right + 1) {
					Z[k] = Z[k1];
				} else {

					left = k;
					
					 //  else start from Right  and check manually
					while (right < input.length && input[right] == input[right-left]) 
						right++;
					Z[k] = right-left;
					right--;
				}
			}
		}
		return Z;
		
		
	}
	
	public List<Integer> matchPattern(char[] text, char[] pattern) {
		char[] input = new char[text.length+pattern.length+1];
		int i=0;
		for (char c:pattern){
			input[i] = c;
			i++;
		}
		input[i] = '$';
		i++;
		
		for (char c:text) {
			input[i]=c;
			i++;
		}
		int[] Z = calculateZArray(input);
		
		List<Integer> result = new ArrayList<>();
		
		for (int j=0;j<Z.length;j++) {
			if (Z[j] == pattern.length)
				result.add(j-pattern.length-1);
		}
		
		return result;
	}

}
