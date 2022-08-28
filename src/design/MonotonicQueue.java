package design;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class MonotonicQueue {

    // double-linked queue, monotonically non-increasing queue.
    Deque<Integer> queue = new ArrayDeque<>();

    // push to the tail
    void push (int n) {
        // push the element n while removing those smaller than n from the tail of the queue
        while (!queue.isEmpty() && queue.peekLast() < n)
            queue.pollLast();
        queue.addLast(n);
    }

    //return the max of the queue, which is the head.
    int max() {
        return queue.peekFirst();
    }

    // pop target head element, which can be removed already, so we need to check its existence first.
    void pop(int n) {
        if (queue.peekFirst() == n)
            queue.pollFirst();
    }


    // use monotonic queue to solve sliding window issue.
    public List<Integer> slidingWindow(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        MonotonicQueue mq = new MonotonicQueue();
        int right = 0;
        while (right < nums.length) {
            int curr = nums[right];
            mq.push(curr);
            if (right >= 2) {
                // get max from queue and put it to res
                res.add(mq.max());
                //remove far left num, which may be already removed from the queue by push()
                mq.pop(nums[right - k +1]);
            }
            right++;
        }
        return res;
    }

    @Test
    public void testMonoQueue() {
        int[] nums = new int[] {5,3,2,1,4,7};
        List<Integer> res = slidingWindow(nums, 3);
        for (Integer re : res)
            System.out.println(re);

    }
}
