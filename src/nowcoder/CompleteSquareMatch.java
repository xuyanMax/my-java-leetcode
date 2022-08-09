package nowcoder;
import java.util.Scanner;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/fe8d6a1b88af4ba6b4dbb10972059040?orderByHotValue=1&questionTypes=000100&mutiTagIds=139&page=1&onlyReference=false
 * 来源：牛客网
 * <p>
 * 合法的括号匹配序列被定义为:
 * 1. 空串""是合法的括号序列
 * 2. 如果"X"和"Y"是合法的序列,那么"XY"也是一个合法的括号序列
 * 3. 如果"X"是一个合法的序列,那么"[X]"也是一个合法的括号序列
 * 4. 每个合法的括号序列都可以由上面的规则生成
 * 例如"", "[]", "[][][]", "[[][]]", "[[[[]]]]"都是合法的。
 * 牛牛现在给出一个括号序列s,牛牛允许你执行的操作是:在s的开始和结尾处添加一定数量的左括号('[')或者右括号(']')使其变为一个合法的括号匹配序列。牛牛希望你能求出添加最少的括号之后的合法的括号匹配序列是什么。
 * 输入描述:
 * 输入包括一个字符串s,s的长度length(1 ≤ length ≤ 50),s中只包含'['和']'。
 * <p>
 * <p>
 * 输出描述:
 * 输出一个字符串,表示括号完全匹配的序列。
 * 示例1
 * 输入
 * ][
 * 输出
 * [][]
 */
public class CompleteSquareMatch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        char[] chars = s.toCharArray();
        /** 向前面添加[*/
        StringBuilder preAdder = new StringBuilder();
        int cnt = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '[')
                cnt++;
            else if (chars[i] == ']')
                cnt--;
            if (cnt < 0) {
                preAdder.append("[");
                cnt = 0;
            }
        }
        /** 向最后添加]*/
        while (cnt >= 1) {
            s = s + "]";
            cnt--;
        }
        System.out.println(preAdder.append(s).toString());
    }
}
