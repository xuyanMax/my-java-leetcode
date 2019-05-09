package algorithm;

/**
 * Created by xu on 08/07/2017.
 * <p>
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 * <p>
 * Note: Do not use any built-in library function such as sqrt.
 * <p>
 * Example 1:
 * <p>
 * Input: 16
 * Returns: True
 * Example 2:
 * <p>
 * Input: 14
 * Returns: False
 */
//方法1
public class ValidSquarePerfect {

    public boolean isPerfectSquare(int num) {
        int low = 1, high = num;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (mid * mid == num) {
                return true;
            } else if (mid * mid < num) {
                low = mid + 1;
            } else
                high = mid - 1;
        }
        return false;
    }

    //方法2
    // x*x = num
    // x = (x+num/x)/2
    public boolean NewtonMethod(int num) {
        long x = num;
        while (x * x > num)
            x = (x + num / x) / 2;
        return x * x == num;
    }
}
