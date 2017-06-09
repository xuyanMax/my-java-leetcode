package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author xu
 *
 * Given a set of candidate numbers (C) (WITHOUT DUPLICATES) and a target number (T), 
 * 
 * find all UNIQUE combinations in C where the candidate numbers sums to T.
 * 
 * The same repeated number may be chosen from C unlimited number of times.'
 * 
 * 2,3,5,7
 * - 2 2 3
 * - 2 5
 * - 7
 * 
 */
public class combinationSum1 {

	public static void main(String[] args) {
		int[] candidates = new int[] {2,3,5,7};
		int target = 8;
		List<List<Integer>> result = combinationSum(candidates, target);
		for (List<Integer> list : result)
			System.out.println(list);
		
	}
	
	static List<List<Integer>> combinationSum(int[] candidates, int target) {
	    	
		Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        combinationSumUtil(result, new ArrayList<Integer>(), candidates, target, 0);
	        
        return result;
	    }
	    
    private static void combinationSumUtil(List<List<Integer>> result, List<Integer> cur, int candidates[], int target, int left){
    	
    	if (target < 0) return;
    	
    	else if(target == 0)
    		result.add(new ArrayList<Integer>(cur));
    	
    	else {
  
    		for(int pos = left; pos < candidates.length && target >= candidates[pos]; pos++){
    			
    			cur.add(candidates[pos]);
    			
    			combinationSumUtil(result, cur, candidates, target - candidates[pos], pos); // same number is allowed
    			
    			cur.remove(cur.size() - 1); // unmake it
    			
    		}
    	}
    
    	
    }

}
