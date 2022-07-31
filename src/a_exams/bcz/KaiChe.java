package a_exams.bcz;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by xu on 01/10/2017.
 */
public class KaiChe {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt();
            int[][] data = new int[n][3];

            for (int i = 0; i < n; i++)
                for (int j = 0; j < 3; j++)
                    data[i][j] = in.nextInt();
            float start = in.nextFloat();
            float end = in.nextFloat();

            //sanity check
            if (start < data[0][0] || end > data[n - 1][1])
                return;

            sol_1(data, start, end);
            sol_2(data, start, end);

        }
    }

    //时间和空间复杂度均为 O(n)
    public static void sol_1(int[][] data, float start, float end) {

        int i = 0;
        float time = 0f;
        //定位start开始位置的路段
        while (i < data.length && data[i][1] < start) i++;

        while (i < data.length && data[i][1] < end) {//计算路段的终点始终小于end
            time += (data[i][1] - start) / data[i][2];
            start = data[i][1];//更新 start为下一个路边的起始点
            i++;
        }
        // 最后一段路单独计算
        time += (end - data[i][0]) / data[i][2];
        System.out.printf("%.2f%n", time);

    }

    //时间复杂度 O(lgn + k), 最坏O(n)
    //空间复杂度 O(n)
    public static void sol_2(int[][] data, float start, float end) {
        int n = data.length;
        int[] arr = new int[n]; // sorted arr
        for (int i = 0; i < n; i++)
            arr[i] = data[i][0];
        int ind_start = Arrays.binarySearch(arr, (int) Math.ceil(start));
        ind_start = ind_start >= 0 ? ind_start : ~ind_start;//要插入的位置如果start在数组中不存在且在数组范围内

        int ind_end = Arrays.binarySearch(arr, (int) Math.ceil(end));
        ind_end = ind_end >= 0 ? ind_end : ~ind_end;//要插入的位置如果end在数组中不存在且在数组范围内

        float time = 0f;
        //如果ind_start返回值为0，说明start在数组中存在，为防止越界，不做-1处理
        //否则不存在，做-1处理，定位对应区间
        ind_start = ind_start == 0 ? ind_start : ind_start - 1;
        int i;

        for (i = ind_start; i < ind_end - 1; i++) {
            time += (data[i][1] - start) / data[i][2];
            start = data[i][1];
        }
        //单独计算最后一段
        time += (end - data[i][0]) / data[i][2];
        System.out.printf("%.2f%n", time);

    }

}
