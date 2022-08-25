package a_OA.nowcoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/17ba5b5df1fc49ca8d6cf8ea407b1972?orderByHotValue=1&questionTypes=000100&mutiTagIds=139&page=1&onlyReference=false
 * 来源：牛客网
 * <p>
 * 对于字符串x和y, 如果擦除x中的某些字母(有可能全擦掉或者都不擦)能够得到y,我们就称y是x的子序列。例如."ncd"是"a_OA.nowcoder"的子序列,而"xt"不是。
 * 现在对于给定的一个字符串s,请计算出字典序最大的s的子序列。
 * 输入描述:
 * 输入包括一行,一个字符串s,字符串s长度length(1 ≤ length ≤ 50).
 * s中每个字符都是小写字母
 * <p>
 * <p>
 * 输出描述:
 * 输出一个字符串,即字典序最大的s的子序列。
 * 示例1
 * 输入
 * test
 * 输出
 * tt
 */
public class LongestSubsequence {
    /**
     * 既然要找最大的字典序，肯定要找开头最大。贪心算法，从后往前找，如果前者比后者大，则保留，否则就删除。
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        char[] res = str.toCharArray();
        List<Character> arr = new ArrayList<>();
        arr.add(res[res.length - 1]);
        for (int i = res.length - 2; i >= 0; i--) {
            if (arr.get(arr.size() - 1) <= res[i])
                arr.add(res[i]);
        }
        StringBuilder builder = new StringBuilder();

        for (Character character : arr) {
            builder.append(character);
        }
        System.out.println(builder.reverse().toString());

    }
}
