package array.heap.hard;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author xu
 * <p>
 * Given an arr nums, there is a sliding window of size k which is moving from the very left
 * <p>
 * of the arr to the very right. You can only see the k numbers in the window.
 * <p>
 * Each time the sliding window moves right by one position.
 * <p>
 * For example,
 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 * <p>
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * Therefore, return the max sliding window as [3,3,5,5,6,7].
 * <p>
 * https://leetcode.com/problems/sliding-window-maximum/#/description
 * https://discuss.leetcode.com/topic/19055/java-o-n-solution-using-deque-with-explanation
 */


public class SlidingWindowMaximum {

    public static void main(String[] args) {

        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] result = maxSlidingWindowPriorityQueue(nums, k);
        int[] result2 = maxSlidingWindow(nums, k);
        for (int n : result2)
            System.out.print(n + " ");

    }

    /*
     * time complexity - O(n*k), since queue.remove(Object obj) requires O(k) time
     *
     * */

    public static int[] maxSlidingWindowPriorityQueue(int[] nums, int k) {

        int len = nums.length;
        int[] ret = new int[len - k + 1];
        Queue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return Integer.compare(b, a); // return the larger one
            }
        });
        for (int i = 0; i < k; i++)
            queue.add(nums[i]);

        ret[0] = queue.peek();

        for (int i = k; i < len; i++) {
            queue.remove(nums[i - k]);
            queue.add(nums[i]);
            ret[i - k + 1] = queue.peek();
        }
        return ret;
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {

        if (nums.length == 0 || k <= 0)
            return new int[]{};

        int len = nums.length;
        int[] ret = new int[len - k + 1];

        // store index
        Deque<Integer> queue = new ArrayDeque<Integer>();

        int reti = 0;
        for (int i = 0; i < len; i++) {

            // remove the element from the head of queue at place less than i-k+1
            if (!queue.isEmpty() && queue.peek() < (i - k + 1))
                queue.poll();


            // remove elements smaller than nums[i] in queue and in k range
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i])
                queue.pollLast();

            queue.add(i);

            if (i >= k - 1) {
                ret[reti] = nums[queue.peek()];
                reti++;
            }
        }
        return ret;

    }
}
