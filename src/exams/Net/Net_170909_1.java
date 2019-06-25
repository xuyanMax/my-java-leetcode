package exams.Net;

import java.util.Scanner;

/**
 * Created by xu on 09/09/2017.
 */
public class Net_170909_1 {
    /*
    * 数组两种情况:
    * 1. 由4的倍数和奇数组成，只需要满足 numOfFour >= odds-1;
      2. 存在4的倍数，只能被2整除的数和奇数，只需要将只能被2整除的数放在一起即可
         数组中需要 numOfFour >= odds 即可，如6,14,18,4,奇数，4，奇数......
    * */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {//注意while处理多个case

            int count = in.nextInt();

            while (count > 0) {
                count--;
                int num = in.nextInt();
                int cnt2 = 0, cnt1 = 0, cnt4 = 0;
                //记录数据
                for (int i = 0; i < num; i++) {
                    int n = in.nextInt();
                    if (n % 4 == 0)
                        cnt4++;
                    else if (n % 2 == 0)
                        cnt2++;
                    else
                        cnt1++;
                }

//                if (cnt4 >= num/2 || cnt4 >= cnt1)
//                    System.out.println("Yes");
//                else
//                    System.out.println("No");
                if (cnt2 == 0) {
                    if (cnt4 >= cnt1 - 1)
                        System.out.println("Yes");
                    else
                        System.out.println("No");
                } else {
                    if (cnt4 >= cnt1)
                        System.out.println("Yes");
                    else
                        System.out.println("No");
                }
            }

        }

    }
}
