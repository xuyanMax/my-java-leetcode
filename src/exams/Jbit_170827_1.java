package exams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by xu on 27/08/2017.
 */
public class Jbit_170827_1 {
    public static void main(String[] args) {
        char cc = '1';
        System.out.println((int)cc);// 49

        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {//注意while处理多个case
            String input = in.next();

            List<Integer> digitList = new ArrayList<>();
            List<Character> alp_list = new ArrayList<>();

            for (char c: input.toCharArray()) {
                if (c >= 48 && c <= 57)
                    digitList.add(c - 48); ///////??????
                else
                    alp_list.add(c);
            }
            //处理digit部分
            int[] digits = new int[digitList.size()];
            int ind = 0;
            for (int digit:digitList)
                digits[ind++] = digit;

            // 排序数组
            Arrays.sort(digits);

            //处理alphabetic部分
            char[] chars = new char[alp_list.size()];
            ind = 0;
            for(char alpha:alp_list)
                chars[ind++] = alpha;
            Arrays.sort(chars);

            //构建输出
            //链接字符与数字
            StringBuilder builder = new StringBuilder();
            for (char c:chars)
                builder.append(c);

            for (int n:digits)
                builder.append(n);
            System.out.println(builder.toString());

        }
    }
}
