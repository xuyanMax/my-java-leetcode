package TwoPointers;

/**
 * 
 * @author xu
 * 
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, 
 * compute how much water it is able to trap after raining.

	For example, 
	Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
	

 * https://leetcode.com/problems/trapping-rain-water/#/description
 * 
 */
public class trappingTrainWater {

	public static void main(String[] args) {
		

	}
	// two pointers left, right
	// keep track of maximum height from both forward direction and backward direction, call them leftMax and rightMax.
	// instead of computing area by height*width, think it in a cumulative way. 
	// search from both side of the array and keep the leftMax and rightMax and pick the smaller one 
	
	public static int solution(int[] height) {
		int left = 0, right = height.length - 1;
		int leftMax = 0, rightMax = 0;
		int area = 0;
		while (left <= right) {
			leftMax = Math.max(leftMax, height[left]);
			rightMax = Math.max(rightMax, height[right]);
			
			if (leftMax < rightMax) {
				area += leftMax - height[left];
				left++;
			}else {
				area += rightMax - height[right];
				right--;
			}
					
		}
		return area;
	}
	public static int solution2 (int[] height) {
		int left = 0, right = height.length - 1;
		int rightMax = 0, leftMax = 0;
		int area = 0;
		while (left <= right) {
			if (height[left] < height[right]) {
				leftMax = Math.max(leftMax, height[left]);
				area += leftMax - height[left];
				left++;
			}
			else {
				rightMax = Math.max(rightMax, height[right]);
				area += rightMax - height[right];
				right--;
			}
		}
		return area;
	} 
}
