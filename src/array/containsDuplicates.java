package array;

/**
 * 
 * @author xu
 * 
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class containsDuplicates {

	public static void main(String[] args) {
		int[] nums = new int[]{1,2,3,4,5,5,6};
		System.out.println(solution(nums));
	}
	
	static boolean solution(int[] nums) {
       
        //// Time complexity O(N*LOG(N)) Space complexity O(1)
        // first sort array by ascending order and compare the adjacent ones
        Arrays.sort(nums);
        for (int i=0;i<nums.length-1;i++) {
            if(nums[i]==nums[i+1]) return true;
        }
        return false;
	}
	static boolean solution2(int[] nums) {

		//// time complextiy O(N*N)
         for (int i=0;i<nums.length;i++) {
             for (int j=i+1; j<nums.length;j++) {
                 if (nums[i]==nums[j]) return true;
             }
         }
         return false;
		
	}
	static boolean solution3(int[] nums) {

		//// hashset Time complexity: O(N), Space complexity O(N)
         if (nums.length==0 || nums==null) return false;
        
         HashSet<Integer> noDuplicate = new HashSet<>();
         
         for (int num:nums) 
             if(!noDuplicate.add(num))
            	 return true;
         
         return false;
		
	}


}
