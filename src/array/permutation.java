package array;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * 
 * @author xu
 *
 * Given a collection of DISTINCT numbers, return all possible permutations.
 *
 * For example,
	[1,2,3] have the following permutations:
	[
	  [1,2,3],
	  [1,3,2],
	  [2,1,3],
	  [2,3,1],
	  [3,1,2],
	  [3,2,1]
	]
 * https://leetcode.com/problems/permutations/#/description
 */
public class permutation {

	public static void main(String[] args) {


	}
	public static List<List<Integer>> permute(int[] nums) {
		   List<List<Integer>> list = new ArrayList<>();
		   // Arrays.sort(nums); // not necessary
		   backtrack(list, new ArrayList<>(), nums);
		   return list;
		}

	private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
	   
		if(tempList.size() == nums.length) {
		   list.add(new ArrayList<>(tempList));
		   return;
		}
	   
	   else{
		   
	      for(int i = 0; i < nums.length; i++){ 
	         /* if does not include this contain check we would get 
	          * 1 1 1
	          * 1 1 2
	          * 1 1 3 
	          * 1 2 1
	          * 1 2 2 
	          * 1 2 3
	          * ....
	          * .... #27 
	          * */
    	     if(tempList.contains(nums[i])) 
    		    continue; // element already exists, skip
    	     		
	         
	         tempList.add(nums[i]);
	         
	         backtrack(list, tempList, nums);
	         
	         tempList.remove(tempList.size() - 1);
	         
	      }
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
