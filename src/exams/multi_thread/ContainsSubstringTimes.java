package exams.multi_thread;

/**
 * Created by xu on 19/09/2017.
 */
public class ContainsSubstringTimes {
     public static void main(String[] args){
         System.out.println(containsTimes("asu51daskjfu51sklfdau51", "u51_1018"));
     }
     public static int containsTimes(String str, String sub) {
         int fromIndex=0;
         int count = 0;
         int index = 0;
         while ((index = str.indexOf(sub, fromIndex)) != -1) {
             count++;
             fromIndex = index + sub.length();
         }
         return count;
     }
}
