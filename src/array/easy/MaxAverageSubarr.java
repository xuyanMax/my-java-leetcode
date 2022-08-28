package array.easy;
//643. Maximum Average Subarray I
public class MaxAverageSubarr {

    public double good(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        int i = 0;
        double sum = 0;
        for (i=0; i<k; i++)
            sum += nums[i];
        double res = sum;
        for (;i<nums.length; i++){
            sum = sum + nums[i] - nums[i-k];
            res = Math.max(res, sum);
        }

        return res/k;
    }
    public double notGood(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        int i = 0;
        double max = Integer.MIN_VALUE;
        double sum = 0;
        for (i=0; i<k; i++)
            sum += nums[i];
        for (;i<nums.length; i++){
            max = Math.max(max, sum/k);
            sum = sum + nums[i] - nums[i-k];
        }
        max = Math.max(sum/k, max);
        return max;


    }
}
