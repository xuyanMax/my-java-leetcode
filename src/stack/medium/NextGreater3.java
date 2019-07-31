package stack.medium;

import java.util.Arrays;

/**
 * @Author: xyx
 * @Date: 2019-07-31 22:47
 * @Version 1.0
 *
 * 556. Next Greater Element III
 *
 * Given a positive 32-bit integer n, you need to find the smallest 32-bit integer
 * which has exactly the same digits existing in the integer n and is greater in value than n.
 * If no such positive 32-bit integer exists, you need to return -1.
 *
 * Example 1:
 *
 * Input: 12
 * Output: 21
 *
 *
 * Example 2:
 *
 * Input: 21
 * Output: -1
 *
 * edge cases
 * 1) descending order 987654321, return -1
 * 2) ascending order  123456789, swap the last two, return 123456798
 * 3) somewhere in between 123498765, find from the tail the first element smaller than next one, which is 4 < 9
 *    find an element next greater than 4, which is 5 in "98765", swap 4 and 5, we get 123598764. Sort sub array ""98764
 *    to "46789". Finally, we get 123546789.
 */
public class NextGreater3 {
    public int nextGreaterElement(int n) {
        char[] number = (n + "").toCharArray();

        int i, j;
        // I) Start from the right most digit and
        // find the first digit that is
        // smaller than the digit next to it.
        for (i = number.length - 1; i > 0; i--)
            if (number[i - 1] < number[i])
                break;

        // If no such digit is found, its the edge case 1.
        if (i == 0)
            return -1;

        // II) Find the smallest digit on right side of (i-1)'th
        // digit that is greater than number[i-1]
        int x = number[i - 1], smallest = i;
        for (j = i + 1; j < number.length; j++)
            if (number[j] > x && number[j] <= number[smallest])
                smallest = j;

        // III) Swap the above found smallest digit with
        // number[i-1]
        char temp = number[i - 1];
        number[i - 1] = number[smallest];
        number[smallest] = temp;

        // IV) Sort the digits after (i-1) in ascending order
        Arrays.sort(number, i, number.length);

        long val = Long.parseLong(new String(number));
        return (val <= Integer.MAX_VALUE) ? (int) val : -1;
    }
}
