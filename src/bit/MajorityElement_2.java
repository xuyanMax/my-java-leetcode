package bit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xu on 2017/7/3.
 */
/*

Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
The algorithm should run in linear time and in O(1) space.

*/
public class MajorityElement_2 {

     public static void main(String[] args){

     }

/*
since the requirement is finding the majority for more than ceiling of [n/3],
the answer would be less than or equal to two numbers.
So we can modify the algorithm to maintain two counters for two majorities (at most two majorities).

*/

    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if(nums.length == 0)
            return res;

        // [0,0,0] =>[0]
        // COUNT1 = 1, COUNT2 = 0
        int num1 = nums[0]; int num2 = nums[0]; int count1 = 0; int count2 = 0 ;

        for (int val : nums) {
            if(val == num1)
                count1++;
            else if (val == num2)
                count2++;
            else if (count1 == 0) {
                num1 = val;
                count1++;
            }
            else if (count2 == 0) {
                num2 = val;
                count2++;
            }
            else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for(int val : nums) {
            if(val == num1)
                count1++;
            else if(val == num2)
                count2++;
        }
        if(count1 > nums.length/3)
            res.add(num1);
        if(count2 > nums.length/3)
            res.add(num2);
        return res;
    }
}
