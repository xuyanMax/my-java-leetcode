package stack;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class NextGreaterElem {

	public static void main(String[] args) {

	}
	/**
	 * 
	 * example 9,8,7,4,3,2,1,6
	 * use stack we keep a decreasing sub-sequence.
	 * whenever while, we see a input x larger than stack.peek() we remove the it 
	 * Then the next greater of those removed elements is x. Those still in the stack don't have next larger.
	 * 
	 * @findNums is a sub-sequence of @nums
	 */
	public static int[] sol(int[]findNums, int[] nums){
		LinkedList<Integer> stack = new LinkedList<Integer>();
		
		Map<Integer, Integer> maps = new HashMap<>();// stores elem -> next larger number
		
		int[] ret = new int[findNums.length];// 
		for (int i=0; i<nums.length; i++) {
			while (!stack.isEmpty() && stack.peek() < nums[i])
				maps.put(stack.pop(), nums[i]);
			
			stack.push(nums[i]);
		}
		for (int i=0; i<findNums.length; i++) 
			ret[i] = maps.getOrDefault(findNums[i], -1);
		
		return ret;
	}
}
