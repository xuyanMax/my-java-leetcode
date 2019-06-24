package bit;

/**
 * Created by xu on 2017/5/30.
 * <p>
 * Given an arr of numbers nums, in which exactly two elements appear only once and all the other elements appear
 * exactly twice. Find the two elements that appear only once.
 * <p>
 * For example:
 * <p>
 * Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
 */


public class SingleNumber_3 {

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(0x5));
        System.out.println(Integer.toBinaryString(0x3));
        System.out.println(Integer.toBinaryString(-0x6 & 0x6));
    }

    public int[] sol(int[] nums) {
        // pass 1 get XOR of the two numbers we need to find
        int diff = 0;
        for (int num : nums)
            diff ^= num;

        // find out the rightmost set bit '1' (where two single number differs 0 & 1 = 1)
        // diff will be something like, 1 <<i (2^i)
        diff &= -diff;

        // diff = a XOR b; a and b differs in a bit
        int[] ret = new int[2];
        // pass 3: separate the nums[] into two groups, the two distinct number a and b will go to separate group

        for (int num : nums) {
            if ((num & diff) == 1)
                ret[0] ^= num;
            else
                ret[1] ^= num;
        }
        return ret;
    }
}
/*

Why diff &= -diff or diff &= ~(diff-1) can find one of the different bit?

a XOR b = 6  (diff)
6   =>    0110
-6  =>    1010 (2s complement of 6)
6 & -6 => 0010 //this is used as a mask to separate 3 and 5 from the nums[]

As the 1st and 2nd bits are set to 1, these bits in the input (a and b) must be different in order to produce 1 (set bit)
in the diff. As long as we pick one of these bits (@zhiqing_xiao mentioned this as arbitrary bit) as a mask to separate
nums[] in to two groups, the two distinct numbers a and b will go to different groups.

FYI, another inefficient way to find this mask in Java is

diff = (int) Math.pow(2,Integer.numberOfTrailingZeros(diff));
*/
