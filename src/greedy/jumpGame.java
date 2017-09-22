package greedy;
/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.

*/

public class jumpGame {

	public static void main(String[] args) {
		System.out.println(jumpGame1(new int[]{2,5,0,0}));
		System.out.println(jumpGame1(new int[]{0,1}));

	}
	public static boolean jumpGame1(int[] nums) {

	    int maxReachSoFar = 0;
        int maxReachStartHere = 0;

		for (int i=0; i<nums.length && i<=maxReachSoFar; i++) {
            maxReachStartHere = i + nums[i];
			maxReachSoFar = Math.max(maxReachStartHere, maxReachSoFar);
		}
		if (maxReachSoFar >= nums.length-1)
			return true;
		else 
			return false;
	}
	public static boolean jumGame2(int[] nums) {
		int maxStep = 0;
		int i=0;
		for (; i<nums.length && i + maxStep < nums.length;) {
			if (i == nums.length - 1) // [0]
				return true;
			maxStep = Math.max(nums[i], maxStep);
	
			if (maxStep == 0 && i <nums.length)
                return false; // [0, 1]
			i++;
			maxStep--;
		}
		if (i + maxStep >= nums.length-1 || i >= nums.length-1)
			return true;
		else 
			return false;
	}

}
