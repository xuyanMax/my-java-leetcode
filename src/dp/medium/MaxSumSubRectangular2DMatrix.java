package dp.medium;

import java.util.concurrent.ConcurrentHashMap;

/*
 * Date 07/31/2014
 * @author tusroy
 * 
 * Write a program to find maximum sum rectangle in give 2D matrix.
 * Assume there is at least one positive number in the 2D matrix.
 * 
 * Solution:
 * Keep temp arr with size as number of rows. Start left and right from 0
 * and keep adding values for each row and maintain them in this temp arr.
 * Run Kadane's algorithm to find max sum subarray in temp. Now increment right by
 * 1. When right reaches last column reset right to 1 and left to 1.
 * 
 * Space complexity of this algorithm is O(row)
 * Time complexity of this algorithm is O(row*col*col)
 * 
 * References
 * http://www.geeksforgeeks.org/dynamic-programming-set-27-max-sum-rectangle-in-a-2d-matrix/
 */
public class MaxSumSubRectangular2DMatrix {

	public static void main(String[] args) {
		MaxSumSubRectangular2DMatrix saw = new MaxSumSubRectangular2DMatrix();
		int[][] input = new int[][]{
			{ 2,  1, -3, -4,  5},
            { 0,  6,  3,  4,  1},
            { 2, -2, -1,  4, -5},
            {-3,  3,  1,  0,  3}};
          Result result = saw.maxSum(input);
          System.out.println(result);

	}
	class Result{
		int maxSumSoFar;
		int maxLeft;
		int maxRight;
		int maxUp;
		int maxBottom;
		
		@Override
		public String toString(){
			return "Result[maxSumSoFar=" + maxSumSoFar + ", maxLeft="+maxLeft + ", maxRight="+maxRight + 
					", maxUp="+maxUp+", maxBottom="+maxBottom+"]";
		}
	}
	class KadaneResult{
		int maxSumSoFar;
		int start;
		int end;		
		public KadaneResult(int maxSum, int maxL, int maxR){
			this.maxSumSoFar = maxSum;
			this.start = maxL;
			this.end = maxR;
		}
	}
	public Result maxSum(int[][] input){

        ConcurrentHashMap hm = new ConcurrentHashMap();
		Result result = new Result();
		int row = input.length;
		int col = input[0].length;
		int[] dp = new int[row];
		
		for (int left=0; left<col; left++) {
			for (int j=0; j<row; j++)
				dp[j] = 0; //初始化为0
			
			for (int right = left; right < col; right++){
				for (int i=0; i<row; i++)
					dp[i] += input[i][right];
				
				KadaneResult kadaneResult = KadaneAlgorithm(dp);
				
				if (kadaneResult.maxSumSoFar > result.maxSumSoFar) {
					result.maxSumSoFar = kadaneResult.maxSumSoFar;
					result.maxUp = kadaneResult.start;
					result.maxBottom = kadaneResult.end;
					result.maxLeft = left;
					result.maxRight = right;
				}
			}
		}
		return result;
	}
	private KadaneResult KadaneAlgorithm(int[] arr) {
		int maxSoFar = 0;
		int maxEndHere = 0;
		
		int start = -1;
		int curr_start = 0;
		
		int end = -1;
		for (int i=0; i<arr.length; i++) {
			maxEndHere += arr[i];
			if (maxEndHere > maxSoFar){
				maxSoFar = maxEndHere;
				end = i;
				start = curr_start;
			} else {
				curr_start = curr_start + 1;
				maxEndHere = 0;
			}
		}
		return new KadaneResult(maxSoFar, start, end);
		
	}

}
