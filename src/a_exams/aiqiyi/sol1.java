package a_exams.aiqiyi;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by xu on 14/10/2017.
 */
public class sol1 {
     public static void main(String[] args){
         Scanner in = new Scanner(System.in);
         String str = in.next();
         int k = in.nextInt();

         int[] count = new int[26];
         for (int i=0; i<str.length(); i++)
             count[str.charAt(i) - 'a']++;

         for (int i=0; i<k; i++) {
             Arrays.sort(count);
             if (count[count.length-1] > 0)
                 count[count.length-1]--;
         }
         int sum = 0;
         for (int i=0; i<count.length; i++)
             sum += count[i]*count[i];

     }
}
