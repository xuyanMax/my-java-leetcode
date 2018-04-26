package dynamic.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
523. Continuous Subarray Sum
Given a list of non-negative numbers and a target integer k, write a function to check if
the arr has a continuous subarray of size at least 2 that sums up to the multiple of k,
that is, sums up to n*k where n is also an integer.

Example 1:
Input: [23, 2, 4, 6, 7],  k=6
Output: True

Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.

Example 2:
Input: [23, 2, 6, 4, 7],  k=6
Output: True

Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.

Note:
The length of the arr won't exceed 10,000.
You may assume the sum of all the numbers is in the range of a signed 32-bit integer.

*/
public class ContinuousSubarraySum {

    // 数学方法
    // 利用余数的原理 (a + n*k) %k == a%k;
    // 举例来说，[23,2,6,4,7], presum [23,25,31,35,42] 余数 [5,1,1,5,0]
    // 可以看出，我们得到了余数5在索引0和3的位置，因此在索引1／2一定出现了一个k=6的倍数
    public boolean math_solution(int[] array, int k) {

        // Since the size of subarray is at least 2.
        if (array.length < 2)
            return false;
        // Two continuous "0" will form a subarray which has sum = 0. 0 * k == 0 will always be true.
        for (int i=0; i<array.length-1; i++)
            if (array[i] ==0 && array[i+1] ==0)
                return true;
        // after above, k can't be "0"
        if (k==0)
            return false;

        // presum -> index
        Map<Integer, Integer> maps = new HashMap<>();
        maps.put(0,-1);// 初始化，保证算法顺利实行,针对 如果[5,2,4], [5,5,2]

        int presum = 0;
        for (int i=0; i<array.length; i++) {

            presum += array[i];
            presum %= k; //防止int溢出
            Integer preIndex = maps.get(presum);

            if (preIndex != null)
                if (( i - preIndex) > 1) // 至少两个元素
                    return true;
            else
                maps.put(presum, i);

        }
        return false;

    }
    public boolean set_solution(int[] array, int k) {
        // a lotta corner cases
        if (array.length < 2)
            return false;
        for (int i=0; i<array.length-1; i++)
            if (array[i] ==0 && array[i+1] ==0)
                return true;
        if (k==0)
            return false;
        Set<Integer> set = new HashSet<>();
        int presum = 0;
        for (int i=0; i<array.length; i++) {
            presum += array[i];

            if (i >= 1) {
                for (int multiple = presum/k*k; multiple >=k; multiple -= k) {
                    // [1,2,3,4]
                    // set 中包含1，2时，1+2+3=6, 6/5*5 = 5
                    // set中包含(6-5)，因此返回true
                    if (set.contains(presum - multiple))
                        return true;
                }
            }
            set.add(presum);
        }
        return false;
    }
}
