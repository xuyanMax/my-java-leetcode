package arr.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xu
 * <p>
 * 442. Find All Duplicates in an Array
 * <p>
 * Given an arr of integers where 1 ≤ a[i] ≤ n (n = size of arr), some elements appear twice and
 * others appear once.
 * Find all the elements of [1, n] inclusive that do not appear in this arr.
 * <p>
 * Do it without extra space and in O(n) runtime
 */

public class FindAllNumbersDisappeared {

    /**
     * Negate each element and pick out the positive ones as the disappeared integers
     * 两次 for 循环
     */
    static List<Integer> solution(int[] arr) {

        List<Integer> disappeared = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            //因为arr[i]的范围是1-n, arr[i]-1后将是有效的arr数组的某一个索引值
            int index = Math.abs(arr[i]) - 1;

            if (arr[index] > 0)
                arr[index] = -arr[index];
        }

        for (int j = 0; j < arr.length; j++)
            if (arr[j] > 0)
                disappeared.add(j + 1);


        return disappeared;
    }

}
