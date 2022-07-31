package a_exams.aiqiyi;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by xu on 14/10/2017.
 */
public class T1_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int k = sc.nextInt();
        int sum = 0;

        int[] count = new int[26]; //记录char出现的次数
        int[] bucket = new int[50]; //bucket[i]=j代表出现i+1次的字符有j个
        for (int i = 0; i < str.length(); i++)
            count[str.charAt(i) - 'a']++;

        if (k <= 0) {
            for (int n : count)
                sum += n * n;
            System.out.println(sum);
            return;
        } else {
            Arrays.sort(count);

            while (k > 0) {
                for (int i = count.length - 1; i >= 0; i--) {
                    if (count[i] > 0) {
                        count[i]--;
                        k--;
                    }
                    if (k == 0)
                        break;
                }
            }

            for (int n : count)
                sum += n * n;
            System.out.println(sum);
            return;
        }


    }
}
