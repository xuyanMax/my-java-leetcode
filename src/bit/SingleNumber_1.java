package bit;

/**
 * Created by xu on 2017/5/29.
 */
public class SingleNumber_1 {

    //
    //Given an array of integers, every element appears twice except for one. Find that single one.
    //
    //known that A XOR A = 0 and the XOR operator is commutative, the solution will be very straightforward.
    public int singleNumber(int A[], int n) {
        int result = 0;
        for (int i = 0; i < n; i++) {
            result ^= A[i];
        }
        return result;
    }

    public int singleNumber_mask(int[] arr) {
        int result = 0;
        int x, sum;

        // Iterate through every bit
        for (int i = 0; i < 32; i++)
        {
            // Find sum of set bits at ith position in all
            // array elements
            sum = 0;
            x = (1 << i);
            for (int j=0; j< arr.length; j++ )
            {
                if ((arr[j] & x) == 1)
                    sum++;
            }

            // The bits with sum not multiple of 3, are the
            // bits of element with single occurrence.
            if (sum % 3 != 0)
                result |= x;
        }

        return result;
    }
}
