package a_exams;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by xu on 17/09/2017.
 */
public class Souhu_170917_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {//注意while处理多个case
            int n = in.nextInt();
            int sum = 0;
            int[] nums = new int[n];

            for (int i = 0; i < n; i++) {
                nums[i] = in.nextInt();
                sum += nums[i];
            }
            int left = 0, right = n - 1;

            while (left < right) {
                if (nums[left] == nums[right]) {
                    left++;
                    right--;
                } else if (nums[left] > nums[right]) {
                    sum += nums[right];
                    right--;
                } else {
                    sum += nums[left];
                    left++;
                }
            }

            System.out.println(sum);
        }
    }

    public static void pass_30_p() {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {//注意while处理多个case
            int n = in.nextInt();
            int sum = 0;
            int[] nums = new int[n];

            for (int i = 0; i < n; i++) {
                nums[i] = in.nextInt();
                sum += nums[i];
            }
            int left = 0, right = n - 1;

            while (left < right) {
                if (nums[left] == nums[right]) {
                    left++;
                    right--;
                } else if (nums[left] > nums[right]) {
                    sum += nums[right];
                    right--;
                } else {
                    sum += nums[left];
                    left++;
                }
            }

            System.out.println(sum);
        }

    }

    public static void pass_10_p() {
        Scanner in = new Scanner(System.in);
        Map<Integer, Integer> map;
        while (in.hasNextInt()) {//注意while处理多个case
            int n = in.nextInt();
            int[] nums = new int[n];
            map = new HashMap<>();

            int sum = 0;
            for (int i = 0; i < n; i++) {
                nums[i] = in.nextInt();
                sum += nums[i];
                if (map.containsKey(nums[i]))
                    map.put(nums[i], map.get(nums[i]) + 1);
                else
                    map.put(nums[i], 1);
//                map.put(nums[i],  map.getOrDefault(nums[i], 0) + 1);
            }

            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() % 2 != 0)
                    sum += entry.getValue();
            }
            System.out.println(sum);
        }
    }
}
