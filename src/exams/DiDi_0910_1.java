package exams;

import java.util.Scanner;

/**
 * Created by xu on 10/09/2017.
 */
public class DiDi_0910_1 {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {//注意while处理多个case
            int n = in.nextInt();
            if (n < 1) {
                System.out.println(1);
                return;
            }
            int num = 0;
            int findCount = 0;

            while(findCount < n) {
                int i = ++num;
                while (i%2 == 0)
                    i/=2;
                while(i%3 == 0)
                    i/=3;
                while (i%5 == 0)
                    i/=5;
                if (i == 1)
                    findCount++;
            }
            System.out.println(num);
        }
    }
}
