package sort;

import java.util.Arrays;

/**
 * Created by xu on 28/08/2017.
 */
/*
Given an unsorted arr, find the maximum difference between the successive elements in its sorted form.

Try to solve it in linear time/space.

Return 0 if the arr contains less than 2 elements.

You may assume all elements in the arr are non-negative integers and fit in the 32-bit signed
integer range.

*/
public class MaximumGap {

    //bucket sort
    //找出数组最大值、最小值
    //将数组分为N-1个bucket
    //# buckets = Max - Min / (N - 1)
    // 出去Min、Max后的N-2个元素放入N-1个buckets中，至少有一个bucket是空的
    // 我们需要存储这个空bucket前的最大值，以及这个bucket后的最小值，求出差值即可
    // bucket的 gap = ceiling((Max-Min)/(N-1));
    // 第k个bucket的取之范围是 [Min + (k-1)*gap, Min + K*gap]
    // 元素num属于第几个bucket？k = (num-Min)/gap

    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2)
            return 0;
        int Max = Integer.MIN_VALUE;
        int Min = Integer.MAX_VALUE;
        int N = nums.length;

        for (int num:nums) {
            Max = Math.max(Max, num);
            Min = Math.min(Min, num);
        }
        if (Max == Min)
            return 0;

        int gap = (int) Math.ceil((double)(Max-Min)/(nums.length-1));
        int[] bucket_MIN = new int[N - 1];
        int[] bucket_MAX = new int[N - 1];

        //初始化bucket数值
        Arrays.fill(bucket_MAX, Integer.MIN_VALUE);
        Arrays.fill(bucket_MIN, Integer.MAX_VALUE);

        for (int num:nums) {
            //不在bucket中记录最大最小值
            if (num == Min || num == Max)
                continue;
            int k = (num-Min)/gap;
            bucket_MAX[k] = Math.max(num, bucket_MAX[k]);
            bucket_MIN[k] = Math.min(num, bucket_MIN[k]);
        }
        // 查找empty bucket
        int max_diff = 0;
        int pre_max = Min;
        for(int i=0; i<N-1; i++) {
            if (bucket_MAX[i] == Integer.MIN_VALUE && bucket_MIN[i] == Integer.MAX_VALUE)
                continue;
            max_diff = Math.max( bucket_MIN[i] - pre_max, max_diff);
            pre_max =  bucket_MAX[i];
        }
        max_diff = Math.max(max_diff, Max - pre_max);//最后一个单独计算
        return max_diff;
    }

}
