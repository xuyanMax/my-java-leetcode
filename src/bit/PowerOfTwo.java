package bit;

/**
 * Created by xu on 2017/5/29.
 */
public class PowerOfTwo {
    // only one bit of n is 1 so use the trick n&(n-1)
    public boolean isPowerOfTwo(int n) {
        if (n < 0) return false;
        return (n & (n - 1)) == 0;
    }

    public boolean isPowerOfTwo2(int n) {
        return (n & (n - 1)) == 0 && (n > 0);
    }
}
