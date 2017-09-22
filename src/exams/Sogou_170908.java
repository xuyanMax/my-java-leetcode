package exams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by xu on 08/09/2017.
 */
/*
圆周上两点间的距离
时间限制：C/C++语言 1000MS；其他语言 3000MS
内存限制：C/C++语言 30720KB；其他语言 555008KB
题目描述：
定义圆周上两点的距离s为这两点之间的劣弧对应的圆心角度数(0<=s<=180)，现输入圆周上的n个点（n>=2），
以角度a表示其位置(0<=a<360)，输入按a从小到大排序。求最远的一对点之间的距离。
输入
第一行为点个数n，后跟n行，每行一个双精度浮点数，表示点的角度（小数点后保留8位），例如输入样例中为4个点的输入：
输出
输出最远的一对点之间的距离（双精度浮点数，小数点后保留8位）和'\n'换行符。例如输入样例中，10.00000000与183.00000000
两个点之间的距离为173.00000000，大于10.00000000与198.0000000之间的距离172.00000000，所以应输出：
173.00000000

样例输入
4
10.00000000
180.00000000
183.00000000
198.00000000
样例输出
173.00000000

Hint
注意事项：
1.程序性能要足够快，否则可能无法通过一些大型测试数据；
2.如果使用java语言，可以考虑使用BufferedReader从标准输入读取输入数据，Scanner读取一些比较大的输入数据会发生超时。

*/
public class Sogou_170908 {
     public static void main(String[] args){

         BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

         try {
             String n_str = null;
             n_str = reader.readLine();
             int n = Integer.valueOf(n_str);
             double[] data = new double[n];
             for (int i=0; i<n; i++) {
                 data[i] = Double.parseDouble(reader.readLine());

             }
             Arrays.sort(data);

             double diff = Double.MIN_VALUE;
             for (int i=0; i<n-1; i++){
                 for (int j=i; j<n;j++) {
                     double d = data[j]-data[i];
                     d = d>180.0?360.0-d:d;
                     diff = Math.max(d, diff);
                 }
             }
             // important!!!!
             System.out.println(String.format("%.8f", diff));
         } catch (IOException e) {
             e.printStackTrace();
         }



     }
}
