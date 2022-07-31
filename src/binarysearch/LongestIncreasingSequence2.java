package binarysearch;

import java.util.Arrays;
//
public class LongestIncreasingSequence2 {
    /**
     * 描述
     * 给定数组 arr ，设长度为 n ，输出 arr 的最长上升子序列。（如果有多个答案，请输出其中 按数值(注：区别于按单个字符的ASCII码值)进行比较的 字典序最小的那个）
     *
     * 数据范围：0 \le n \le 200000 , 0 \le arr_i \le 10000000000≤n≤200000,0≤arr
     * i
     * ​
     *  ≤1000000000
     * 要求：空间复杂度 O(n)O(n)，时间复杂度 O(nlogn)O(nlogn)
     * 示例1
     * 输入：
     * [2,1,5,3,6,4,8,9,7]
     * 复制
     * 返回值：
     * [1,3,4,8,9]
     * 复制
     * 示例2
     * 输入：
     * [1,2,8,6,4]
     * 复制
     * 返回值：
     * [1,2,4]
     * 复制
     * 说明：
     * 其最长递增子序列有3个，（1，2，8）、（1，2，6）、（1，2，4）其中第三个 按数值进行比较的字典序 最小，故答案为（1，2，4）
     */

    /**
     * return the longest increasing subsequence
     *两步走：
     *
     * 第一步——求最长递增子序列长度
     * 第二步——求字典序靠前的子序列
     * 对于第一步，有两种解法：
     *
     * 动态规划，时间复杂度为O(n^2)，会超时
     * 贪心+二分，时间复杂度为O(nlogn)
     * 下面说说贪心+二分的解法，举例说明基本思路，假设数组arr为[2, 3, 1, 2, 3]，vec数组里面存放递增子序列，maxLen数组里存放以元素i结尾的最大递增子序列长度，那么遍历数组arr并执行如下更新规则:
     *
     * 初始情况下，vec为[2]，maxLen[1]
     * 接下来遇到3，由于vec最后一个元素小于3，直接更新，vec为[2,3]，maxLen[1,2]
     * 接下来遇到1，由于vec最后的元素大于1, 我们在vec中查找大于等于1的第一个元素的下标，并用1替换之，此时vec为[1,3], maxLen[1,2,1]
     * 接下来遇到2，由于vec最后的元素大于2，我们在vec中查找大于等于2的第一个元素的下标，并用2替换之，此时vec为[1,2], maxLen[1,2,1,2]
     * 接下来遇到3，由于vec最后一个元素小于3，直接更新，vec为[1,2,3]，maxLen为[1,2,1,2,3]
     * 此时vec的大小就是整个序列中最长递增子序列的长度（但是vec不一定是本题的最终解）
     * @param arr int整型一维数组 the array
     * @return int整型一维数组
     */
    public int[] LIS(int[] arr) {
        // write code here
        int n = arr.length;
        int[] maxLen = new int[n], dp = new int[n];

        int pos = 0;
        dp[0] = arr[0];
        maxLen[0] = 1;

        for (int i = 1; i < n; i++) {
            if (arr[i] > dp[pos]) {
                pos++;
                dp[pos] = arr[i];
                maxLen[i] = pos + 1;//index + 1
            } else {
                int index = Arrays.binarySearch(dp, 0, pos, arr[i]);
                if (index < 0)
                    index = -(index) - 1;
                dp[index] = arr[i];
                maxLen[i] = index + 1;
            }
        }
        int[] answer = new int[pos + 1];
        for (int i = n - 1; i >= 0; i--) {
            if (maxLen[i] == pos + 1) {
                answer[pos] = arr[i];
                pos--;
            }
        }
        return answer;
    }

}
