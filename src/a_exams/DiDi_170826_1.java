package a_exams;

import java.util.Scanner;

/**
 * Created by xu on 26/08/2017.
 */
public class DiDi_170826_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {//注意while处理多个case
            String input = in.nextLine();

            //按空格拆分
            String[] nums_str = input.split("\\s+");
            //新建整形数组
            int[] nums = new int[nums_str.length];

            //字符数组转整形数组
            for (int i = 0; i < nums.length; i++)
                nums[i] = Integer.valueOf(nums_str[i]);

            //使用dp方法，解决
            // dp[i]nums[i]结尾的数组时，最大子序列和
            int[] dp = new int[nums.length];
            dp[0] = nums[0];

            int max = dp[0];//全局变量记录最大子序列和
            for (int i = 1; i < nums.length; i++) {
                dp[i] = nums[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
                max = Math.max(max, dp[i]);
            }
            System.out.println(max);
        }
    }
}
