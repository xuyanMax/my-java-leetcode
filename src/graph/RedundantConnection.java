package graph;

/**
 * 684. Redundant Connection
 * In this problem, a graph.tree is an undirected graph that is connected and has no cycles.
 * <p>
 * The given input is a graph that started as a graph.tree with N nodes (with distinct values 1, 2, ..., N),
 * with one additional edge added. The added edge has two different vertices chosen from 1 to N,
 * and was not an edge that already existed.
 * <p>
 * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v,
 * that represents an undirected edge connecting nodes u and v.
 * <p>
 * Return an edge that can be removed so that the resulting graph is a graph.tree of N nodes.
 * If there are multiple answers, return the answer that occurs last in the given 2D-array.
 * The answer edge [u, v] should be in the same format, with u < v.
 * <p>
 * Example 1:
 * Input: [[1,2], [1,3], [2,3]]
 * Output: [2,3]
 * Explanation: The given undirected graph will be like this:
 * 1
 * / \
 * 2 - 3
 * Example 2:
 * Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * Output: [1,4]
 * Explanation: The given undirected graph will be like this:
 * 5 - 1 - 2
 *      |   |
 *      4 - 3
 * Note:
 * The size of the input 2D-array will be between 3 and 1000.
 * Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
 * <p>
 * Update (2017-09-26):
 * We have overhauled the problem description + test cases and specified clearly
 * the graph is an undirected graph. For the directed graph follow up
 * please see Redundant Connection II). We apologize for any inconvenience caused.
 */

public class RedundantConnection {
    // union-find
    public int[] findRedundantConnection(int[][] edges) {
        int[] parents = new int[2001];

        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }

        for (int[] edge : edges) {
            int from = edge[0], to = edge[1];
            if (find(parents, from) == find(parents, to))
                return edge;
            else
                parents[find(parents, from)] = find(parents, to);

        }
        return new int[2];
    }

    public int find(int[] parents, int f) {
        if (parents[f] != f) {
            parents[f] = find(parents, parents[f]);
        }
        return parents[f];
    }
}
