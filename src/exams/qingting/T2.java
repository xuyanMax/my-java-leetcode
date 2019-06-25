package exams.qingting;

import java.util.Scanner;

/**
 * Created by xu on 12/10/2017.
 */
public class T2 {
    //类似 first positive Integer
    //有一个长度为n的正整数数组，其中的元素均满足大于等于0并且小于等于n-1。
    //要求输出o-n-1中没有在上述数组中出现的所有数字
    // 如输入[1,2,2,4,1]，则输出为0
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];

        for (int i = 0; i < n; i++)
            nums[i] = in.nextInt();

        for (int i = 0; i < n; i++) {
            while (nums[i] != nums[nums[i]]) {
                swap(nums, i, nums[i]);
                // 1,2 ->2,1
                // 2,1,2
                //
                // 2,1,2
                //
                //2,1,2,4->2,1,2,1,4
                // 2,1,2,1
                //
                // 2,1,2,1,4
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i])
                System.out.println(i);
        }

    }

    public static void swap(int[] nums, int i, int j) {

    }
}
