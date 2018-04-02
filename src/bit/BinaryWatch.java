package bit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xu on 2017/5/29.
 *
 * A binary watch has 4 LEDs on the top which represent the hours (0-11:1-2-4-8), and the 6 LEDs(1-2-4-8-16-32) on the bottom
 * represent the minutes (0-59).
 * Each LED represents a zero or one, with the least significant bit on the right.
 * Given a non-negative integer n which represents the number of LEDs that are currently on,
 * return all possible times the watch could represent.
 *


 */
public class BinaryWatch {
    public List<String> readBinaryWatch(int n) {
        List<String> result = new ArrayList<String>();

        String [][] hour = {{"0"},// hours contains 0 1's
                {"1","2","4","8"},// hours contains 1 1's
                {"3","5","6","9","10"},// hours contains 2 1's
                {"7","11"}// hours contains 3 1's
        };

        String [][] minite = {{"00"},// mins contains 0 1's
                {"01","02","04","08","16","32"},// mins contains 1 1's
                {"03","05","06","09","10","12","17","18","20","24","33","34","36","40","48"},// mins contains 2 1's
                {"07","11","13","14","19","21","22","25","26","28","35","37","38","41","42","44","49","50","52","56"},// mins contains 3 1's
                {"15","23","27","29","30","39","43","45","46","51","53","54","57","58"},// mins contains 4 1's
                {"31","47","55","59"}// mins contains 5 1's
        };

        // loop from 0 to 3 which is the max number of bits can be set in hours (4 bits)
        for(int i=0; i<hour.length && i<=n; i++) {
            // this if condition is to make sure the index from minutes arr would be valid
            if(n-i<=5) {
                for(String hr: hour[i]) {
                    // if we have i 1's in hours, then we need n - i 1's in minutes,
                    // that's why the arrays were created by grouping the number of 1's bits
                    for(String min: minite[n-i])
                        result.add(hr+":"+min);
                }


            }

        }
        return result;
    }
    public List<String> readBinaryWatchDFS(int n) {
        int[] h = new int[]{1,2,4,8};
        int[] m = new int[]{1,2,4,8,16,32};

        List<Integer> hour = new ArrayList<>();
        List<Integer> min = new ArrayList<>();
        List<String> ret = new ArrayList<>();

        for (int i=0; i<n+1; i++) {
            hour = generateDigit(i, h);
            min = generateDigit(n-i, m);
            for (int h1 : hour) {
                if (h1 >= 12) continue;
                for (int m1:min) {
                    if (m1 >= 60) continue;
                    ret.add(h1 + ":"+ (m1<10?("0"+m1):m1));
                }

            }
        }
        return ret;
     }
    public List<Integer> generateDigit(int count, int[] data) {
        List<Integer> ret = new ArrayList<>();
        generateDigitHelper(ret, count, data, 0, 0);
        return ret;
    }
    public void generateDigitHelper(List<Integer>result, int count, int[] num, int sum, int pos){
        if (count == 0) {
            result.add(sum);
            return; // necessary
        }

        for (int ipos=pos; ipos<num.length; ipos++)
            generateDigitHelper(result, count-1, num, sum + num[ipos], ipos+1);// i + 1

    }
}
