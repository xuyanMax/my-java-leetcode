package nowcoder;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/1624bc35a45c42c0bc17d17fa0cba788?orderByHotValue=1&questionTypes=000100&difficulty=00010&page=1&onlyReference=false
 * 来源：牛客网
 * <p>
 * [编程题]滑动窗口的最大值
 * 热度指数：125858时间限制：1秒空间限制：32768K
 * <p>
 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
 * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}；
 * 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
 * {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}，
 * {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
 */
public class SlidingMaximum {

    /**
     * 用一个双端队列，队列第一个位置保存当前窗口的最大值，当窗口滑动一次
     * 1.判断当前最大值是否过期
     * 2.新增加的值从队尾开始比较，把所有比他小的值丢掉
     *
     * @param num
     * @param size
     * @return
     */
    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        if (num == null || num.length == 0) return new ArrayList<>();

        //存索引
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < num.length; i++) {
            int leftMargin = i - size + 1;
            if (deque.isEmpty())
                deque.add(i);
                // 头部元素的周期最长，因此要查看头部元素是否过期
            else if (deque.peekFirst() < leftMargin) {
                deque.pollFirst();
            }

            while (!deque.isEmpty() && num[deque.peekLast()] < num[i])
                deque.pollLast();
            deque.add(i);

            if (leftMargin >= 0)
                result.add(num[deque.peekFirst()]);
        }
        return result;

    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return new int[]{};
        LinkedList<Integer> queue = new LinkedList<Integer>();
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            //from the head index < i - k + 1
            if (!queue.isEmpty() && queue.peek() < i - k + 1)
                queue.poll();

            // from the tail of queue, while element smaller than nums[i] in range k
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i])
                queue.pollLast();
            queue.add(i);

            if (i >= k - 1)
                res[i - k + 1] = nums[queue.peek()];
        }

        return res;
    }
}
