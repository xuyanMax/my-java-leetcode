package a_OA.nowcoder; /**
 * @Author: xyx
 * @Date: 2018-12-01 20:16
 * @Version 1.0
 * 链接：https://www.nowcoder.com/questionTerminal/a174820de48147d489f64103af152709
 */

//这个题目其实非常简单，只要考虑两个条件，第一，总数一定能被牛的数量整除，第二，每头牛
//比平均值多出来的苹果数一定能被2整除，不满足这两个条件的输出-1，满足的情况下，将比平均值
//多出的苹果数除2，就是移动次数

import java.util.Scanner;

public class FenApple {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int num = in.nextInt();
            int[] apples = new int[num];
            int sum = 0;
            for (int i = 0; i < num; i++) {
                int a = in.nextInt();
                sum += a;
                apples[i] = a;
            }
            int avg = sum / num;
            if (sum % num != 0) {
                System.out.println(-1);
                return;
            }
            int res = 0;
            for (int n : apples) {
                if (n > avg) {
                    int over = n - avg;
                    if (over % 2 != 0) {
                        System.out.print(-1);
                        return;
                    } else {
                        res += over / 2;
                    }
                }
            }
            System.out.println(res);
        }
    }
}
