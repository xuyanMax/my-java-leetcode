package arr.easy;

import java.util.Arrays;

/**
 * 167. Two Sum II - Input array is sorted
 * <p>
 * Given an array of integers that is already sorted in ascending order,
 * find two numbers such that they add up to a specific target number.
 * <p>
 * The function twoSum should return indices of the two numbers such that they add up to the target,
 * where index1 must be less than index2.
 * Please note that your returned answers (both index1 and index2) are not zero-based.
 * <p>
 * You may assume that each input would have exactly one solution and you may not use the same element twice.
 * <p>
 * Input: numbers={2, 7, 11, 15}, target=9
 * Output: index1=1, index2=2
 */
public class TwoSum2SortedArray {
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length == 0) return new int[0];
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            long tmp = numbers[left] + numbers[right];
            if (tmp == target)
                break;
                //{
                //     result[0]=left+1;
                //     result[1]=right+1;
                //}
            else if (tmp < target)
                left++;
            else right--;
        }
        // return result;
        return new int[]{left + 1, right + 1};

        // or: use binary search to locate the biggest num less than the target and
        // use two pointers

        // or: fixed numbers[0] and   search the rest n-1 elements numers[1..n-1]. If cannot find any
        // element nums[?]==target-nums[0], then try numbers[1] and binary search numbers[2..n-1] until numbers[n-2] and numbers[n-1]
    }

    public static int[] twoSumSol2(int[] numbers, int target) {
        if (numbers == null || numbers.length == 0) return new int[0];
        int left = 0;
        int right = Arrays.binarySearch(numbers, 0, numbers.length, target);
//        right = right>=0?right+1:numbers.length-1;// works rightly
        right = right >= 0 ? right + 1 : (~right) < numbers.length ? ~right : numbers.length - 1;
        int ind = 0;
        for (; left <= right; left++) {
            ind = 0;
            int tmp = target - numbers[left];
            ind = Arrays.binarySearch(numbers, left + 1, right + 1, tmp);// (left, right]
            if (ind >= left && ind <= right) {
                right = ind;
                break;
            }
        }
        return new int[]{left + 1, right + 1};
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 4, 9, 56, 90};
        // -1 0 ->-1
        //[1,2,3,4,4,9,56,90]->8

//         System.out.println(Arrays.binarySearch(arr, 0, 2, 0));
        int[] res = twoSumSol2(arr, 8);
        Arrays.stream(res).forEach(System.out::println);
    }
}
