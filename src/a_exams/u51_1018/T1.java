package a_exams.u51_1018;

import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by xu on 18/10/2017.
 */
public class T1 {
    // 33% ac MLE
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int max = 0;
        int maxNum = 0;
        for (int i = 0; i < n; i++) {
            int num = in.nextInt();
            if (map.containsKey(num))
                map.put(num, map.get(num) + 1);
            else
                map.put(num, 1);
            if (max < map.get(num)) {
                max = map.get(num);
                maxNum = num;
            }
        }
        System.out.println(map.get(maxNum));

    }

    // 0% ac MLE
    public static void sol2() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n * 10];
        int maxNum = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            int num = in.nextInt();
            nums[num]++;
            if (nums[num] > max) {
                max = nums[num];
            }
            if (nums[num] == max && num < maxNum)
                maxNum = num;
        }
        System.out.println(nums);


    }
}
