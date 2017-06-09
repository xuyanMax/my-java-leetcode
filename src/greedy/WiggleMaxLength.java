package greedy;
// https://leetcode.com/problems/wiggle-subsequence/#/description
//https://discuss.leetcode.com/topic/51946/very-simple-java-solution-with-detail-explanation/34

// For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) 
// are alternately positive and negative.
	
public class WiggleMaxLength {

	public static void main(String[] args) {
		

	}
	public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        
        // deal the array like [0,0,0,1,3,2,...] 
        // equal values at the beginning
        int start = 1;
        while((start < nums.length) && (nums[start-1] == nums[start]) ){
              start++;
        }
        if (start == nums.length) // [0,0]
            return 1;
            
        int count = 1;
        // small big small big...
        // big small big small...
        boolean increasing = nums[start] > nums[0];
        for (int i=start; i<nums.length; i++) {
            if ((increasing && (nums[i]>nums[i-1])) || (!increasing && (nums[i]<nums[i-1]))) {
                increasing = !increasing;
                count++;
            }
        }
        return count;
        
        // diff[i] * diff[i-1] >= 0 
        // num--;
    }
}
