package a_exams.Net;

import java.util.Scanner;

/**
 * 为了得到一个数的"相反数",我们将这个数的数字顺序颠倒,然后再加上原先的数得到"相反数"。
 * 例如,为了得到1325的"相反数",首先我们将该数的数字顺序颠倒,我们得到5231,之后再加上原先的数,
 * 我们得到5231+1325=6556.如果颠倒之后的数字有前缀零,前缀零将会被忽略。例如n = 100, 颠倒之后是1.
 * <p>
 * 输入描述:
 * 输入包括一个整数n,(1 ≤ n ≤ 10^5)
 * <p>
 * <p>
 * 输出描述:
 * 输出一个整数,表示n的相反数
 * <p>
 * 输入例子1:
 * 1325
 * <p>
 * 输出例子1:
 * 6556
 */
public class Net_170909_xiang_fan_shu {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int num = in.nextInt();
            int rev = 0;
            int n = num;

            while (n != 0) {
                rev += rev * 10 + n % 10;
                n /= 10;
            }
            System.out.println(n + num);
        }
    }
}
