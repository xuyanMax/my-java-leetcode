package bit;

/**
 * Created by xu on 2017/5/29.
 * <p>
 * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
 * <p>
 * Example:
 * <p>
 * Given a = 1 and b = 2, return 3.
 * <p>
 * references:
 * https://discuss.leetcode.com/topic/50315/a-summary-how-to-use-bit-manipulation-to-solve-problems-easily-and-efficiently
 */
public class BitsArithmetic {
    public static void main(String[] args) {
        int a = 0xff;//1111 1111
        int b = 0x89;//1000 1001
        // set union
        System.out.println(Integer.toBinaryString(a | b)); // 1111 1111
        // set intersection
        System.out.println(Integer.toBinaryString(a & b)); // 1000 1001
        // set subtraction
        System.out.println(Integer.toBinaryString(a & ~b)); //0111 0110
        // set negation
        System.out.println(Integer.toBinaryString(~b));
        // extract last bit
        System.out.println(Integer.toBinaryString(b & -b));

        // clear bit (1) first bit from the lsb b &= ~(1)
        // clear bit (2) second bit from the lsb b &=~(2)
        // clear bit (3) second bit from the lsb b &=~(4)
        // clear bit (4) second bit from the lsb b &=~(8)
        // .... ...
        System.out.println(Integer.toBinaryString(b &= ~(8)));

        // get all 32-bit 1's
        System.out.println(Integer.toBinaryString(~0));

        // REMOVE LAST appeared 1 BIT
        // for example, 1000 1000 after operation becomes 1000 0000
        b = 0x88;//1000 1000 = 2^7 + 2^3 = 128 + 8 = 136
        System.out.println(Integer.toBinaryString(b & (b - 1)));

        // get negative number in two's complement
        System.out.println(~b + 1); // -136
    }

    public int count_one(int number) {
        int count = 0;
        while (number != 0) {
            number &= (number - 1);
            count++;
        }
        return count;
    }

    // iterative
    public int getSum(int n1, int n2) {
        if (n1 == 0) return n2;
        if (n2 == 0) return n1;

        /*
         n1 = 0001
         n2 = 0011

         1.
         carry = 0001
         n1 = 0010
         n2 = carry<<1 = 0010
         2.
         carry = 0010 & 0010 = 0010
         n1 = 0010 ^ 0010 = 0000
         n2 = 0010<<1 = 0100
         3.
         carry = 0000 & 0100 = 0000
         n1 = 0000 ^ 0100 = 0100
         n2 = 0000<<1 = 0000
         break loop

         return n1 = 0100

         */
        while (n2 != 0) {
            int carry = n1 & n2; // find a carry (again)
            n1 = n1 ^ n2; // find the different bits and assign it to n1
            n2 = carry << 1; // left-shift-carry-1-bit and assign it to n2
        }
        return n1;

    }

    /* for example n1 = 0101 n2 = 0011 (5-3=2)
     * 1.
     * borrow = 1010 & 0011 = 0010
     * n1 = 0101 ^ 0011 = 0110
     * n2 = 0100
     *
     * 2.
     * borrow = 1001 & 0100 = 0000
     * n1 = 0110 ^ 0100 = 0010
     * n2 = 0000;
     *
     *  break loop
     *  return n1 = 0010 (2)
     *
     * */
    public int getSubstract(int n1, int n2) {// n1 - n2

        while (n2 != 0) {
            int borrow = ~n1 & n2; // bits that requires to borrow a 1 from their left bit
            n1 = n1 ^ n2; // find the different bits
            n2 = borrow << 1;

        }
        return 1;
    }

}
