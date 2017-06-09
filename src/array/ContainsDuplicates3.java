package array;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Created by xu on 2017/6/9.
 *
 * Given an array of integers, find out whether there are two distinct indices i and j in the array
 * such that the absolute difference between nums[i] and nums[j] is at most t
 * and the absolute difference between i and j is at most k.
 *
 */
public class ContainsDuplicates3 {
     public static void main(String[] args){

         // -429496728
         //  429496730
         System.out.println((-Integer.MIN_VALUE));//-2147483648
         System.out.println((Integer.MIN_VALUE));// -2147483648
         System.out.println((Integer.MAX_VALUE));//  2147483647
         System.out.println((-Integer.MAX_VALUE));// -2147483647

         System.out.println((5 - Integer.MIN_VALUE));//-2147483643
         System.out.println((long)(5 - Integer.MIN_VALUE)/5); // -429496728

         System.out.println(((long)5 - Integer.MIN_VALUE));// 2147483653
         System.out.println(((long) 5 - Integer.MIN_VALUE)/5); // 429496730


     }


    // using TreeSet and maintain a window of size k
    public boolean sol(int[] arr, int k, int t){
        TreeSet<Long> redBlackTree = new TreeSet<>();
        int i=0;

        for (; i<arr.length; i++) {

            // search for the range
            Long floor = redBlackTree.floor((long)arr[i] + t);// floor <= arr[i] + t
            Long ceil = redBlackTree.ceiling((long)arr[i] - t); //ceil >= arr[i] - t;

            if ((floor != null && floor >= arr[i]) || (ceil != null && ceil <= arr[i]) )
                return true;

            // add to the tree
            redBlackTree.add((long)arr[i]);
            // maintain a window of size k
            if (i >= k)
                redBlackTree.remove((long)arr[i - k]);
        }

        return false;
    }

    // using buckets
    // we map a range of values into a bucket, seen as duplicates
    // reference https://discuss.leetcode.com/topic/15199/ac-o-n-solution-in-java-using-buckets-with-explanation
    public boolean sol2(int[] arr, int t, int k) {

        if (k <1 || t <0) return false;
        Map<Long, Long> map = new HashMap<>();
        for (int i=0; i<arr.length; i++) {
            long remapped = (long)arr[i] - Integer.MIN_VALUE;
            long bucket = remapped / ((long)t + 1);

            if (map.containsKey(bucket)
                    ||(map.containsKey(bucket - 1) && (remapped - map.get(bucket-1)) <= t)
                    ||(map.containsKey(bucket + 1) && (map.get(bucket+1) - remapped) <= t))
                return true;

            if (map.entrySet().size() >= k) {
                // cast (long)的位置很关键
//                long lastBuck =  (long)(arr[i - k] - Integer.MIN_VALUE) / ((long)t + 1); // wrong
                long lastBuck =  ((long)arr[i - k] - Integer.MIN_VALUE) / ((long)t + 1);
                map.remove(lastBuck);
            }
            map.put(bucket, remapped);
        }
        return false;
    }
    public boolean sol2_change_some_sequence(int[] arr, int t, int k) {

        if (k <1 || t <0) return false;
        Map<Long, Long> map = new HashMap<>();
        for (int i=0; i<arr.length; i++) {
            long remapped = (long)arr[i] - Integer.MIN_VALUE;
            long bucket = remapped / ((long)t + 1);

            //difference
            if (map.entrySet().size() > k) {
                // cast (long)的位置很关键
                // difference
                long lastBuck =  ((long)arr[i - k - 1] - Integer.MIN_VALUE) / ((long)t + 1);
                map.remove(lastBuck);
            }

            if (map.containsKey(bucket)
                    ||(map.containsKey(bucket - 1) && (remapped - map.get(bucket-1)) <= t)
                    ||(map.containsKey(bucket + 1) && (map.get(bucket+1) - remapped) <= t))
                return true;

            map.put(bucket, remapped);
        }
        return false;
    }
}
