package dp.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author xu
 * 120. Triangle
 * 写程序查找从最高点到底部任意处结束的路径， 使路径经过数字的和最大。每一步可以走到左下方的点也可以走到右下方的点。
 * 
 * For example, given the following triangle
	[
	     [2],
	    [3,4],
	   [6,5,7],
	  [4,1,8,3]
	]
	
 * references:
 * 
 * https://leetcode.com/problems/triangle/#/description
 * https://discuss.leetcode.com/topic/1669/dp-solution-for-triangle/52
 * 
 */
public class Triangle {
	public static void main(String[] args) {
		
		// initialize the triangle 
		List<ArrayList<Integer>> triangle = inputData();
		
		minimumTotal2D(triangle);
		
	}
	/**
	 * 
	 * @param triangle
	 * 
	 * Bottom-up DP
	 * 
	 * we start from the nodes on the bottom row;
	 * the minimum pathsums for these nodes are the values of the nodes themselves
	 *
     * From there, we bottom-up and the pathsum for the ith node on the kth row would be
	 * the Math.min(k+1th two children pathsums) + val of itself
	 * 
	 * or better, since minPath[k+1][] would be useless after minPath[k][] is calculated (O(n^2)),
     * so we can simply set minPath[] to 1D arr.
	 * and iteratively update minPath[]--> O(n) space complexity
	 *
	 * like 
	 * for kth level
	 * 
	 * minPath[i] = Math.min(minPath[i], minPath[i+1]) + triangle[k][i]
	 *  
	 */
	static int minimumTotal2D(List<ArrayList<Integer>> triangle){
		
		int[][] dp = new int[triangle.size()][triangle.size()];
		List<Integer> lastRow = triangle.get(triangle.size()-1);
		
		// initialize the last row of dp table
		for (int i=triangle.size()-1,j=0; j<i; j++) 
			dp[i][j] = lastRow.get(j);
		
		for (int i=triangle.size()-2; i>=0; i--) 
			for (int j=0; j<=i; j++) // kth row has k number of items
				dp[i][j] = Math.min(dp[i+1][j], dp[i+1][j+1]) + triangle.get(i).get(j);
				
		System.out.println(dp[0][0]);
		return dp[0][0];
	}
	static int minimumTotal1D(List<ArrayList<Integer>> triangle){
		int[] dp = new int[triangle.size()];
		
		for (int i=triangle.size()-1, j=0; j<triangle.size(); j++)
			dp[j] = triangle.get(i).get(j); // 赋值最后一列数据
		
		for (int i=triangle.size()-2; i>=0;i--) 
			for (int j=0;j<=i;j++) 
				dp[j] = Math.min(dp[j+1], dp[j]) + triangle.get(i).get(j);			
		
		System.out.println("The shortest path is: " + dp[0]);
		return dp[0];
		
	}
	/*
	 * input key and transform the key into Lists of List
	 */
	static List<ArrayList<Integer>> inputData(){
		
		System.out.println("Specify the number of rows of pyramid:");
		List<ArrayList<Integer>> triangle = new ArrayList<ArrayList<Integer>>();
	
		Scanner kb = new Scanner(System.in);
		int n = kb.nextInt();
		for (int i=0;i<n;i++) {
			ArrayList<Integer> level = new ArrayList<>();
			for (int j=0; j<=i; j++) {
				 int tmp = kb.nextInt();
				 level.add(tmp);
			}
			triangle.add(level);
		}
//		System.out.println("The shortest path is: "+ triangle);
		kb.close();
		return triangle;
	}
	static void printArr(int[][]arr) {

		for(int i=0;i<arr.length;i++) {
			for (int j=0;j<arr[0].length;j++)
				System.out.print(arr[i][j]);
			System.out.println("");
		}
	}
	static void input2dArray(){
		Scanner kb = new Scanner(System.in);

		// input the total level of number pyramids
		// pad zeros on the first row, first and last columns
		
		int n = kb.nextInt();
		int[][] arr = new int[n+1][n+2];

		// hold numbers pyramids in arr[][]
		for (int i=1;i<n+1;i++) 
			for (int j=1;j<n+1;j++)
				arr[i][j] = kb.nextInt();
		
		printArr(arr);
		kb.close();
	}
}
