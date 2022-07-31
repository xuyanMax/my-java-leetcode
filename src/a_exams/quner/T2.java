package a_exams.quner;

import java.util.Scanner;

/**
 * Created by xu on 11/10/2017.
 */
public class T2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String str = in.nextLine();
        String[] str_split = str.split(" ");

        String[] str_split_rev = reverse(str_split);

        //初始化返回值 count += 所有空格匹配
        String str_rev = String.join(" ", str_split_rev);
        int count = LCSub(str.toCharArray(), str_rev.toCharArray());

        System.out.println(count);


    }

    public static String[] reverse(String[] strings) {

        String[] output = new String[strings.length];
        int k = 0;
        for (int i = strings.length - 1; i >= 0; i--)
            output[k++] = strings[i];
        return output;
    }

    public static int LCSub(char[] str1, char[] str2) {
        int[][] dp = new int[str1.length + 1][str2.length + 1];

        for (int i = 0; i <= str1.length; i++)
            dp[i][0] = 0;
        for (int j = 0; j <= str2.length; j++)
            dp[0][j] = 0;

        for (int i = 1; i <= str1.length; i++)
            for (int j = 1; j <= str2.length; j++) {
                if (str1[i - 1] == str2[j - 1])
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        return dp[str1.length][str2.length];
    }

}
