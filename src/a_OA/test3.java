package a_OA;

import java.util.HashMap;
import java.util.Map;

public class test3 {
    public static int solution(int[] A) {
        // write your code in Java SE 8
        int sum = 0, res = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            if (preSum.containsKey(sum))
                res += preSum.get(sum);

            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}
