package array.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 229. Majority Element II
 * <p>
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * The algorithm should run in linear time and in O(1) space.
 */
public class MajorityElement2 {
    /*
     * The essential concepts is you keep a counter for the majority number X.
     * If you find a number Y that is not X, the current counter should deduce 1.
     * The reason is that if there is 5 X and 4 Y, there would be one (5-4) more X than Y.
     * This could be explained as “4 X being paired out by 4 Y”.
     * And since the requirement is finding the majority for more than ceiling of [n/3],
     * the answer would be less than or equal to two numbers.
     * So we can modify the algorithm to maintain two counters for two majorities.
     * */
    public List<Integer> majorityElement(int[] nums) {
        if (nums == null || nums.length == 0)
            return new ArrayList<Integer>();
        List<Integer> result = new ArrayList<Integer>();

        int number1 = nums[0], number2 = nums[0],
                count1 = 0, count2 = 0, len = nums.length;

        for (int i = 0; i < len; i++) {
            if (nums[i] == number1)
                count1++;
            else if (nums[i] == number2)
                count2++;
            else if (count1 == 0) {
                number1 = nums[i];
                count1 = 1;
            } else if (count2 == 0) {
                number2 = nums[i];
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == number1)
                count1++;
            else if (nums[i] == number2)
                count2++;
        }
        if (count1 > len / 3)
            result.add(number1);
        if (count2 > len / 3)
            result.add(number2);
        return result;
    }
}
