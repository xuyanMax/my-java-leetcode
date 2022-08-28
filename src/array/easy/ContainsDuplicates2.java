package array.easy;

import java.util.HashSet;
import java.util.Set;

/*
* *
* 219. Contains Duplicate II

Given an array of integers and an integer k,
find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j]
and the absolute difference between i and j is at most k.
* */
public class ContainsDuplicates2 {

    static boolean solution_2(int[] arr, int k) {
        Set<Integer> set = new HashSet<>();
        int right = 0;
        while (right < arr.length) {
            if (right > k)
                set.remove(arr[right - k -1]);// 1, 2, 3, 4, 1 k = 3
            if (!set.add(arr[right])) // exist duplicates
                return true;
            right++;
        }
        return false;

    }
    // 与sol2不同之处，调换了条件检测的先后顺序
    static boolean solution_3(int[] arr, int k) {
        Set<Integer> set = new HashSet<>();
        int right = 0;
        while (right < arr.length) {
            if (!set.add(arr[right])) // exist duplicates
                return true;

            if (right >= k)
                set.remove(arr[right - k]);// 1, 2, 3, 4, 1 k = 3

            right++;
        }
        return false;

    }
}
