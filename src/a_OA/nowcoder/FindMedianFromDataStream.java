package a_OA.nowcoder;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Median is the findMiddle value in an ordered integer list.
 * If the size of the list is even, there is no findMiddle value. So the median is the mean of the two findMiddle value.
 */
public class FindMedianFromDataStream {
    Queue<Integer> smaller = new PriorityQueue<>(), larger = new PriorityQueue<>();

    public void addNum(int num) {
        larger.add(num);
        smaller.add(-larger.poll());
        // smaller -1, larger + 1 ==> larger > smaller (in terms of size)
        if (smaller.size() > larger.size()) //smaller中的最小值，加入到larger中，变换符号后为最大值
            larger.add(-smaller.poll());
    }

    /**
     * larger: 3,4
     * smaller: -2, -1
     *
     * @return
     */
    public double findMean() {
        if (smaller.size() < larger.size()) {//
            return larger.peek();
        } else { //larger == smaller
            return (-smaller.peek() + larger.peek()) / 2.0;
        }
    }

    private int count = 0;
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((a, b) -> (b - a));//存储小数
    private PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();//存数大数

    public void Insert(Integer num) {
        if ((count++ & 1) == 1) {// count为奇数,加入到任意一个堆，比如这里选择大堆
            //必须先加入小堆中筛选最小的，再加入大堆
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        } else {//count为偶数 加入小堆
            //同样道理，加入大堆过滤出最大的小数，再加入小堆
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        }
    }

    public Double GetMedian() {
        if ((count & 1) == 0)
            return new Double((maxHeap.peek() + minHeap.peek())) / 2;
        else
            return new Double(minHeap.peek());
    }
}
