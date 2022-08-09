package nowcoder; /**
 * @Author: xyx
 * @Date: 2018-12-01 17:55
 * @Version 1.0
 * 链接：https://www.nowcoder.com/questionTerminal/661c49118ca241909add3a11c96408c8
 * <p>
 * 有 n 个学生站成一排，每个学生有一个能力值，牛牛想从这 n 个学生中按照顺序选取 k 名学生，
 * 要求相邻两个学生的位置编号的差不超过 d，使得这 k 个学生的能力值的乘积最大，你能返回最大的乘积吗？
 */


import java.util.Scanner;

public class HeChangTuan {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            //总人数
            int n = sc.nextInt();
            //学生能力值数组，第i个人直接对应arr[i]
            int[] arr = new int[n + 1];
            //初始化
            for (int i = 1; i <= n; i++) {//人直接对应坐标
                arr[i] = sc.nextInt();
            }
            //选择的学生数
            int kk = sc.nextInt();
            //间距
            int dd = sc.nextInt();

            /**
             * 递推的时候，以f[one][k]的形式表示
             * 其中：one表示最后一个人的位置，k为包括这个人，一共有k个人
             * 原问题和子问题的关系：f[one][k]=max{f[left][k-1]*arr[one],g[left][k-1]*arr[one]}
             */
            //规划数组
            long[][] f = new long[n + 1][kk + 1];//人直接对应坐标,n和kk都要+1
            long[][] g = new long[n + 1][kk + 1];
            //初始化k=1的情况
            for (int one = 1; one <= n; one++) {
                f[one][1] = arr[one];
                g[one][1] = arr[one];
            }
            //自底向上递推
            for (int k = 2; k <= kk; k++) {
                for (int one = k; one <= n; one++) {
                    //求解当one和k定的时候，最大的分割点
                    long tempmax = Long.MIN_VALUE;
                    long tempmin = Long.MAX_VALUE;
                    for (int left = Math.max(k - 1, one - dd); left <= one - 1; left++) {
                        if (tempmax < Math.max(f[left][k - 1] * arr[one], g[left][k - 1] * arr[one])) {
                            tempmax = Math.max(f[left][k - 1] * arr[one], g[left][k - 1] * arr[one]);
                        }
                        if (tempmin > Math.min(f[left][k - 1] * arr[one], g[left][k - 1] * arr[one])) {
                            tempmin = Math.min(f[left][k - 1] * arr[one], g[left][k - 1] * arr[one]);
                        }
                    }
                    f[one][k] = tempmax;
                    g[one][k] = tempmin;
                }
            }
            //n选k最大的需要从最后一个最大的位置选
            long result = Long.MIN_VALUE;
            for (int one = kk; one <= n; one++) {
                if (result < f[one][kk]) {
                    result = f[one][kk];
                }
            }
            System.out.println(result);
        }
    }
}