package binarySearch;

/**
 * Created by xu on 08/07/2017.
 */
public class ValidSquarePerfect {
    public boolean isPerfectSquare(int num) {
        int low = 1, high = num;
        while (low <= high) {
            int mid = low + (high-low)/2;
            if (mid*mid == num) {
                return true;
            } else if (mid*mid < num) {
                low = mid + 1;
            } else
                high = mid - 1;
        }
        return false;
    }
    // x*x = num
    // x = (x+num/x)/2
    public boolean NewtonMethod(int num) {
        long x= num;
        while (x*x > num)
            x = (x+num/x)/2;
        return x*x == num;
    }
}
