package a_exams.Net;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by xu on 28/09/2017.
 * <p>
 * 小易有一些彩色的砖块。每种颜色由一个大写字母表示。各个颜色砖块看起来都完全一样。
 * 现在有一个给定的字符串s,s中每个字符代表小易的某个砖块的颜色。小易想把他所有的砖块排成一行。
 * 如果最多存在一对不同颜色的相邻砖块,那么这行砖块就很漂亮的。
 * 请你帮助小易计算有多少种方式将他所有砖块排成漂亮的一行。
 * (如果两种方式所对应的砖块颜色序列是相同的,那么认为这两种方式是一样的。)
 * <p>
 * 例如: s = "ABAB",那么小易有六种排列的结果:
 * "AABB","ABAB","ABBA","BAAB","BABA","BBAA"
 * 其中只有"AABB"和"BBAA"满足最多只有一对不同颜色的相邻砖块。
 */
public class CaiSeZiFu {//智力题.....

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String str = in.nextLine();
            Set<Character> set = new HashSet<>();
            for (int i = 0; i < str.length(); i++) {
                set.add(str.charAt(i));
                if (set.size() > 2) {
                    System.out.println(0);
                    return;
                }
            }
            //如果有两个字符旧返回2，如果有一个旧返回1
            System.out.println(set.size());
        }


    }

}
