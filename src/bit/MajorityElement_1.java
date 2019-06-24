package bit;

/**
 * Created by xu on 2017/5/29.
 */
public class MajorityElement_1 {

    /**
     * Moore Voting Algorithm
     * bit manipulation
     * We need to count the number of 1's in a specific bit and check if it is larger than n/2 times.
     * So we'll use a mask, like 1, 10, 100, 1000, to get the number of i-th bit in every one of nums.
     */
    public int majorityElementBit(int[] nums) {

        int mask = 1;
        int majority = 0;
        for (int i = 1; i < 32; i++, mask <<= 1) {
            // counting every i-th bit
            int bitCounts = 0;
            for (int j = 0; j < nums.length; j++) {
                if ((nums[j] & mask) != 0) // j-th bit 存在1
                    bitCounts++;
                if (bitCounts > nums.length / 2) {
                    majority |= mask; //合并mask的j-th bit于majority本身
                    break;
                }
            }
        }
        return majority;

    }
}
