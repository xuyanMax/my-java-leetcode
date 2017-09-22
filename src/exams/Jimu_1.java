package exams;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by xu on 02/09/2017.
 */
/*
二、数字积木(brick．pas／c／cpp) 【问题描述】 小明有一款新式积木,每个积木上都有 一个数,一天小明突
二、数字积木(brick．pas／c／cpp)
【问题描述】
小明有一款新式积木,每个积木上都有
一个数,一天小明突发奇想,要是把所有的积
木排成一排,所形成的数目最大是多少呢?
你的任务就是读入n个数字积木,求出
所能形成的最大数.
【问题输入】
第一行是一个整数n(n≤1000),接下来
n行每行是一个正整数.
【问题输出】
所能形成的最大整数
【样例输入】
3
13
131
343
【样例输出】
34313131
【数据范围】
30％的数据,n≤10,每个数
*/
public class Jimu_1 {

    //字符串的比较
//    但是怎么使靠前的数尽可能大？显然根据样例单纯判断数字大小不可取。
//    样例中：13<131，而13要放在131前面……
//    于是，考虑两个串a和b应该如何放入答案……
//    a与b组合只有两种方式，a+b和b+a，那么就可以按a+b>b+a排序，得到的排序后序列即为最优解。
//    上代码……
    public static void main(String[] args){

//        System.out.println( "92".compareTo("122"));
//         Scanner in = new Scanner(System.in);
//         while (in.hasNext()) {//注意while处理多个case
//            int num = in.nextInt();
//             String[] input = new String[num];
//             for (int i=0; i<num; i++)
//                 input[i] = in.next();
//             Arrays.sort(input, (a, b)->((b+a).compareTo(a+b)));
//             StringBuilder builder = new StringBuilder();
//             for (String s : input)
//                 builder.append(s);
//             System.out.println(builder.toString());
//         }
        manual_solution();

    }

    public static void manual_solution(){
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {//注意while处理多个case
            int num = in.nextInt();
            String[] input = new String[num];
            for (int i=0; i<num; i++)
                input[i] = in.next();

            binaryInsertionSort(input);

            StringBuilder builder = new StringBuilder();
            for (String s : input)
                builder.append(s);
            System.out.println(builder.toString());
        }
    }
    public static void NSort(String[] input) {

        for (int i=0; i<input.length-1; i++) {// 0:n-2(需要比较的) -> n-1:1(比较次数)
            int k = i;

            for (int j=i; j<input.length; j++) {// j与i比较字符串大小
                if (input[k].compareTo(input[j]) < 0) // input[k]<input[j]
                    k = j;//更新k
            }
            //比较完后，交换最大的到i的位置
            if(k != i) {
                String tmp  = input[k];
                input[k] = input[i];
                input[i] = tmp;
            }
        }
    }
    /*
     * If the initial part of the specified range is already sorted,
     * this method can take advantage of it: the method assumes that the
     * elements from index {@code lo}, inclusive, to {@code start},
     * exclusive are already sorted.
     *
     * @param a the array in which a range is to be sorted
     * @param start the index of the first element in the range that is
     *        not already known to be sorted ({@code lo <= start <= hi})
            * @param c comparator to used for the sort
     */
    public static void binaryInsertionSort(String[] input) {
        int start = 1;
        int high = input.length;

        for (;start<high; start++) {
            String pivot = input[start];
            int left = 0;
            int right = start;

            /*invariants
            * pivot >= all in [left, mid)
            * pivot < all int [right, start)
            * */
            while (left < right) {
                int mid = left + (right - left)/2;
                // pivot > input[mid]
                if (pivot.compareTo(input[mid]) < 0 )
                    right = mid;
                else
                    left = mid+1;
            }
            int n = start - left;
            switch (n) {
                case 2: input[left + 2] = input[left + 1];
                case 1: input[left + 1] = input[left];
                default: System.arraycopy(input, left, input, left+1, n);

            }
            input[left] = pivot;
        }
    }
}
