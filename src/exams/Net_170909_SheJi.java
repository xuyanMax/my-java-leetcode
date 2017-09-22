package exams;

import java.util.Scanner;

/**
 * Created by xu on 18/09/2017.
 */
public class Net_170909_SheJi {
/*
链接：https://www.nowcoder.com/questionTerminal/d3f26db0325444078717cc802e0056d8
来源：牛客网

四个for循环。每个for循环选取一个点（判断该点不同于前面的点），前三个点要求不共线。
    前两个点A,B通过第一条直线；
        第三个点C通过另一条直线；
            第四个for循环，对于剩下的n-3个点，判断是否落在这两条直线上。如果有AD与AB平行，则落在第一条直线上；如果有CD与AB垂直，则落在第二条直线上。
            第四个for循环结束，可以知道这两条直线能穿过最多几个点，每次更新最大值。
所有循环结束，输出最终的最大值即可。
*/
     public static void main(String[] args){
         Scanner in = new Scanner(System.in);
         while (in.hasNextInt()) {
             int n = in.nextInt();
             int[] p_x = new int[n];
             int[] p_y = new int[n];

             for (int i=0; i<n; i++)
                 p_x[i] = in.nextInt();
             for (int i=0; i<n; i++)
                 p_y[i] = in.nextInt();
             int max = 0;
             // b=a+1
             //     c=b+1
             //         d=c+1
             //这样得不到结果，因为每个点需要尝试四个点中的每一个位置！
             for (int a=0; a<n; a++){
                 for (int b=0; b<n; b++){
                     if (a !=b) {
                         int[] a_ = new int[]{p_x[a], p_y[a]};
                         int[] b_ = new int[]{p_x[b], p_y[b]};

                         for (int c = 0; c < n; c++) {
                             if (c != a && c != b) {
                                 int[] c_ = new int[]{p_x[c], p_y[c]};
                                     int count = 3;
                                     for (int d = 0; d < n; d++) {
                                        if (d!=a && d!=b && d!=c) {
                                            int[] d_ = new int[]{p_x[d], p_y[d]};
                                            if (isParallel(a_, b_, c_) || isVertical(a_, b_, c_, d_))
                                                count++;
                                        }
                                     }
                                 max = Math.max(max, count);
                             }
                         }
                     }
                 }
             }
             System.out.println(max);


         }
     }
     public static boolean isVertical(int[] a, int[] b, int[] c){
        // by-ay/bx-ax * cy-ay/cx-ax = -1
         return (b[1]-a[1])*(c[1]-a[1])+ ((b[0]-a[0]))*(c[0]-a[0]) == 0;
     }
    public static boolean isParallel(int[] a, int[] b, int[] d){
        // by-ay/bx-ax = dy-ay/dx-ax
        return (b[1]-a[1])*(d[0]-a[0]) == (d[1]-a[1])*(b[0]-a[0]);
    }
     public static boolean isVertical(int[] a, int[] b, int[] c, int[] d){
        //
         return (b[1]-a[1])*(d[1]-c[1]) + (b[0]-a[0])*(d[0]-c[0]) == 0;
     }
}
