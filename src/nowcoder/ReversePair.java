package nowcoder;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/96bd6684e04a44eb80e6a68efc0ec6c5?orderByHotValue=1&questionTypes=000100&page=3&onlyReference=false
 * 来源：牛客网
 * <p>
 * <p>
 * [编程题]数组中的逆序对
 * 热度指数：222211时间限制：2秒空间限制：32768K
 * 算法知识视频讲解
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
 * 输入描述:
 * 题目保证输入的数组中没有的相同的数字
 * <p>
 * 数据范围：
 * <p>
 * 对于%50的数据,size<=10^4
 * <p>
 * 对于%75的数据,size<=10^5
 * <p>
 * 对于%100的数据,size<=2*10^5
 * <p>
 * <p>
 * 示例1
 * 输入
 * 1,2,3,4,5,6,7,0
 * 输出
 * 7
 */
public class ReversePair {
    public int reverse(int[] arr) {
        if (arr == null || arr.length == 0)
            return 0;

        int[] sorted = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
            sorted[i] = arr[i];

        return merge(arr, sorted, 0, arr.length - 1) % 1000000007;
    }

    public int merge(int[] arr, int[] sortedCopy, int left, int right) {
        if (left == right)
            return 0;

        int mid = left + (right - left) >> 2;

        int leftCount = merge(arr, sortedCopy, left, mid);
        int rightCount = merge(arr, sortedCopy, mid + 1, right);

        int i = mid, j = right;
        int count = 0;
        int indexSortedCopy = right;
        //从tail对两部分排序
        while (i >= left && j > mid) {
            if (arr[i] > arr[j]) {
                sortedCopy[right--] = arr[i--];
                count += j - (mid + 1) + 1;
            } else {
                sortedCopy[right--] = arr[j--];
            }
        }
        while (i-- > left) {
            sortedCopy[indexSortedCopy--] = arr[i];
        }
        while (j-- > mid) {
            sortedCopy[indexSortedCopy--] = arr[j];
        }
        return leftCount + rightCount + count;

    }
}
