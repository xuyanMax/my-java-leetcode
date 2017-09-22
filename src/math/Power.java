package math;

/**
 * Created by xu on 26/07/2017.
 *
 * Implement pow(x, n).
 */
public class Power {
    public double myPow(double x, int n) {
        if (n == 0)
            return 1;
        if (n == Integer.MIN_VALUE) { //if n = -2147483648, -n will cause overflow
            x = x*x;
            n = n/2;
        }
        if (n < 0) {// 2^(-3) = (1/2)^3
            n = -n;
            x = 1/x;
        }
        // 如果n是奇数，返回x*(x*x)^(n/2)
        // 如果n是偶数，返回(x*x)^(n/2)
        // 无论n最初是奇数还是偶数，都归结到 n="1"，
        // 256^1 = 256*myPow(256^2, 0) = 256 * 1 = 256
        return n%2==0 ? myPow(x*x, n/2) : x * myPow(x*x, n/2);

    }

}
