package graph.hard;
//directed
/*Example 1:
    Input: [[1,2], [1,3], [2,3]]
    Output: [2,3]
    Explanation: The given DIRECTED graph will be like this:
    1
    / \
    v   v
    2-->3

    Example 2:
    Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
    Output: [4,1]
    Explanation: The given directed graph will be like this:
    5 <- 1 -> 2
        ^    |
        |    v
        4 <- 3

    Note:
    The size of the input 2D-array will be between 3 and 1000.
    Every integer represented in the 2D-array will be between 1 and N,
    where N is the size of the input array.
*/
public class RedundantConnection2 {
    /*This problem is very similar to “Redundant Connection”.
    But the description on the parent/child relationships is much better clarified.

    There are two cases for the tree structure to be invalid.
            1) A node having two parents;
    including corner case: e.g. [[4,2],[1,5],[5,2],[5,3],[2,4]]
            2) A circle exists
    If we can remove exactly 1 edge to achieve the tree structure,
    a single node can have at most two parents. So my solution works in two steps.

1) Check whether there is a node having two parents.
    If so, store them as candidates A and B, and set the second edge invalid.
2) Perform normal union find.
    If the tree is now valid
        simply return candidate B
    else if candidates not existing
        we find a circle, return current edge;
    else
        remove candidate A instead of B.*/
    public int[] findRedundantDirectedConnection(int[][] edges) {

        int[] can1 = {-1, -1};
        int[] can2 = {-1, -1};
        int[] parent = new int[edges.length + 1];
        // two parents checking
        for (int i = 0; i < edges.length; i++) {
            if (parent[edges[i][1]] == 0) {
                parent[edges[i][1]] = edges[i][0];
            } else {
                can1 = new int[] {parent[edges[i][1]], edges[i][1]};
                can2 = new int[] {edges[i][0], edges[i][1]};
                edges[i][1] = 0;// INVALID the 2nd edge causing the two parents issue
            }
        }
        // circle checking
        for (int i = 0; i < edges.length; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < edges.length; i++) {
            if (edges[i][1] == 0) // pass the invalid edge
                continue;

            int child = edges[i][1], father = edges[i][0];
            if (find(parent, father) == child) {// find a circle
                if (can1[0] == -1) // only find a circle w/o a node with 2 parents
                    return edges[i];// 返回当前edge
                // both conditions met
                // since 2nd edge was invalid
                return can1;
            }
            parent[child] = father;
        }
        // if after invalidating 2nd edge, there is no circle
        // 2nd edge is the ans
        return can2;
    }

    int find(int[] parent, int i) {
        while (i != parent[i]) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }
}
