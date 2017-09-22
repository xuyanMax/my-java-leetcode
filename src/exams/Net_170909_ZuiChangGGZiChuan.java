package exams;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by xu on 13/09/2017.
 */
/*
一个合法的括号匹配序列被定义为:
1. 空串""是合法的括号序列
2. 如果"X"和"Y"是合法的序列,那么"XY"也是一个合法的括号序列
3. 如果"X"是一个合法的序列,那么"(X)"也是一个合法的括号序列
4. 每个合法的括号序列都可以由上面的规则生成
例如"", "()", "()()()", "(()())", "(((()))"都是合法的。
从一个字符串S中移除零个或者多个字符得到的序列称为S的子序列。
例如"abcde"的子序列有"abe","","abcde"等。
定义LCS(S,T)为字符串S和字符串T最长公共子序列的长度,即一个最长的序列W既是S的子序列也是T的子序列的长度。
小易给出一个合法的括号匹配序列s,小易希望你能找出具有以下特征的括号序列t:
1、t跟s不同,但是长度相同
2、t也是一个合法的括号匹配序列
3、LCS(s, t)是满足上述两个条件的t中最大的
因为这样的t可能存在多个,小易需要你计算出满足条件的t有多少个。

如样例所示: s = "(())()",跟字符串s长度相同的合法括号匹配序列有:
"()(())", "((()))", "()()()", "(()())",其中LCS( "(())()", "()(())" )为4,其他三个都为5,所以输出3.

输出描述:
输出一个正整数,满足条件的t的个数。

输入例子1:
(())()

输出例子1:
3

*/
public class Net_170909_ZuiChangGGZiChuan {
    /*
    * 当修改一个字符时，LCS最大
    * 遍历每一个字符，并插入到任意一个位置，判断合法，丢到Set里，去重，累计
    *
    * 如何插入到任意一个位置：从字符串先拿掉该字符，然后一次插入到字符串从开头到末尾的所有位置。
    * 如何判断合法：遍历字符串并，计数"(", ")"，但凡出现")"数量大于"("，则返回非法
    *
    * */
     public static void main(String[] args){
         Scanner in = new Scanner(System.in);
         String s;
         Set<String> set = new HashSet<>();
         while (in.hasNext()) {
             s = in.next();
             StringBuilder builder;

             for(int i=0; i<s.length(); i++){
                 builder = new StringBuilder(s);
                 builder.deleteCharAt(i);

                 //插入 [0 - n-1]
                 for (int j=0; j<s.length(); j++) {
                     builder.insert(j, s.charAt(i));
                     if (isLegal(builder.toString())) {
                         set.add(builder.toString());
                     }
                     builder.deleteCharAt(j);
                 }

             }
             // set中包含s本身，需要减去
             System.out.println(set.size() - 1);
         }
     }
     public static boolean isLegal(String s){
         int left = 0, right = 0;
         for (char c:s.toCharArray()) {
             if (c == '(')
                 left++;
             else if (c == ')')
                 right++;
             if (right > left)
                 return false;
         }
         return true;
     }
}
