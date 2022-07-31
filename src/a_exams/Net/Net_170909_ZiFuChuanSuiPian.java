package a_exams.Net;

import java.util.Scanner;

/**
 * Created by xu on 13/09/2017.
 * <p>
 * 一个由小写字母组成的字符串可以看成一些同一字母的最大碎片组成的。例如,"aaabbaaac"是由下面碎片组成的:
 * 'aaa','bb','c'。牛牛现在给定一个字符串,请你帮助计算这个字符串的所有碎片的平均长度是多少。
 * <p>
 * 输入描述:
 * 输入包括一个字符串s,字符串s的长度length(1 ≤ length ≤ 50),s只含小写字母('a'-'z')
 * <p>
 * <p>
 * 输出描述:
 * 输出一个整数,表示所有碎片的平均长度,四舍五入保留两位小数。
 * <p>
 * 如样例所示: s = "aaabbaaac"
 * 所有碎片的平均长度 = (3 + 2 + 3 + 1) / 4 = 2.25
 * <p>
 * 输入例子1:
 * aaabbaaac
 * <p>
 * 输出例子1:
 * 2.25
 */
public class Net_170909_ZiFuChuanSuiPian {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s;
        while (in.hasNext()) {
            s = in.next();
            char[] chars = s.toCharArray();
            char c = chars[0];
            int numSeg = 1;
            for (int i = 1; i < chars.length; i++) {
                if (chars[i] != c) {
                    numSeg++;
                    c = chars[i];
                }
            }
            double ret = (double) chars.length / numSeg;
            System.out.println(String.format("%.2f", ret));
        }
    }
}
