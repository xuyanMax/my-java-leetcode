package bit;

/**
 * Created by xu on 2017/6/29.
 */
public class NumberComplement {
     public static void main(String[] args){
         System.out.println(Integer.highestOneBit(~7 & (7<<1) - 1));
         System.out.println(Integer.toBinaryString(7));
         System.out.println(Integer.toBinaryString(-7));
         System.out.println(Integer.toBinaryString(~7));

         System.out.println(findComplement_2(8));
     }

/*
~ operator
 a = 0011 1100
~a = 1100 0011

2's COMPLEMENT   - ~a + 1

But we only flip N bits within the range from LEFTMOST bit of 1 to RIGHTMOST
So we create a BIT-MASK with N bits

examples
1. 7 (111)

==32-bit
Integer.highestOneBit(7) = 0000... 0100 = 4
mask = 100<<1 - 1 = 1000 - 1 = 111;

~7 = 11111111111111111111111111111000 = -8
-7 = 11111111111111111111111111111001 = -7 2's complement of 111

return 11111111111111111111111111111000 & 111 = 0

*/
    public int findComplement(int num) {
        int mask = Integer.highestOneBit(num)<<1 - 1;
        return ~num & mask;
    }

/*
CREATE bit mask

TO XOR num & mask

example ( 1000 0000)

FOR 8-bit
mask = 1000 0000

mask |= mask >> 1 -> mask := 1100 0000
mask |= mask >> 2 -> mask := 1111 0000
mask |= mask >> 4 -> mask := 1111 1111

return 1000 000 ^ 1111 1111 = 111 1111

FOR 32-bit
we still need
mask |= mask >> 8
mask |= mask >> 16

If input num is "less than" 32 bits, say 7 = 111, the rest of bits are all 0s.
mask = 111;
mask |= mask >> 1 -> mask := 111 | 11 = 111
mask |= mask >> 2 -> mask := 111 | 1 = 111
mask |= mask >> 4 -> mask := 111 | 0 = 111
...
mask |= mask >> 16 -> mask := 111 | 0 = 111


*/
    public static int findComplement_2(int num) {
        int mask = num;
        mask |= mask >> 1;

        mask |= mask >> 2;

        mask |= mask >> 4;

        mask |= mask >> 8;

        mask |= mask >> 16; // := mask|0

        return num ^ mask;
    }
}
