package exams;

import java.util.Scanner;

/**
 * Created by xu on 12/08/2017.
 */
public class Net_170812_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {//注意while处理多个case
            int x = in.nextInt(); //每日房租
            int f = in.nextInt();//已有的水果个数
            int d = in.nextInt();//手中现金
            int p = in.nextInt();//售卖水果单价

            int days = 0;

            if (d <= x)//手中钱无法付1天房租
                System.out.println(days);
             //手中有水果
            else{
                if (d/x >= f) {// 能通过现有的水果活下去 f 天
                    days += f;
                    d -= x*f; //剩下的钱
                }
                else {//手里的钱支部不起房租f天
                    days = d / x;
                    System.out.println(days);
                    return;
                }
                days += d / (x+p);
                System.out.println(days);
            }

        }
    }
}
