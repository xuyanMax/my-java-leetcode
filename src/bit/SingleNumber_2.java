package bit;

/**
 * Created by xu on 2017/5/29.
 */

/*
    Given an array of integers, every element appears 3 times except for one,
    which appears exactly once. Find that single one.


 */
public class SingleNumber_2 {
/*

references:

https://discuss.leetcode.com/topic/2031/challenge-me-thx/107

one two
0   0
1   0
0   1
------
0   0

one = (one ^ A[i]) & ~two
two = (two ^ A[i]) & ~one
 */

    public int sol1(int[] A) {
        int ones = 0, twos = 0;
        for (int i = 0; i < A.length; i++) {
            ones = (ones ^ A[i]) & ~twos;
            twos = (twos ^ A[i]) & ~ones;
        }
        return ones;// appears only once
    }

    // reference
    // https://discuss.leetcode.com/topic/2031/challenge-me-thx/109
    // construct the result using mask
    public int sol2(int[] A) {
        // Initialize result
        int result = 0;

        // iterate through every bit of 32-bit number
        for (int i=0; i<32; i++) {
            // Find sum of set bits at ith position in all
            // array element
            int sum = 0;
            int mask = 1 << i;
            for (int j=0; j<A.length; j++) {
                if ((mask & A[j]) == 1)
                    sum++;
            }
            // The bits with sum not multiple of 3, are the
            // bits of element with single occurrence.
            if (sum % 3 != 0)
                result |= mask;
        }
        return result;
    }


}
