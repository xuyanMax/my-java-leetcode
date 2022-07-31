package a_exams.Net;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by xu on 28/09/2017.
 */
public class DengChaShuLie {//判断一个数列是否能通过多次交换元素，成为等差数列。。。。

    public static void main(String[] args) {


    }

    public static void sol_easy() {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt();
            int[] input = new int[n];
            for (int i = 0; i < n; i++)
                input[i] = in.nextInt();

            // 先排序
            Arrays.sort(input);
            int d = input[1] - input[0];
            for (int i = 2; i < n; i++)
                if (input[i] - input[i - 1] != d) {
                    System.out.println("Impossible");
                    return;
                }
            System.out.println("Possible");
        }
    }
}
