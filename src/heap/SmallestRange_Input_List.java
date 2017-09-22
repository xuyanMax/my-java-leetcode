package heap;

import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by xu on 11/09/2017.
 */
public class SmallestRange_Input_List {
    // min heap
    // O(nk*lgk)
    public int[] smallestRange_heap(List<List<Integer>> list) {

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
                (a,b)->a[2]-b[2]); //int[]存储 i-j
        int max = Integer.MIN_VALUE;
        int range = Integer.MAX_VALUE;

        int start = -1, end = -1;

        for (int i=0; i<list.size(); i++) {
            minHeap.add(new int[]{i, 0, list.get(i).get(0)});
            max = Math.max(max, list.get(i).get(0));
        }

        while (minHeap.size() == list.size()) {
            int[] curr = minHeap.poll();
            if (max - curr[2] < range) {
                range = max - curr[2];
                start = curr[2];
                end = max;
            }
            if (curr[1] + 1 < list.get(curr[0]).size()) {
                curr[1]++;
                curr[2] = list.get(curr[0]).get(curr[1]);

                minHeap.add(curr);

                //if 的位置很重要，必须在add下方
                //因为max要与curr[k][pointer].next元素比较大小
                if (curr[2] > max)
                    max = curr[2];
            }
        }
        return new int[]{start, end};
    }
}
