package nowcoder;

import java.util.ArrayList;

/**
 * 链接：http://www.nowcoder.com/questionTerminal/390da4f7a00f44bea7c2f3d19491311b?orderByHotValue=1&questionTypes=000100&page=1&onlyReference=false
 * 来源：牛客网
 * <p>
 * [编程题]和为S的两个数字
 * 热度指数：152355时间限制：1秒空间限制：32768K
 * <p>
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
 * 输出描述:
 * 对应每个测试案例，输出两个数，小的先输出。
 */
public class TwoSumMinProduct {

    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        ArrayList<Integer> res = new ArrayList<>();
        int left = 0, right = array.length - 1;
        while (left < right) {
            if (array[left] + array[right] == sum) {
                res.add(array[left]);
                res.add(array[right]);
                break;
            }
            while (left < right && array[left] + array[right] > sum) right--;
            while (left < right && array[left] + array[right] < sum) left++;
        }
        return res;
    }
}
