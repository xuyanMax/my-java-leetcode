package dp.slidingWindow;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by xu on 2022/10/15 16:59.
 */
public class minSizeSubArraySumWithNegative {
    public int shortestSubarray(int[] A, int K) {
        int[] presum = new int[A.length + 1];
        //presum[i] 代表A[0:i-1]前缀数组和
        for (int i = 1; i < presum.length; i++)
            presum[i] += A[i - 1];
        Deque<Integer> deque = new ArrayDeque<>();
        int min_len = Integer.MAX_VALUE;

        // 上限 presum 长度
        for (int i = 0; i < presum.length; i++) {
            while (!deque.isEmpty() && presum[i] - presum[deque.peekFirst()] >= K) //因为首先添加的是presum[0]=0, so index is 1 based
                min_len = Math.min(min_len, i - deque.pollFirst());
            while (!deque.isEmpty() && presum[i] <= presum[deque.peekLast()])
                deque.pollLast();
            deque.addLast(i);
        }
        return min_len == Integer.MIN_VALUE ? -1 : min_len;

    }
}
