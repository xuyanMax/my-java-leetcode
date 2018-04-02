package divide_conquer.hard;
/*327. Count of Range Sum

Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.

Note:
A naive algorithm of O(n2) is trivial. You MUST do better than that.

Example:
Given nums = [-2, 5, -1], lower = -2, upper = 2,
Return 3.
The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.

Credits:
Special thanks to @dietpepsi for adding this problem and creating all test cases.*/
public class CountRangeSum {
    // naive solution
    // time complexity O(n^2) TLE
    public int naive(int[] nums, int lower, int upper) {
        if (nums==null || nums.length==0) return 0;
        int count = 0;
        // presum[n+1]
        int[] presum = new int[nums.length+1];
        //sum[i:j] = presum[j] - presum[i]
        for (int i=0; i<nums.length; i++)
            presum[i+1] = presum[i] + nums[i];
        for (int i=0; i<presum.length-1; i++){
            for (int j=i+1; i<presum.length; j++){
                int tmp = presum[j] - presum[i];
                if ( tmp>= lower && tmp <= upper)
                    count++;
            }
        }
        return count;
    }

    // merge sort solution 1
    int count = 0;
    int lower;
    int upper;
    public int countRangeSum(int[] nums, int lower, int upper) {
        if(nums==null || nums.length==0) return 0;
        long[]pre = new long[nums.length+1];
        for (int i=0; i<nums.length; i++) pre[i+1] = pre[i] + nums[i];

    }
    public void mergeSort(long[] pre, int start, int end){

    }
    public void merge(long[] pre, int start, int end, int mid){

    }
}
