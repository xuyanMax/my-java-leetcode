package array.easy;

/**
 * 217. Contains Duplicate
 * Given an array of integers, find if the array contains any duplicates.
 * Your function should return true if any value appears at least twice in the array,
 * and it should return false if every element is distinct.
 */

import java.util.Arrays;
import java.util.HashSet;

public class ContainsDuplicates {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 5, 6};
        System.out.println(solution(nums));
    }

    static boolean solution(int[] nums) {

        //Time complexity O(N*LOG(N)) Space complexity O(1)
        // first sort arr by ascending order and compare the adjacent ones
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++)
            if (nums[i] == nums[i + 1]) return true;

        return false;
    }

    static boolean solution2(int[] nums) {

        //// time complexity O(N*N)
        for (int i = 0; i < nums.length; i++)
            for (int j = i + 1; j < nums.length; j++)
                if (nums[i] == nums[j]) return true;

        return false;

    }

    static boolean solution3(int[] nums) {

        //// hashset Time complexity: O(N), Space complexity O(N)
        if (nums.length == 0 || nums == null) return false;
        HashSet<Integer> noDuplicate = new HashSet<>();

        for (int num : nums)
            if (!noDuplicate.add(num))
                return true;

        return false;

    }

}
