package string;

import com.sun.tools.corba.se.idl.InterfaceGen;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by xu on 08/09/2017.
 */
public class MinimumTimeDifference {
     public static void main(String[] args){
         List<String> places = Arrays.asList("00:00", "23:59", "00:00");
         System.out.println(findMinDifference(places));

     }

    public static int findMinDifference(List<String> timePoints) {
        List<Integer> list = new ArrayList();
        int min = 0;
        for (String str:timePoints){
            String[] seg = str.split(":");
            if (seg[0].equals("00"))
                min = 24*60 + Integer.parseInt(seg[1]);
            else
                min = Integer.parseInt(seg[0])*60 + Integer.parseInt(seg[1]);
            list.add(min);
            min = 0;
        }
        Collections.sort(list);
        int diff = Integer.MAX_VALUE;
        for (int i=0; i<list.size()-1; i++) {
            diff = Math.min(diff, list.get(i+1) - list.get(i));
        }
        return diff;
    }

    public int bucket(List<String> timePoints) {
         boolean[] b = new boolean[24*60];
         for (String s:timePoints) {
             String[] time = s.split(":");
             int m = Integer.parseInt(time[0])*60 + Integer.parseInt(time[1]);

             if (b[m]) // duplicates
                 return 0;
             b[m] = true;
         }
         int pre = -24*60;
         int max = Integer.MIN_VALUE;
         int min = Integer.MAX_VALUE;
         int res = 24*60;

         for (int i=0; i<24*60; i++) {
             if (b[i]) { // 存在
                 if (min != Integer.MAX_VALUE) //min不再是缺省值，而是至少为第一个值
                     res = Math.min(i - pre, res);
                 max = Math.max(max, i);
                 min = Math.min(min, i);
                 pre = i;
             }
         }
         // min --- max --- 24*60
        // 比较max-min 与 24*60-(max-min)大小
        // 即0-min + max-24*60大小 VS. max-min
        // 小的为最小Time Difference
        res = Math.min(res, 24*60 - (max - min));
        return res;
    }
}
/*
["12:12","00:13"]
Output:
721
Expected:
719

*/
