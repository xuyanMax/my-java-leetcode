package greedy;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by xu on 20/08/2017.
 */
/*

630.
There are n different online courses numbered from 1 to n. Each course has some duration(course length) t
and closed on dth day. A course should be taken continuously for t days and must be finished before or on
the dth day. You will start at the 1st day.

Given n online courses represented by pairs (t,d), your task is to find the maximal number of courses that
can be taken.

Example:
Input: [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
Output: 3

Explanation:

There're totally 4 courses, but you can take 3 courses at most:

First, take the 1st course, it costs 100 days so you will finish it on the 100th day, and ready to take the
next course on the 101st day.

Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, and ready to take
the next course on the 1101st day.
Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day.
The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.

Note:
The integer 1 <= d, t, n <= 10,000.
You can't take two courses simultaneously.

*/
public class CourseSchedule_3 {
    public int scheduleCourse(int[][] courses) {

        //将courses按照end time 顺序排列
        Arrays.sort(courses, (a,b)->(a[1]-b[1]) );

        //创建一个MaxHeap， 按照前K项课程的时间长度，从大到小排序
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b)->(b-a));

        // 记录完成前K项courses的累计时间
        int start = 0;
        for (int[] course : courses) {
            start += course[0];//完成该课程的时间
            maxHeap.add(course[0]);//将该课程所需的时间添加到 maxHeap

            if (start > course[1]) {//如果完成该课程的时间超过了要求的期限
                start -= maxHeap.poll();//从maxHeap挑出耗时最长的课程，并减去该课程的时间（不再上这节课了）
            }

        }
        return maxHeap.size();
    }
}
