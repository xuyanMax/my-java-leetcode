package hashtable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ContinuousSubarraySum {

	public static void main(String[] args) {
		

	}
	/*
	 * This time we could just use Set<Integer> instead of Map
	 */
	 public boolean checkSubarraySum(int[] nums, int k) {
	        // Since the size of subarray is at least 2.
		 if (nums.length < 2)
			 return false;
		 // Two continuous "0" will form a subarray which has sum = 0. 0 * k == 0 will always be true. 
		 for (int i=0; i<nums.length-1; i++)
			 if (nums[i] ==0 && nums[i+1] ==0) // k = 0 and n can be any integer
				 return true;
		 // after above, k can't be "0"
		 if (k==0)
			 return false;
		 
		  // Let's only check positive k. Because if there is a n makes n * k = sum, it is always true -n * -k = sum.
		 if (k<0)
			 k = -k;
		 int presum=0;
//		 Map<Integer, Integer> maps = new HashMap<>();
//		 maps.put(0,1);
		 Set<Integer> set = new HashSet<>();
		 set.add(0);
		 for (int i=0; i<nums.length; i++) {
			 presum += nums[i];
			 
			 if (i>0) { // size of at least 2
			 // Validate from the biggest possible n * k to k
				 for (int j= (presum/k)*k; j>=k; j-=k){
//					 if (maps.containsKey(presum - j))
					 if(set.contains(presum - j))
						 return true;
				 }
			 }
//			 maps.put(presum, 1);
			 set.add(presum);
		 }
		 return false;
	 }
}
