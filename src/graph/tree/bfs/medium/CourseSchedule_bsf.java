package graph.tree.bfs.medium;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by xu on 19/08/2017.
 * 207. Course Schedule
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * <p>
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
 * which is expressed as a pair: [0,1]
 * <p>
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish
 * all courses?
 * <p>
 * For example:
 * <p>
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
 * <p>
 * 2, [[1,0],[0,1]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to
 * take course 0 you should also have finished course 1. So it is impossible.
 * <p>
 * Hints:
 * <p>
 * This problem is EQUIVALENT to finding if a cycle exists in a directed graph.
 * - If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
 * - Topological sort could also be done via BFS.
 */
public class CourseSchedule_bsf {

    //使用"邻接表"记录边的关系
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[][] mat = new int[numCourses][numCourses];
        int[] numIndegree = new int[numCourses];

        for (int[] edge : prerequisites) {
            int to = edge[0];
            int from = edge[1];

            if (mat[from][to] == 0)
                numIndegree[to]++;
            mat[from][to] = 1;
        }
        //将所有入度为0 的节点加入queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numIndegree.length; i++)
            if (numIndegree[i] == 0)
                queue.add(i);

        //  统计按照拓扑排序输出的节点的个数
        // 如果与总的节点个数相同，表明该拓扑图无环，否则有环
        int count = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            count++;

            for (int i = 0; i < numCourses; i++) {
                if (mat[curr][i] != 0) {
                    mat[curr][i] = 0; //将所有curr->指向的节点的"边"取消
                    numIndegree[i]--; // 将被指向的节点的入度减少1

                    if (numIndegree[i] == 0)
                        queue.add(i);
                }
            }
        }
        return count == numCourses;

    }

    //使用链表表示一个节点所--->的相邻节点
    //运行速度优于邻接表
    public boolean canFinish_2(int numCourses, int[][] prerequisites) {
        List<Integer>[] edge_lists = new LinkedList[numCourses];

        // 初始化每一个节点的链表

        for (int i = 0; i < numCourses; i++)
            edge_lists[i] = new LinkedList<>();

        int[] numIndegree = new int[numCourses];

        for (int[] edge : prerequisites) {
            int to = edge[0];
            int from = edge[1];
            edge_lists[from].add(to);
            numIndegree[to]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (numIndegree[i] == 0)
                queue.add(i);
        }

        int count = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            count++;

            for (int i = 0; i < edge_lists[curr].size(); i++) {
                int to = edge_lists[curr].get(i);
                if (--numIndegree[to] == 0)
                    queue.add(to);
            }

        }
        return count == numCourses;
    }

    // CourseSchedule2
    //  在上面的基础上输出该图的拓扑排序
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
