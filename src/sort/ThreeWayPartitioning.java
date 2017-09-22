package sort;

import java.util.Arrays;

/**
 * Created by xu on 28/08/2017.
 */
public class ThreeWayPartitioning {
     public static void main(String[] args){
         ThreeWayPartitioning inst = new ThreeWayPartitioning();
         String[] strs = new String[]{"13","132","32","43","9"};

         Arrays.sort(strs, (a,b)->(b+a).compareTo(a+b));
         for (String s:strs)
             System.out.println(s);
     }

    // 3-way-partition-to-wiggly in O(n) time with O(1) space.
    // 排序后"小-中-大"次序
    // mid: nums数组的中位数
    public void algo(int[] nums, int mid){
        int i=0, j=0, n=nums.length-1;
        // It uses three indices i, j and n, maintaining the invariant that i ≤ j.
        // n holds the boundary of numbers greater than mid.
        // j is the position of number under consideration.
        // And i is the boundary for the numbers lesser than the mid.
        while (j <= n) {
            if (nums[j] == mid)
                j++;
            else if (nums[j] > mid) {
                swap(nums[j], nums[n]);
                n--;
            }
            else {
                swap(nums[i], nums[j]);
                i++;
                j++;
            }
        }
    }
    public void swap(int a, int b){
        int tmp = a;
        a = b;
        b = tmp;
    };
}
