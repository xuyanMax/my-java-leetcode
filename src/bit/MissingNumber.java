package bit;

/**
 * Created by xu on 2017/6/28.
 * <p>
 * Given an arr containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the arr.
 * <p>
 * For example,
 * Given nums = [0, 1, 3] return 2.
 */
public class MissingNumber {
    /**
     * since the n numbers are from [0, n], we can just add all the numbers from [0, n] together and
     * minus the sum of the n-1 numbers in arr.
     */

    /*MATH*/
    public int missingNumber(int[] nums) {
        int sum = nums.length;
        for (int i = 0; i < nums.length; i++) {
            sum += i - nums[i];
        }
        return sum;
    }

    public int missingNumber_sum(int[] nums) {
        int sum = nums.length * (nums.length + 1) / 2;
        for (int i = 0; i < nums.length; i++) {
            sum -= nums[i];
        }
        return sum;
    }

    /**
     * BIT-MANIPULATION
     * <p>
     * Apply XOR operation to both the index and value of the arr. In a complete arr with no missing numbers,
     * the index and value should be perfectly corresponding( nums[index] = index), so in a missing arr,
     * what left finally is the missing number.
     */
    public int missingNumber_bit(int[] nums) {
        int xor = nums.length;
        for (int i = 0; i < nums.length; i++)
            xor = xor ^ i ^ nums[i];

        return xor;
    }

}
