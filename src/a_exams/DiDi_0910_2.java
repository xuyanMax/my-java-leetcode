package a_exams;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by xu on 10/09/2017.
 */
public class DiDi_0910_2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {//注意while处理多个case
            int n = in.nextInt();
            int[] data = new int[n];

            for (int i = 0; i < n; i++)
                data[i] = in.nextInt();

            Map<Integer, Integer> map = new HashMap<>();

            for (int d : data) {
                if (d <= n) {
                    if (map.containsKey(d))
                        map.put(d, map.get(d) + 1);
                    else
                        map.put(d, 1);
                }
            }

            int count = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int key = entry.getKey();
                if (key == 0) {
                    count++;
                    continue;
                }
                int val = entry.getValue();

                while (val >= 2) {
                    count++;
                    val /= 2;
                }

            }
            System.out.println(count);

        }
    }
}
