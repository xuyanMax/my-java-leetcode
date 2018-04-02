package exams.Net;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by xu on 12/08/2017.
 */
public class Net_170812_3_2 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {//注意while处理多个case
            int n = in.nextInt();
            int k = in.nextInt();
            Map<String, Integer> map = new HashMap<>();


            int count = helper (n,0, k, 1, map);

            System.out.println(count % 1000000007);
//            System.out.println(wrapper.count % 1000000007);
        }
    }

    // visit + cache
    public static int helper(int n, int depth, int k, int ele, Map<String, Integer>map){

        //利用cache 避免重复运算
        String key = depth + "->" + ele;
        if (map.containsKey(key)) {
            return map.get(key);
        }

        //边界判断
        int val = 0;
        if(depth == n-1) {
            for (int j=1; j<=k; j++) {
                if ((ele % j) != 0 || ele <= j) {
                   val++;
                }
            }
            return val;
        }

        // visit
        int count = 0;
        for (int j=1; j<=k; j++) {
            if ((ele % j) != 0 || ele <= j) {
                 val = helper(n, depth + 1, k, j, map);
                 count += val;
            }
        }
        // 存储val值
        map.put(key, count);
        return count;
    }
}
