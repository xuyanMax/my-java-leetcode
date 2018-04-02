package arr.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xu on 26/07/2017.
 */
/*
Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l)
there are such that A[i] + B[j] + C[k] + D[l] is zero.

To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500.
All integers are in the range of -2^28 to 2^28 - 1 and the result is guaranteed to be at most 2^31 - 1.
*/
public class Four4Sum2 {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> maps = new HashMap<>();
        for (int a:A){
            for (int b:B) {
                int sum = a+b;
                maps.put(sum, maps.getOrDefault(sum, 0) + 1);
            }
        }
        int ret = 0;
        for (int c:C){
            for (int d:D) {
                int sum = c+d;
                ret += maps.getOrDefault(-(c+d), 0);
            }
        }
        return ret;
    }
}
