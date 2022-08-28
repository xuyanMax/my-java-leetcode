package array.heap.hard;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by xu on 2017/6/26.
 * <p>
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 * <p>
 * Define a pair (u,v) which consists of one element from the first arr and one element from the second arr.
 * <p>
 * Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
 * <p>
 * <p>
 * Given nums1 = [1,1,2], nums2 = [1,2,3],  k = 2
 * <p>
 * Return: [1,1],[1,1]
 * <p>
 * The first 2 pairs are returned from the sequence:
 * [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * <p>
 * <p>
 * Given nums1 = [1,2], nums2 = [3],  k = 3
 * <p>
 * Return: [1,3],[2,3]
 * <p>
 * All possible pairs are returned from the sequence:
 * [1,3],[2,3]
 */
public class Find_K_PairsSmallestSum {

    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> heapq = new PriorityQueue<>((a, b) -> a[1] + a[0] - b[0] - b[1]);
        List<int[]> ret = new ArrayList<>();

        for (int i = 0; i < nums1.length; i++)
            heapq.add(new int[]{nums1[i], nums2[0], 0});

        while (k > 0 && !heapq.isEmpty()) {
            int[] tmp = heapq.poll();
            ret.add(new int[]{tmp[0], tmp[1]});

            if (tmp[2] == nums2.length - 1)
                continue;

            heapq.add(new int[]{tmp[0], nums2[tmp[2] + 1], tmp[2] + 1});
            k--;
        }

        return ret;
    }
}
