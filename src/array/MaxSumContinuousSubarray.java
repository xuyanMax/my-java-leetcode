package array;

/**
 * Created by xu on 11/08/2017.
 */
public class MaxSumContinuousSubarray {

    public int kadane(int[] nums) {

        int maxSoFar = 0;
        int maxEndHere = 0;

        // to print the subarray with the maximum sum, we maintain the indices whenever we get the maximum sum
        int start = 0, end = 0, s = 0;

        for (int i=0; i<nums.length; i++) {
            maxEndHere += nums[i];
            if (maxEndHere < 0) {
                maxEndHere = 0;
                s = i + 1;
            }
            else if (maxSoFar < maxEndHere) {// compare only when maxEndHere >= 0
                maxSoFar = maxEndHere;
                end = i;
                start = s;
            }
        }
        System.out.println("Start index:" + start);
        System.out.println("End index:" + end);

        return  maxSoFar;
    }
    // 动态规划解法，参考dp->MaxContinuousSubarray.java
}
