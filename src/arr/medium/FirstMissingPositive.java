package arr.medium;

/*
* 41. First Missing Positive

Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.
* */
public class FirstMissingPositive {
    //大小为n数组，第一个missing正整数的范围是[1,n+1]
    //1、如果数组为[1,2,3,4,...,n]，那么f m p 为n+1
    //2、使用任意x (x<=0 || x>n)代替数组[1,2,3,4,...,n]中任意一个数，一定有一个数是小于n+1的

    public int firstMissingPositive(int[] nums) {
        for (int i=0; i<nums.length; i++) {

            while (nums[i]>0 && nums[i]<=nums.length
                    && nums[nums[i]-1] != nums[i] )  //索引0对应1，因为要求正整数
                swap(nums, i, nums[i]-1);

        }
        for (int i=0; i<nums.length; i++)
            if (i!=nums[i]-1)
                return i+1;

        return nums.length+1;
    }
    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
