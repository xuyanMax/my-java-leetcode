package dfs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by xu on 20/08/2017.
 */
public class CourseSchedule_2 {
    // 在CourseSchedule_1的基础上输出该图的拓扑排序
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] ret = new int[numCourses];
        if (numCourses == 0)
            return ret;
        if (prerequisites.length == 0) {
            for (int i=0; i<numCourses; i++)
                ret[i] = i;
        }

        List<Integer>[] edge_lists = new LinkedList[numCourses];

        // 初始化每一个节点的链表
        for (int i=0; i<numCourses; i++)
            edge_lists[i] = new LinkedList<>();

        int[] numIndegree = new int[numCourses];

        for (int[] edge : prerequisites) {
            int to = edge[0];
            int from = edge[1];
            edge_lists[from].add(to);
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
            ret[count++] = curr;
            for (int i=0; i<edge_lists[curr].size(); i++) {
                int to = edge_lists[curr].get(i);
                numIndegree[to]--;
                if (numIndegree[to] == 0)
                    queue.add(to);
            }
        }

        return count == numCourses ? ret : new int[]{};
    }

}
