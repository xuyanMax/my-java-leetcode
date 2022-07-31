package a_exams;

import java.util.Scanner;

/**
 * Created by xu on 18/08/2017.
 * <p>
 * 时间限制：C/C++语言 1000MS；其他语言 3000MS
 * 内存限制：C/C++语言 65536KB；其他语言 589824KB
 * 题目描述：
 * 字母计算机中存储的形式为ascii码，每一个大写字母都有唯一一个ascii码与之对应，A对应65…，Z对应90。因此，每个单词都会对应一个ascii码，
 * 如ACM = 65 + 67 + 77 =209。现给定每个大写字母的数目，以及要求的单词ascii码范围m,n，请计算出符合条件的单词有多少个
 * (包括和为m和n的情况，不考虑字母顺序如:XYZ和XZY等考虑成同一个单词)，1<=n<m<=500,每个单词中大写字母的个数在0-20之间（含边界）。
 * 输入
 * 输入包含多组数据。第一行为一个正整数T(T<=1000)，表示测试数据的组数。然后是T组测试数据，每组测试数据占两行，第一行包含两个整数n和m，
 * 表示ASCII码的范围。第二行为所使用的大写字母的掩码，共26个数字构成的串，对应于26个大写字母，表示使用对应字母的数量。
 * 输出
 * 对于每一组输入数据，输出一行"Case #k ans",表示第k组数据的结果是ans。
 * <p>
 * 样例输入
 * 2
 * 1 100
 * 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
 * 100 200
 * 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
 * 样例输出
 * Case #1: 3
 * Case #2: 4
 * <p>
 * Hint
 * 说明：请注意严格按照格式进行输出，如 Case #1: 3（Case的C大写，冒号后有空格）
 * 第一组数据即A,B,C各一个的情况下，求ASCII和在1到100的组合，共有三组：即A(65),B(66),C(67)；
 * 第二组在同等情况下求和在100到200范围内的组合，共四组，即AB(131),AC(132),BC(133),ABC(198),其余交换字母位置的可能不考虑。
 * 温馨提示
 * 请尽量在全场考试结束10分钟前调试程序，否则由于密集排队提交，可能查询不到编译结果
 * 点击“调试”亦可保存代码
 * 编程题可以使用本地编译器，此页面不记录跳出次数
 */
public class acmcoder_170818_1 {
    public static Wrapper wrapper;

    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);
        int count, n, m;
        int[] num = new int[26];
        StringBuilder builder;
        while (cin.hasNextInt()) {
            count = cin.nextInt();

            int c = 0;
            builder = new StringBuilder();

            while (count > 0) {
                c++;
                count--;
                wrapper = new Wrapper(0);

                n = cin.nextInt();
                m = cin.nextInt();

                String str = cin.nextLine();
                String[] strs = str.split(" ");

                for (int i = 0; i < 26; i++)
                    num[i] = Integer.valueOf(strs[i]);

                for (int i = 0; i < 26; i++)
                    dfs(num, 0, 0, n, m);
                builder.append("Case#" + c + ": " + wrapper.count + "\n");
            }
            System.out.println(builder.toString());
        }
    }

    public static void dfs(int[] nums, int depth, int presum, int lower_bound, int higher_bound) {
        if (depth == nums.length)
            return;
        if (presum <= higher_bound && presum >= lower_bound)
            wrapper.count++;
        else
            return;

        for (int i = depth; i < nums.length; i++) {
            dfs(nums, i + 1, presum + nums[i], lower_bound, higher_bound);
        }

    }

    static class Wrapper {
        int count;

        public Wrapper(int count) {
            this.count = count;
        }
    }
}
