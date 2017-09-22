package math;

import java.util.Scanner;

/**
 * Created by xu on 02/09/2017.
 */
/*
对于一个整数X，定义操作rev(X)为将X按数位翻转过来，并且去除掉前导0。例如:
如果 X = 123，则rev(X) = 321;
如果 X = 100，则rev(X) = 1.
现在给出整数x和y,要求rev(rev(x) + rev(y))为多少？
输入描述:
输入为一行，x、y(1 ≤ x、y ≤ 1000)，以空格隔开。


输出描述:
输出rev(rev(x) + rev(y))的值

输入例子:
123 100

输出例子:
223
*/
public class Reverse {
    private static int rev(int x) {
        String str = String.valueOf(x);
        String s = new StringBuilder(str).reverse().toString();
        return Integer.parseInt(s);
    }

    public static void main(String[] arg) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            int x = scan.nextInt();
            int y = scan.nextInt();
            x = rev(x);
            y = rev(y);
            System.out.println(rev(x + y));
        }
        scan.close();
    }

}
