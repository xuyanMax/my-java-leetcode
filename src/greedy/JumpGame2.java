package greedy;

import java.util.ArrayList;
import java.util.List;

public class JumpGame2 {

	public static void main(String[] args) {
	JumpGame2 jg = new JumpGame2();
	jg.jump2(new int[]{1,2,3,1,1});
	System.out.println(jg.jump(new int[]{1,2,3,1,1,2,1}));

	}
	// Your goal is to reach the last index in the minimum number of jumps.
	// for-loop inside while-loop iteratively find out the longest distance reachable in current position
	// and then take the longest reachable one in current position as the next bounding edge of the next iteration
	 public int jump(int[] nums) {
	        int next_jump_lgst_dst = 0;
	        int longest_dst_sofar = 0;
	        
	        int currPos = 0;
	        int min_step = 0;
	        while (longest_dst_sofar < nums.length - 1) {
	        	// longest reachable one as the bounding edge
	        	// i:current position
	            for (; currPos<=longest_dst_sofar; currPos++) 
	                if ( (currPos + nums[currPos]) > next_jump_lgst_dst)
	                    next_jump_lgst_dst = currPos + nums[currPos];
	            
	            if (next_jump_lgst_dst > longest_dst_sofar) {
	                longest_dst_sofar = next_jump_lgst_dst;
	                min_step++;
	            } else // unable to reach beyond the longest distance  
	            	return -1;
	                
	        }
	        
	        return min_step;
	    }
	 
	 // record indices of jumps
	 public int jump2(int[] nums){
		 int last_jump_lgst = 0; // last longest distance having reached这一跳能走的最远距离
		 int step = 0; // minimum steps 
		 int curr_jump_lgst = 0; // longest distance that can be reached with 1 more step //下一跳能走的最远距离
		 
		 List<Integer> index = new ArrayList<>(); 
		 int ind = 0;
		 for (int i=0; i<nums.length; i++) {
			
			 if (i > last_jump_lgst) { // i < last_lgst : 所有尝试走的步数都在当前jump的范围内
				 step++;
				 last_jump_lgst = curr_jump_lgst;
				 
				 index.add(ind);
				 ind = i + 1;
				 
				 if (curr_jump_lgst > nums.length-1)
					 break;
			 }
			// curr_jump_lgst = Math.max(curr_jump_lgst, i + nums[i]); // 当i<last_lgst， 即当前位置在下一jump范围内时，寻找最远距离。
			 									//一旦超出下一jump距离，更新最远距离，并增加步数1
			 if (curr_jump_lgst < i + nums[i]) {
				 ind = i;
				 curr_jump_lgst = i + nums[i];
			 }

		 }
		 
		 index.add(nums.length-1);
		 for (int n : index) 
			 System.out.println(n);
		 
		 System.out.println(step);
		 return step;
	 }
}
