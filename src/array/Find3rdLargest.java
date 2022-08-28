package array;

/**
 * @author xu
 * Given a non-empty arr of integers, return the third maximum number in this arr.
 * If it does not exist, return the maximum number. The time complexity must be in O(n).
 */
public class Find3rdLargest {

    static int solution(int[] arr) {
        long max = Long.MIN_VALUE;
        long min = max, mid = max;

        for (int n : arr) {
            if (n > max) {
                max = n;
                mid = max;
                min = mid;
            } else if (n < max && n > mid)
                mid = n;
            else if (n > min && n < mid)
                min = n;
        }
        return (int) (min != Long.MIN_VALUE ? min : max);
    }
}
