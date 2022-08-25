package slidingWindow;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;


public class maxSlidingWindow {

    //failed leetcode tests. k=50000
    public static int[] maxWindowPriority(int[] nums, int k) {
        Queue<Integer> queue = new PriorityQueue<Integer>((a, b) -> Integer.compare(b, a));

        int[] ret = new int[nums.length - k + 1];
        int i = 0;
        for (; i < nums.length; i++) {
            if (i < k)
                queue.add(nums[i]);
            else {
                ret[i - k] = queue.peek();
                queue.remove(nums[i - k]);
                queue.add(nums[i]);
            }
        }
        ret[i - k] = queue.peek();
        return ret;

    }

    public static int[] maxWindowDequeue(int[] nums, int k) {
        Deque<Integer> q = new ArrayDeque<>();
        int[] ret = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {

            // remove out of range k index from the head of queue
            while (!q.isEmpty() && q.peekFirst() < i - k + 1)
                q.pollFirst();
            // remove smaller than nums[i] numbers from the tail of the queue
            while (!q.isEmpty() && nums[q.peekLast()] < nums[i])
                q.pollLast();
            // add to tail of the queue
            q.addLast(i);

            if (i >= k-1)
                ret[i - k + 1] = nums[q.peekFirst()];
        }
        return ret;
    }

    @Test
    public void test() {
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int[] res = maxSlidingWindow.maxWindowPriority(nums, 3);
        for (int re : res) {
            System.out.println(re);
        }
//        Assert.assertArrayEquals();
    }

}