package nowcoder;

/**
 * 时间限制：1秒 空间限制：32768K 热度指数：163490
 * 本题知识点： 数组
 * 算法知识视频讲解
 * 题目描述
 * 一个整型数组里除了两个数字之外，其他的数字都出现了偶数次。请写程序找出这两个只出现一次的数字。
 */
public class AppearingOnceInArray {
    /**
     * 如果只有一个数字出现一次，其他数字出现偶数次，那么通过异或可以锁定该数字
     * 如果有两个数字出现一次，其他数字出现偶数次，那么最终结果一定不是零，两个数字既然不同，那么至少有一位分别是0和1，
     * 我们找到异或的结果中，
     * 第一次出现1的位置，然后通过该位的遍历整个数组，并判断，如果该位是1，那么异或num1，如果是0，那么异或num2，
     * 最终得到num1和num2
     */
    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        if (array == null || array.length < 2)
            return;
        int temp = 0;
        for (int i = 0; i < array.length; i++)
            temp ^= array[i];

        int indexOf1 = findFirstBitIs(temp);
        for (int i = 0; i < array.length; i++) {
            if (isBit(array[i], indexOf1))
                num1[0] ^= array[i];
            else
                num2[0] ^= array[i];
        }
    }

    public int findFirstBitIs(int num) {
        int indexBit = 0;
        while (((num & 1) == 0) && (indexBit) < 8 * 4) {
            num = num >> 1;
            ++indexBit;
        }
        return indexBit;
    }

    public boolean isBit(int num, int indexBit) {
        num = num >> indexBit;
        return (num & 1) == 1;
    }

    /**
     * flag:最后一个出现的1
     *
     * @param array
     * @param num1
     * @param num2
     */
    public void FindNumsAppearOnce2(int[] array, int num1[], int num2[]) {
        if (array.length < 2) return;
        int myxor = 0;
        int flag = 1;
        for (int i = 0; i < array.length; ++i)
            myxor ^= array[i];
        while ((myxor & flag) == 0) flag <<= 1;
        // num1[0] = myxor;
        //num2[0] = myxor;
        for (int i = 0; i < array.length; ++i) {
            if ((flag & array[i]) == 0)
                num2[0] ^= array[i];
            else
                num1[0] ^= array[i];
        }


    }
}
