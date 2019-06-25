package exams.Net;

import java.util.Scanner;

/**
 * Created by xu on 12/08/2017.
 */
public class Net_170812_3_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {//注意while处理多个case
            int n = in.nextInt();
            int k = in.nextInt();

            Wrapper wrapper = new Wrapper(0);

            helper(n, 0, k, 1, wrapper);

            System.out.println(wrapper.count % 1000000007);
        }
    }

    public static void helper(int n, int depth, int k, int ele, Wrapper wrapper) {
        //边界判断
        if (depth == n - 1) {
            for (int j = 1; j <= k; j++) {
                if ((ele % j) != 0 || ele <= j) {
                    wrapper.count++;
                }
            }
            return;
        }

        for (int j = 1; j <= k; j++) {
            if ((ele % j) != 0 || ele <= j) {
                helper(n, depth + 1, k, j, wrapper);
            }
        }

    }

    static class Wrapper {
        int count = 0;

        public Wrapper(int count) {
            this.count = count;
        }
    }
}
