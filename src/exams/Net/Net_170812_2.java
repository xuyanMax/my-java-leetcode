package exams.Net;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by xu on 12/08/2017.
 */
public class Net_170812_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {//注意while处理多个case
            int n = in.nextInt();
            int[][] data = new int[2][n];


            int[] ret = new int[n];// 返回结果

            // 录入 x 坐标
            for (int i=0; i<n; i++)
                data[0][i] = in.nextInt();

            // 录入 y 坐标
            for (int i=0; i<n; i++)
                data[1][i] = in.nextInt();

            // 按横坐标生序排序
            Arrays.sort(data, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            });


        }
    }
}
