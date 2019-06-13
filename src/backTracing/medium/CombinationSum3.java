package backTracing.medium;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author xu
 * 216. Combination Sum III
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers
 * from 1 to 9 can be used and each combination should be a unique set of numbers.
 *
 * Example 1:

	Input: k = 3, n = 7
	
	Output:
	
	[[1,2,4]]
	
	Example 2:
	
	Input: k = 3, n = 9
	
	Output:
	
	[[1,2,6], [1,3,5], [2,3,4]]
	
 * https://leetcode.com/problems/combination-sum-iii/#/description
 */
public class CombinationSum3 {

	public static List<List<Integer>> solution(int k, int n) {
		List<List<Integer>> result = new ArrayList<>();
		
		solutionUtil(result, new ArrayList<>(), n, k, 1);//pos:1-9
		
		return result;
	
	}
	
	private static void solutionUtil (List<List<Integer>> resultList, List<Integer> aResult, 
			int targetRemain, int kRemain, int pos) {
		if (targetRemain < 0 )
			return;
		
		if (targetRemain == 0 && kRemain == 0) {
			resultList.add(new ArrayList<>(aResult));
			return;
		}
		
		for (int ipos=pos; ipos < 10; ipos++) {
//			if (aResult.contains(ipos))
//				continue;
			aResult.add(ipos);
			
			solutionUtil(resultList, aResult, targetRemain - ipos, kRemain - 1, ipos + 1);
			
			aResult.remove(aResult.size() - 1);
		}
	}

	//todo: optimize solution??
}
