package exams.Net;

import java.util.Scanner;

/**
 * Created by xu on 28/09/2017.
 */
/*
小易有一个长度为n的整数序列,a_1,...,a_n。然后考虑在一个空序列b上进行n次以下操作:
1、将a_i放入b序列的末尾
2、逆置b序列
小易需要你计算输出操作n次之后的b序列。
输入描述:
输入包括两行,第一行包括一个整数n(2 ≤ n ≤ 2*10^5),即序列的长度。
第二行包括n个整数a_i(1 ≤ a_i ≤ 10^9),即序列a中的每个整数,以空格分割。


输出描述:
在一行中输出操作n次之后的b序列,以空格分割,行末无空格。

输入例子1:
4
1 2 3 4
4
输出例子1:
4 2 1 3

*/
public class CaoZuoXuLie {
     public static void main(String[] args){
         Scanner in = new Scanner(System.in);
         while (in.hasNextInt()) {
             int n = in.nextInt();
             int[] nums = new int[n];
             for (int i=0; i<n; i++)
                 nums[i] = in.nextInt();
             if (n == 1) {
                 System.out.println(nums[0]);
                 return;
             }

                 for (int i=n-1; i>=0; i-=2)
                     System.out.print(nums[i] + " ");

                 for (int i=n%2; i<n-2; i+=2) //判断奇偶性，奇数，从1开始，偶是从0开始
                     System.out.print(nums[i] + " ");

                 System.out.println(nums[n-2]);


         }
     }
}
/*
             int n = in.nextInt();
             in.nextLine();
             String str = in.nextLine();
             String[] strs = str.split(" ");


             StringBuilder builder = new StringBuilder();
             for (int i=0; i<strs.length; i++) {
                 builder.append(strs[i]).reverse();
             }
             str = builder.toString();
             builder = new StringBuilder();
             for (char c : str.toCharArray())
                 builder.append(c + " ");
             builder.deleteCharAt(builder.length()-1);
             System.out.println(builder.toString());

*/
