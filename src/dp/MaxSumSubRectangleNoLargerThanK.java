package dp;

import java.util.TreeSet;
/*
 * reference:
 * 
 * https://discuss.leetcode.com/topic/48875/accepted-c-codes-with-explanation-and-references
 * 
 * https://leetcode.com/problems/max-sum-of-sub-matrix-no-larger-than-k/#/description
 */
/*
Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix
such that its sum is no larger than k.

Example:
Given matrix = [
  [1,  0, 1],
  [0, -2, 3]
]

k = 2
The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 is the max number
no larger than k (k = 2).

Note:
The rectangle inside the matrix must have an area > 0.
********************************************************************************
What if the number of rows is much larger than the number of columns?
********************************************************************************
*/
public class MaxSumSubRectangleNoLargerThanK {

	public static void main(String[] args) {
		

	}
	public int maxSumSubmatrix(int[][] matrix, int k) {
	    //2D Kadane algorithm + 1D MaxSumSubarrayNoMoreThanK
	    //2D subarray sum solution

	    if(matrix.length == 0) return 0;
	    
	    int ROW = matrix.length, COL = matrix[0].length;
	    int result = Integer.MIN_VALUE;
	    
	    //outer loop should use smaller axis
	    //now we assume we have more rows than cols, therefore outer loop will be based on cols 
	    for(int left = 0; left < COL; left++){
	        //accumulate sums for each ROW from left-most col to right-most col
	        int[] sums = new int[ROW];

	        for(int right = left; right < COL; right++){
	            //update sums[] to include values in curr right col

	            for(int i = 0; i < ROW; i++){
	                sums[i] += matrix[i][right];
	            }
	            
	            //we use TreeSet to help us find the rectangle with maxSum <= k with O(logN) time
	            TreeSet<Integer> set = new TreeSet<Integer>();
	            //add 0 to cover the single row case
	            set.add(0);
	            int sum_j = 0;
	            
	            for(int sum : sums){
	                sum_j += sum;
	                Integer sum_i = set.ceiling(sum_j - k);
	                if(sum_i != null)
	                    result = Math.max( result, sum_j - sum_i );
	                set.add(sum_j);
	            }
	        }
	    }
	    return result;
	}

}
