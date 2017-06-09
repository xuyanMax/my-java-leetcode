package bit;

/**
 * Created by xu on 2017/5/28.
 * Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as theHamming weight).
 *
 * For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011,
 * so the function should return 3.
 *
 * The binary representation of Integer.MAX_VALUE is 0111 1111 1111 1111 1111 1111 1111 1111, and
 * The binary representation of Integer.MAX_VALUE + 1 is 1000 0000 0000 0000 0000 0000 0000 0000 (spaces added).
 */
public class NumOf1Bits {
    public int hammingWeight(int num){
        int count = 0;
        while (num != 0) {
            count += (num & 1);
            num >>>= 1; // right unsigned shift 1 bit
        }
    return count;
    }
    // mask
    public int hammingWeight2(int num) {
        int mask = 1;
        int count = 0;
        for (int i=0; i<32; i++) {
            if ( (mask & num) == 1) count++;
            mask <<= 1;
        }
        return count;
    }
}
