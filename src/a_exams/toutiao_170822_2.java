package a_exams;

import java.util.Scanner;

/**
 * Created by xu on 22/08/2017.
 * <p>
 * 时间限制：C/C++语言 1000MS；其他语言 3000MS
 * 内存限制：C/C++语言 131072KB；其他语言 655360KB
 * 题目描述：
 * 给定一个数组序列, 需要求选出一个区间, 使得该区间是所有区间中经过如下计算的值最大的一个：
 * 区间中的最小数 * 区间所有数的和
 * 最后程序输出经过计算后的最大值即可，不需要输出具体的区间。如给定序列  [6 2 1]则根据上述公式, 可得到所有可以选定各个区间的计算值:
 * [6] = 6 * 6 = 36;
 * [2] = 2 * 2 = 4;
 * [1] = 1 * 1 = 1;
 * [6,2] = 2 * 8 = 16;
 * [2,1] = 1 * 3 = 3;
 * [6, 2, 1] = 1 * 9 = 9;
 * 从上述计算可见选定区间 [6] ，计算值为 36， 则程序输出为 36。
 * 区间内的所有数字都在[0, 100]的范围内;
 * 输入
 * 第一行输入数组序列个数，第二行输入数组序列。
 * 输出
 * 输出数组经过计算后的最大值。
 * <p>
 * 样例输入
 * 3
 * 6 2 1
 * 样例输出
 * 36
 * <p>
 * Hint
 * 对于 50%的数据,  1 <= n <= 10000;
 * 对于 100%的数据, 1 <= n <= 500000;
 */
public class toutiao_170822_2 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int N = 0;
        Wrapper maxWrapper;
        while (cin.hasNextInt()) {

            maxWrapper = new Wrapper(0);
            N = cin.nextInt();
            int[] nums = new int[N];

            for (int i = 0; i < N; i++)
                nums[i] = cin.nextInt();

            dfs(nums, 0, N - 1, maxWrapper);

            System.out.println(maxWrapper.max);

        }

    }

    public static void dfs(int[] nums, int left, int right, Wrapper maxWrapper) {

        if (left == right) {
            maxWrapper.max = Math.max(nums[left] * nums[left], maxWrapper.max);
            return;
        }
        int presum = 0;
        int min = Integer.MAX_VALUE;
        for (int i = left; i <= right; i++) {
            presum += nums[i];
            min = Math.min(nums[i], min);

            maxWrapper.max = Math.max(maxWrapper.max, presum * min);

            dfs(nums, i + 1, right, maxWrapper);

        }
    }

    static class Wrapper {
        int max;

        public Wrapper(int max) {
            this.max = max;
        }
    }
}
