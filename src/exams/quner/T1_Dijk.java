package exams.quner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by xu on 11/10/2017.
 */
public class T1_Dijk {
     public static void main(String[] args){
         Scanner in = new Scanner(System.in);
         int n = in.nextInt();
         int m = in.nextInt();

         Map<String, ArrayList<String>> map = new HashMap<>();

         String depart = in.next();
         String destination = in.next();

         for (int i=0; i<m; i++) {
             String from = in.next();
             String to = in.next();
             if (!map.containsKey(from)){
                 map.put(from, new ArrayList<String>());
             }
             map.get(from).add(to);
         }



     }
}
