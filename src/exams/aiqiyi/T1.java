package exams.aiqiyi;

import java.util.Scanner;

/**
 * Created by xu on 14/10/2017.
 */
public class T1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int k = sc.nextInt();
        int sum = 0;

        int[] count = new int[26]; //记录char出现的次数
        int[] bucket = new int[50]; //bucket[i]=j代表出现i+1次的字符有j个
        for (int i=0; i<str.length(); i++)
            count[str.charAt(i) -'a']++;

        if (k <= 0) {
            for (int n:count)
                sum += n*n;
            System.out.println(sum);
            return;
        } else {
//            Arrays.sort(count);
            for (int n:count)
                if (n != 0)
                    bucket[n-1]++;//

            int kk = k;
            for (int i=bucket.length-1; i>= 0; i++) {
                if (kk > 0) {
                    while (kk > 0 && bucket[i] > 0 ) { // kk
                        if (kk >= (i + 1)) {
                            bucket[i]--;
                            kk -= i + 1;
                        } else {
                            break;
                        }
                    }

                }else
                    break;
            }
            for (int i=0; i<bucket.length; i++) {
                sum += (i+1)*(i+1)*bucket[i];
            }
            System.out.println(sum);
            return;
        }



    }

}
