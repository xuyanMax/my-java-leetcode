package greedy;

import java.util.PriorityQueue;

/**
 * @Author: xyx
 * @Date: 2019-06-30 01:51
 * @Version 1.0
 * <p>
 * 1 — Basic Interval Scheduling Problem
 * Our objective is to maximize the number of selected intervals in a given time-frame.
 * Formally speaking, we have a set of requests {1, 2, …, n};
 * the i-th request corresponds to an interval of time starting at s(i) and finishing at f(i).
 * A subset of the requests is compatible if no two of them overlap in time,
 * and our goal is to accept as large a compatible subset as possible.
 *
 * nlgn
 */
public class IntervalSchedule {

    public int interval(int[][] nums) {

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));

        for (int[] num : nums) {
            pq.add(num);
        }

        int count = 0;
        int sofar = 0;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int s = curr[0], e = curr[1];
            count++;
            while (!pq.isEmpty() && pq.peek()[0] < sofar) {
                pq.poll();
            }
        }
        return count;
    }
}
