package stack;

import java.util.Arrays;
import java.util.LinkedList;

public class NextGreaterElem2_circular_array {

	/**
	 * https://leetcode.com/problems/next-greater-element-ii/#/description
	 * <p>
	 * Given a circular arr (the next element of the last element is the first element of the arr),
	 * print the Next Greater Number for every element.
	 */
	public static int[] sol(int[] nums) {
		int[] ret = new int[nums.length];
		LinkedList<Integer> stack = new LinkedList<Integer>();

		Arrays.fill(ret, -1);

		for (int i = 0; i < 2 * nums.length; i++) {

			int num = nums[i % (nums.length)];

			while (!stack.isEmpty() && nums[stack.peek()] < num) {
				ret[stack.pop()] = num;
			}

			if (i < nums.length)
				stack.push(i);
		}
		return ret;

	}

	public static int[] sol_back(int[] nums) {
		int[] ret = new int[nums.length];
		LinkedList<Integer> stack = new LinkedList<Integer>();


		return ret;
	}

}
