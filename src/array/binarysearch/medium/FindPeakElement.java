package array.binarysearch.medium;

/**
 * @author xu
 * 62. Find Peak Element
 * A peak element is an element that is greater than its neighbors.
 * <p>
 * Given an input arr where num[i] ≠ num[i+1], find a peak element and return its index.
 * <p>
 * The arr may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * <p>
 * You may imagine that num[-1] = num[n] = -∞.
 * <p>
 * For example, in arr [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 */
public class FindPeakElement {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 3, 1, 3, 2, 4, 3};
        System.out.println(solution(arr));
        System.out.println(solution2(arr));
        System.out.println(solutionIter(arr));

    }

    /* Binary Search */
    static int solution(int[] arr) {
        int left = 0, right = arr.length;
        return helper(arr, left, right);
    }

    static int helper(int[] arr, int left, int right) {

        int mid = (left + right) / 2;

        // mid-1, mid, mid+1
        if (arr[mid] < arr[mid - 1])
            return helper(arr, left, mid - 1);
        else if (arr[mid] < arr[mid + 1])
            return helper(arr, mid + 1, right);
        else return mid; // arr[mid] > arr[mid-1] && arr[mid] > arr[mid+1]
    }

    /* Binary search 2*/
    static int solution2(int[] arr) {
        return helper2(arr, 0, arr.length);
    }

    static int helper2(int[] arr, int left, int right) {

        if (left == right)
            return left;

        int mid = (left + right) / 2;

        if (arr[mid] > arr[mid + 1])
            return helper2(arr, left, mid);
        else
            return helper2(arr, mid + 1, right);

    }

    /* iterative binary search*/
    static int solutionIter(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < nums[mid + 1])
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }
}
