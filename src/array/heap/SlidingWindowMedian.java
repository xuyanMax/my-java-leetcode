package array.heap;


import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by xu on 2017/6/28.
 */

/**
 * Given an arr nums, there is a sliding window of size k which is moving
 * from the very left of the arr to the very right.
 * <p>
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * Your job is to output the median arr for each window in the original arr.
 * <p>
 * For example,
 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 * <p>
 * Window position                Median
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 * 1 [3  -1  -3] 5  3  6  7       -1
 * 1  3 [-1  -3  5] 3  6  7       -1
 * 1  3  -1 [-3  5  3] 6  7       3
 * 1  3  -1  -3 [5  3  6] 7       5
 * 1  3  -1  -3  5 [3  6  7]      6
 */
public class SlidingWindowMedian {

    /*****************************************************
     *
     *            PRIORITY QUEUES: need rebalance after each Add and Remove operations.
     *
     ******************************************************/
    Queue<Integer> small = new PriorityQueue<>(Collections.reverseOrder());// smaller part descending order
    Queue<Integer> large = new PriorityQueue<>(); //  larger part ascending order

    public static void main(String[] args) {
        SlidingWindowMedian inst = new SlidingWindowMedian();
        inst.medianSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] res = new double[nums.length - k + 1];
        if (nums == null || nums.length == 0)
            return res;

        for (int i = 0; i < nums.length; i++) {
            add(nums[i]);
            if (i >= k - 1) {
                res[i - k + 1] = getMedian();
                remove(nums[i - k + 1]);
            }

        }
        return res;

    }

    public void add(int toAdded) {
        // toAdded should lie in small
        if (toAdded < getMedian()) {
            small.add(toAdded);
        } else {
            large.add(toAdded);
        }

        // re-balance in case nums are added to only one queuee
        reBalance();
    }

    public void remove(int toRemove) {
        if (toRemove <= getMedian()) // 必须包含等于号 toRemove lies in small
            small.remove(toRemove);
        else
            large.remove(toRemove);
        // when remove an element from smaller queue, we need re-balance the size
        reBalance();

    }

    public void reBalance() {
        // re-balance the two queues, making sure either they are equal size or smaller is 1 size over than larger.
        if (small.size() < large.size())
            small.add(large.poll());
        else if (small.size() - large.size() > 1)
            large.add(small.poll());
    }

    //        [2147483647,2147483647]
    //        2
    public double getMedian() {
        if (small.isEmpty() && large.isEmpty())
            return 0;
        if (small.size() == large.size())
            return ((double) small.peek() + (double) large.peek()) / 2.0; // 防止int越界

        else
            return small.peek();
    }

/*****************************************************
 *
 *            TREEMAP
 *
 ******************************************************/


}
