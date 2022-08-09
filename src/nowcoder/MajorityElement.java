package nowcoder;

import java.util.Arrays;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/e8a1b01a2df14cb2b228b30ee6a92163?orderByHotValue=1&questionTypes=000100&page=2&onlyReference=false
 * 来源：牛客网
 * <p>
 * [编程题]数组中出现次数超过一半的数字
 * 热度指数：247799时间限制：1秒空间限制：32768K
 * 算法知识视频讲解
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 */
public class MajorityElement {

    /**
     * 如果有符合条件的数，那么它出现的次数比其他所有数字出现的次数和都要多
     *
     * @param arr
     * @return
     */
    public int sol1(int[] arr) {
        if (arr == null || arr.length == 0)
            return 0;
        Arrays.sort(arr);
        return arr[arr.length / 2];
    }

    // majority element
    public int sol2(int[] arr) {
        if (arr == null || arr.length == 0)
            return 0;
        int cnt = 0, majority = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == majority) {
                cnt++;
            } else cnt--;
            if (cnt == 0) {
                majority = arr[i];
                cnt = 1;//重新标记为1
            }
        }
        //验证
        cnt = 0;
        for (int num : arr)
            if (num == majority)
                cnt++;
        if (cnt << 1 > arr.length)
            return majority;
        else return 0;
    }
}
