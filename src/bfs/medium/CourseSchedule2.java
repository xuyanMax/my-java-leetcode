package bfs.medium;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by xu on 20/08/2017.
 * 210. Course Schedule II
 * Given the total number of courses and a list of prerequisite pairs,
 * return the ordering of courses you should take to finish ALL courses.
 *
 * There may be multiple correct orders, you just need to return one of them.
 * If it is impossible to finish all courses, return an empty array.
 */
public class CourseSchedule2 {
    // 在CourseSchedule_1的基础上输出该图的拓扑排序
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] ret = new int[numCourses];
        if (numCourses == 0)
            return ret;

        // sanity check
        if (prerequisites.length == 0) {
            for (int i=0; i<numCourses; i++)
                ret[i] = i;
        }

        List<Integer>[] relations = new LinkedList[numCourses];

        for (int i=0; i<numCourses; i++)
            relations[i] = new LinkedList<>();

        int[] numIndegree = new int[numCourses];

        for (int[] edge : prerequisites) {
            int to = edge[0];
            int from = edge[1];
            relations[from].add(to);
            numIndegree[to]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i=0; i<numCourses; i++) {
            if (numIndegree[i] == 0)
                queue.add(i);
        }
        int count = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            //与courseSchedule1不同的一行代码
            ret[count++] = curr;

            for (int i=0; i<relations[curr].size(); i++) {
                int to = relations[curr].get(i);
                numIndegree[to]--;
                if (numIndegree[to] == 0)
                    queue.add(to);
            }
        }

        return count == numCourses ? ret : new int[]{};
    }

}
