package nowcoder;

import java.util.ArrayList;
import java.util.List;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/beb5aa231adc45b2a5dcc5b62c93f593?orderByHotValue=1&questionTypes=000100&difficulty=00011&page=1&onlyReference=false
 * 来源：牛客网
 * <p>
 * [编程题]调整数组顺序使奇数位于偶数前面
 * 热度指数：354377时间限制：1秒空间限制：32768K
 * 算法知识视频讲解
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
 * 所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 */
public class SortOddEven {

    /**
     * 类似冒泡排序算法
     *
     * @param array
     */
    public void reOrderArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if (array[j] % 2 == 0 && array[j - 1] % 2 == 1) {
                    int tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                }
            }
        }
    }

    /**
     * 设置两个数组，依次存储奇数、偶数
     *
     * @param array
     */
    public void reOrderArray2(int[] array) {
        List<Integer> odd = new ArrayList<>();

        List<Integer> even = new ArrayList<>();

        for (int num : array)
            if (num % 2 == 0)
                even.add(num);
            else odd.add(num);


        int i = 0;

        for (int num : odd)
            array[i++] = odd.get(i);
        for (int num : even)
            array[i++] = even.get(i - odd.size());
    }

    /**
     * 双指针i, j
     * i从左向由指向第一个偶数，j从i+1开始，找到第一个奇数
     * 将arr[j]放置到arr[i]的位置，并将arr[i:j-1]整体后移一位arr[i+1:j]
     * O(n) 时间
     * O(1) 空间
     *
     * @param array
     */
    public void reOrderArray3(int[] array) {
        if (array == null || array.length == 0)
            return;

        int i = 0, j;
        while (i < array.length) {
            while (i < array.length && array[i] % 2 != 0)
                i++;
            j = i + 1;
            while (j < array.length && array[i] % 2 == 0)
                j++;
            if (j < array.length) {
                int tmp = array[j];
                System.arraycopy(array, i, array, i + 1, j - i);
                /*for (int k = j - 1; k >= i; k--) {
                    array[k + 1] = array[k];
                }*/
                array[i++] = tmp;

            } else break;//i后无奇数
        }
    }

}
