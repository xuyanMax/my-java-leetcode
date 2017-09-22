package math;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by xu on 02/09/2017.
 */
/*
一道C++编程题!
距离排序
[问题描述]
给出三维空间中的n个点（不超过10个）,求出n个点两两之间的距离,并按距离由大到小依次输出两个点的坐标及它们之间的距离.

输入：
输入包括两行,第一行包含一个整数n表示点的个数,第二行包含每个点的坐标(坐标都是整数).点的坐标的范围是0到100,
输入数据中不存在坐标相同的点.

输出：
对于大小为n的输入数据,输出n*(n-1)/2行格式如下的距离信息：
(x1,y1,z1)-(x2,y2,z2)=距离
其中距离保留到数点后面2位.
采用冒泡排序,快速排序,选择排序中的两种排序算法实现排序功能.

*/
public class PointsDistanceSort {
     public static void main(String[] args){
         Scanner in = new Scanner(System.in);
         int n, k;

         while (in.hasNextInt()) {
            n = in.nextInt();
            k = n*(n-1)/2;
            Point[] points = new Point[n];
            Distance[] distances = new Distance[k];

            for (int i=0; i<n; i++) {
                points[i].x = in.nextInt();
                points[i].y = in.nextInt();
                points[i].z = in.nextInt();
            }
            // 更新distance
             int ind = 0;
            for (int i=0; i<n-1; i++) {
                for (int j=i+1; j<n; j++){
                    distances[ind].a = points[i];
                    distances[ind].b = points[j];
                    distances[ind++].dist = getSquare(points[i], points[j]);
                }
            }
            // 排序
             Arrays.sort(distances, new Comparator<Distance>() {
                 @Override
                 public int compare(Distance o1, Distance o2) {
                     return o1.dist - o2.dist;
                 }
             });
             for (int i=0; i<n; i++)
                System.out.println(distances[i].dist + " " + distances[i].a + " " + distances[i].b);

         }

     }
     private static int getSquare(Point a, Point b) {
         return (int)Math.sqrt((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y) + (a.z-b.z)*(a.z-b.z) );
     }
     static class Point{
         int x;
         int y;
         int z;

         public Point(int x, int y, int z) {
             this.x = x;
             this.y = y;
             this.z = z;
         }
     }
     static class Distance {
         Point a, b;
         int dist;

         public Distance(Point a, Point b, int dist) {
             this.a = a;
             this.b = b;
             this.dist = dist;
         }
     }
}
