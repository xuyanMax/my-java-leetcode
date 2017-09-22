package exams;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by xu on 18/09/2017.
 */
public class WuYi_170918_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println(51 & 192);
        while (in.hasNextInt()) {//注意while处理多个case
            String str1 = in.next();
            String str2 = in.next();

            String[] str1_ = str1.split(" ");
            String[] str2_ = str2.split(" ");
            int n = str1_.length;
            int[] wt = new int[n];
            int[] vote = new int[n];

            for (int i=0; i<n; i++){
                vote[i] = Integer.parseInt(str1_[i]);
                wt[i] = Integer.parseInt(str2_[i]);
            }

            int[] map = new int[10000];//存储第i个选者的权重和
            int max1 = 0, max2 = 0, ind1 = 0, ind2 = 0, cnt1=0, cnt2=0;
            for (int i=0; i<n; i++) {
                map[vote[i]] += wt[i];
                if (map[vote[i]] > max1) {//存储最大的权重和、第二大权重、索引
                    max2 = max1;
                    ind2 = ind1;//更新索引
                    ind1 = i;
                    max1 = map[vote[i]];
                }
                else if (map[vote[i]] <= max1 && map[vote[i]] > max2) {
                    max2 = map[vote[i]];
                    ind2 = i;
                }
            }
            if (max1 != max2)
                System.out.println(vote[ind1]);
            else {
                for (int i=0; i<n; i++) {
                    if (vote[i] == vote[ind1])
                        cnt1++;
                    else if (vote[i] == vote[ind2])
                        cnt2++;
                }
                System.out.println(cnt1>cnt2?vote[ind1]:vote[ind2]);
            }

        }

    }
}
