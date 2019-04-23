package bfs.medium;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 279. Perfect Squares
 * <p>
 * Given a positive integer n, find the least number of
 * perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 * <p>
 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4;
 * given n = 13, return 2 because 13 = 4 + 9.
 * <p>
 * Credits:
 * Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.
 */
public class PerfectSquares {
    public static int numSquares(int num) {

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(0);
        visited.add(0);
        int depth = 0;
// Java BFS implementation: Start from node 0 in queue,
// and keep pushing in perfect square number + curr value,
// once we reach number n, we found the solution.
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            while (size > 0) {
                int u = queue.poll();
                size--;
                //neighbors
                for (int i = 1; i * i <= num; i++) {
                    int v = i * i + u;
                    if (v == num) {
                        return depth;
                    }
                    if (v > num) {
                        break;
                    }
                    // queue.add(v);
                    if (visited.add(v)) {
                        queue.add(v);
                    }
                }
            }

        }
        return depth;
    }
}
