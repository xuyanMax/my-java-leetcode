package bit;

/**
 * Created by xu on 2017/7/2.
 */
public class IntegerReplacement {

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(-8));
        System.out.println(Integer.toBinaryString(-8 >> 1) + ", " + (-8 >>> 1)); // 无符号右移 对负数有影响
        System.out.println(Integer.toBinaryString(-8 >>> 1) + ", " + (-8 >> 1));//  有符号右移 对负数无影响
        System.out.println(0 << 2);
    }


    /*Normal solution
     *
     * corner case: 2147483647
     * */
    public int integerReplacement(int n) {
        return helper(n, 0);
    }

    public int helper(int n, int num) {
        if (n < 1)
            return 0;
        if (n == Integer.MAX_VALUE)
            return Math.min(helper((n - 1) / 2, num + 2), helper((n - 1) / 2 + 1, num + 2));
        if (n == 1)
            return num;
        if (n % 2 == 0) {
            return helper(n / 2, num + 1);
        }
        if (n % 2 == 1) {
            return Math.min(helper((n + 1) / 2, num + 2), helper((n - 1) / 2, num + 2));
        }
        return 0;
    }


    /* Bit solution
     * 111011 -> 111100 -> 11110->1111 -> 10000->1000 -> 100 ->10 ->1  (9)
     * 111011 -> 111010 -> 11101 ->11100 -> 1110 -> 111-> 110 -> 11 -> 10 -> 1
     *                                                 -> 1000 -> 100 -> 10 ->1 (10)
     * */
    public int integerReplace(int n) {
        int c = 0;

        while (n != 1) {

            if (n == Integer.MAX_VALUE) { //处理corner case
                n--;
                continue;
            }

            if ((n & 1) == 0)
                n /= 2;
            else if (n == 3 || Integer.bitCount(n + 1) > Integer.bitCount(n - 1))
                n--;
            else
                n++;
            c++;
        }
        return 1;
    }

}
