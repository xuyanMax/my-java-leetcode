package string;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xu on 05/09/2017.
 * <p>
 * 532. K-diff Pairs in an Array
 * Given an arr of integers and an integer k, you need to find the number of unique k-diff pairs
 * in the arr. Here a k-diff pair is defined as an integer pair (i, j), where i and j are both numbers in the arr and their absolute difference is k.
 * <p>
 * Example 1:
 * Input: [3, 1, 4, 1, 5], k = 2
 * Output: 2
 * Explanation: There are two 2-diff pairs in the arr, (1, 3) and (3, 5).
 * Although we have two 1s in the input, we should only return the number of unique pairs.
 * <p>
 * Example 2:
 * Input:[1, 2, 3, 4, 5], k = 1
 * Output: 4
 * Explanation: There are four 1-diff pairs in the arr, (1, 2), (2, 3), (3, 4) and (4, 5).
 * <p>
 * Example 3:
 * Input: [1, 3, 1, 5, 4], k = 0
 * Output: 1
 * Explanation: There is one 0-diff pair in the arr, (1, 1).
 * <p>
 * Note:
 * The pairs (i, j) and (j, i) count as the same pair.
 * The length of the arr won't exceed 10,000.
 * All the integers in the given input belong to the range: [-1e7, 1e7].
 */
public class KdiffParisInArray {

    //利用hashmap计算num出现的个数，
    public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0)
            return 0;
        Map<Integer, Integer> map = new HashMap();

        for (int num : nums)
            map.put(num, map.getOrDefault(num, 0) + 1);

        int count = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (k == 0) {
                if (entry.getValue() >= 2) //出现重复的多个，算一个
                    count++;
            } else {
                //二选一
                int a = entry.getKey() + k;
//                int a = entry.getKey() - k;
                if (map.containsKey(a))
                    count++;
            }

        }
        return count;

    }
}
