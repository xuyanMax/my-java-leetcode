package exams;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by xu on 10/09/2017.
 */
public class DiDi_0910_2_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {//注意while处理多个case
            int n = in.nextInt();
            int[] data = new int[n];

            for(int i=0; i<n; i++)
                data[i] = in.nextInt();

            int[] map = new int[n+1];

            for (int d:data){
                if (d <= n)
                    map[d]++;
            }

            int count = 0;
            if (map[0]!=0)
                count++;
            for (int i=1; i<n+1; i++) {
                int val = map[i];

                while (val >= 2) {
                    count++;
                    val /= 2;
                }

            }
            System.out.println(count);

        }
    }
}
