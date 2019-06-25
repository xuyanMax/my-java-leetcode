package exams;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by xu on 27/08/2017.
 */
public class Jbit_170827_2 {
    public static void main(String[] args) {


        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {//注意while处理多个case
            int m = in.nextInt();
            int n = in.nextInt();
            int[][] map = new int[m][n];

            // 创建地图
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++)
                    map[i][j] = in.nextInt();
            }
            //反向推到，只能向"上"、向"左移动"
            // Dijkstra


        }
    }
}
