package a_exams.Net;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by xu on 28/09/2017.
 * <p>
 * <p>
 * 小易将n个棋子摆放在一张无限大的棋盘上。第i个棋子放在第x[i]行y[i]列。同一个格子允许放置多个棋子。
 * 每一次操作小易可以把一个棋子拿起并将其移动到原格子的上、下、左、右的任意一个格子中。
 * 小易想知道要让棋盘上出现有一个格子中至少有i(1 ≤ i ≤ n)个棋子所需要的最少操作次数.
 * <p>
 * 输入描述:
 * 输入包括三行,第一行一个整数n(1 ≤ n ≤ 50),表示棋子的个数
 * 第二行为n个棋子的横坐标x[i](1 ≤ x[i] ≤ 10^9)
 * 第三行为n个棋子的纵坐标y[i](1 ≤ y[i] ≤ 10^9)
 * <p>
 * <p>
 * 输出描述:
 * 输出n个整数,第i个表示棋盘上有一个格子至少有i个棋子所需要的操作数,以空格分割。行末无空格
 * <p>
 * 如样例所示:
 * 对于1个棋子: 不需要操作
 * 对于2个棋子: 将前两个棋子放在(1, 1)中
 * 对于3个棋子: 将前三个棋子放在(2, 1)中
 * 对于4个棋子: 将所有棋子都放在(3, 1)中
 */
public class DuiQiZi {
    /**/
    private static int manhattan(int x1, int x2, int y1, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }


    private static int get_dist_sum(int[] X, int[] Y, int X0, int Y0, int k) {
        int[] dists = new int[X.length];

        //计算所有点到(X0, Y0)的曼哈顿距离
        for (int i = 0; i < X.length; i++)
            dists[i] = manhattan(X[i], X0, Y[i], Y0);

        //对所有距离dists排序，取前k个的和，返回和
        Arrays.sort(dists);
        int dist_sum = 0;
        for (int i = 0; i < k; i++)
            dist_sum += dists[i - 1];

        return dist_sum;
    }

    /*
    求，让一个格子内出现有k个棋子所需要的最少移动次数（移动k-1个棋子）
    这个函数最关键，计算两个点之间的曼哈顿距离可以直接对两个点横纵坐标距离求和
    所有使得，dist_sum最小的格子的x坐标之一一定有X中的一个，最小的y坐标之一，一定有Y中的一个

    - 遍历每一个备选格子X[i]Y[j]，并计算dist_sum，
    - 求的最小的dist_sum作为移动k-1个棋子到一个格子的对小代价

    */
    private static int min_move(int[] X, int[] Y, int k) {
        int tmp_dist_min = 0;
        int MIN_DIST_SUM = Integer.MAX_VALUE;
        for (int i = 0; i < X.length; i++) {//x坐标
            for (int j = 0; j < X.length; j++) {// y坐标
                tmp_dist_min = get_dist_sum(X, Y, X[i], Y[j], k);
                MIN_DIST_SUM = Math.min(tmp_dist_min, MIN_DIST_SUM);
            }
        }
        return MIN_DIST_SUM;
    }

    private static int[] min_moves(int[] X, int[] Y, int n) {
        int[] res = new int[n];
        for (int k = 2; k <= n; k++) {
            res[k - 1] = min_move(X, Y, k);
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt();
            int[] X = new int[n];
            int[] Y = new int[n];

            for (int i = 0; i < n; i++)
                X[i] = in.nextInt();
            for (int i = 0; i < n; i++)
                Y[i] = in.nextInt();
//            res[] = new int[n];// res[i]表示移动i-1个棋子，使得i个棋子在一个格子所需要的最小移动距离
            int[] res = min_moves(X, Y, n);

            //按要求输出，最后一个数据单独输出
            for (int k = 1; k < n - 1; k++)
                System.out.print(res[k] + " ");
            System.out.println(res[n - 1]);
        }
    }

}
