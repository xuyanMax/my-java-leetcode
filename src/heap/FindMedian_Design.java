package heap;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Created by xu on 2017/6/26.
 */
public class FindMedian_Design {
    /**
     * initialize your key structure here.
     */
    private PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder());
    private PriorityQueue<Integer> large = new PriorityQueue<>();
    boolean even = true;


    public void addNum(int num) {
        if (even) {
            large.add(num);
            small.add(large.poll());
        } else {
            small.add(num);
            large.add(small.poll());
        }
        even = !even;
    }

    public double findMedian() {
        return large.size() == small.size() ? (large.peek() + small.peek()) / 2.0 : small.peek();
    }
}
/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */