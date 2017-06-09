package bit;

/**
 * Created by xu on 2017/5/29.
 * Reverse bits of a given 32 bits unsigned integer.

 For example, given input 43261596 (represented in binary as 00000010100101000001111010011100),
 return 964176192 (represented in binary as 00111001011110000010100101000000).
 */
public class ReverseBits {
    public static void main(String[] args){
//System.out.println(Integer.toBinaryString(reverse3(0x1101)));
     }
    public static int reverse(int num) {
        int reverseBits = 0;
        int i=0;
        while (i<32) {
            reverseBits += num & 1;
            num >>>= 1;
            if (i < 31)
                reverseBits <<= 1;

        }
        return reverseBits;
    }
    public static int reverse2(int num) {
        int reverseBits = 0;
        int i=0;
        while (i<32) {
            reverseBits <<= 1;
            reverseBits += num & 1;
            num >>>= 1;

        }
        return reverseBits;
    }
   // problem is ret contains 32 bits and the lst is 1...
   // does not work well
    public static int reverse3(int num) {
        int mask = 1<<31;
        int ret = 0;
        for (int i=0; i<32; i++) {
            if ((num & 1) ==1)
                ret |= mask;
            mask >>= 1;
            num >>= 1;
        }
        return ret;
    }
}
