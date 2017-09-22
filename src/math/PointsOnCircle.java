package math;

import java.util.Scanner;

/**
 * Created by xu on 02/09/2017.
 */
/*
小易有一个圆心在坐标原点的圆，小易知道圆的半径的平方。小易认为在圆上的点而且横纵坐标都是整数的点是优雅的，
小易现在想寻找一个算法计算出优雅的点的个数，请你来帮帮他。

例如：半径的平方如果为25
优雅的点就有：(+/-3, +/-4), (+/-4, +/-3), (0, +/-5) (+/-5, 0)，一共12个点。
输入描述:
输入为一个整数，即为圆半径的平方,范围在32位int范围内。


输出描述:
输出为一个整数，即为优雅的点的个数

输入例子:
25

输出例子:
12
*/
public class PointsOnCircle {
    public static void main(String[] arg) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            int r2 = scan.nextInt();
            System.out.println(solve(r2));
        }
        scan.close();
    }
    private static int solve(int num) {
        int count = 0;
        for (int x = 1; x*x<num; x++) {
            int x_2 = x*x;
            int y = (int) Math.sqrt(num - x_2);
            if (x_2 + y*y == num)
                count++;
        }

        return count * 4;
    }


}
