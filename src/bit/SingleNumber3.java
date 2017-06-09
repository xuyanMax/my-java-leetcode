package bit;

/**
 * Created by xu on 2017/5/30.
 */
public class SingleNumber3 {

     public static void main(String[] args){
         System.out.println(Integer.toBinaryString(0x5));
         System.out.println(Integer.toBinaryString(0x3));
         System.out.println(Integer.toBinaryString(-0x6 & 0x6));
     }

    public int[] sol(int[] nums) {
        // pass 1 get XOR of the two numbers we need to find
        int diff = 0;
        for (int num : nums)
            diff ^= num;

        // find out the rightmost set bit (where two single number differs)
        // diff will be something like, 1 <<i (2^i)
        diff &= -diff;

        int[] ret = new int[2];
        // pass 3: separate the two single numbers by the difference bit
        for (int num : nums) {
            if ((num & diff) == 1)
                ret[0] ^= num;
            else
                ret[1] ^= num;
        }
        return ret;
    }
}
