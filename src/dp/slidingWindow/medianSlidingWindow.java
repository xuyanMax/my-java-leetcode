package dp.slidingWindow;
//480. Sliding Window Median

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
 * Explanation:
 * Window position                Median
 * ---------------                -----
 * [1  3  -1] -3  5  3  6  7        1
 * 1 [3  -1  -3] 5  3  6  7       -1
 * 1  3 [-1  -3  5] 3  6  7       -1
 * 1  3  -1 [-3  5  3] 6  7        3
 * 1  3  -1  -3 [5  3  6] 7        5
 * 1  3  -1  -3  5 [3  6  7]       6
 */
public class medianSlidingWindow {

    PriorityQueue<Integer> desc_smaller = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> asc_larger = new PriorityQueue<>();

    //Given an unsorted list of numbers, how do we find the median element?
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return null;
        double[] res = new double[nums.length - k + 1];
        int right = 0;
        while (right < nums.length) {
            add(nums[right]);
            if (right >= k - 1) {
                res[right - k + 1] = getMedian();
                remove(nums[right - k + 1]);
            }
        }
        return res;
    }

    public void add(int num) {
        if (num > getMedian())
            asc_larger.add(num);
        else
            desc_smaller.add(num);
        re_balance();
    }

    public void remove(int num) {
        if (num > getMedian())
            asc_larger.remove(num);
        else desc_smaller.remove(num);
    }

    public void re_balance() {
        if (desc_smaller.size() < asc_larger.size())
            desc_smaller.add(asc_larger.poll());
        else if (desc_smaller.size() - asc_larger.size() > 1)
            asc_larger.add(desc_smaller.poll());
    }

    public double getMedian() {
        return desc_smaller.size() == asc_larger.size()
                ? (double) (desc_smaller.peek() + asc_larger.peek()) / 2
                : desc_smaller.peek();
    }


}
