package array;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author xu
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
public class combinationSum3 {

	public static void main(String[] args) {
			
		List<List<Integer>> result = solution(3, 7);
		printListList(result);

	}
	public static List<List<Integer>> solution(int k, int n) {
		List<List<Integer>> result = new ArrayList<>();
		
		solutionUtil(result, new ArrayList<>(), n, k, 1);
		
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
	static <T> void printListList(List<List<T>> result) {

		Iterator<List<T>> it = result.iterator();
		while (it.hasNext()) {
			List<T> tmp = (List<T>) it.next();
			for (T s: tmp)
				System.out.print(s+" ");
			System.out.println("");
		}
	}

}
