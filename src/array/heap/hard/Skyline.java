package array.heap.hard;

import java.util.*;

//https://leetcode.com/problems/the-skyline-problem/description/
public class Skyline {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> result = new ArrayList<>();
        List<int[]> height = new ArrayList<>();
        for (int[] b : buildings) {
            // start point has negative height value
            height.add(new int[]{b[0], -b[2]});
            // end point has normal height value
            height.add(new int[]{b[1], b[2]});
        }

        // sort $height, based on the first value, if necessary, use the second to
        // break ties
        // 对critical points 排序，结果为，按照x递增顺序排序矩阵端点
        Collections.sort(height, (a, b) -> {
            if (a[0] != b[0])
                return a[0] - b[0];
            return a[1] - b[1];
        });

        // Use a maxHeap to store possible heights
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));

        // Provide a initial value to make it more consistent
        pq.offer(0);

        // Before starting, the previous max height is 0;
        int prev = 0;

        // visit all points in order
        for (int[] h : height) {
            if (h[1] < 0) { // a start point, add height
                pq.offer(-h[1]);
            } else {  // a end point, remove height
                pq.remove(h[1]);
            }
            int cur = pq.peek(); // current max height;

            // compare current max height with previous max height, updateHighestHeightBtwLR result and
            // previous max height if necessary
            if (prev != cur) {
                result.add(new int[]{h[0], cur});
                prev = cur;
            }
        }
        return result;
    }
}
