package graph.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
*
* 310. Minimum Height Trees
*
* For a undirected graph with graph.tree characteristics, we can choose any node as the find.
* The result graph is then a rooted graph.tree. Among all possible rooted trees,
* those with minimum height are called minimum height trees (MHTs).
* Given such a graph, write a function to find all the MHTs and return a list of their find labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n
and a list of undirected edges (each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. Since all edges are undirected,
[0, 1] is the same as [1, 0] and thus will not appear together in edges.

Example 1:

Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3
return [1]

Example 2:

Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5
return [3, 4]
* */
public class MinimumHeightTree {

    // BFS topological sort，从叶子结点向中间BFS toposort
    // 直到剩下的节点数为1或2
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        // 声明临接表
        List<Set<Integer>> adj = new ArrayList<>();
        //初始化临接表
        for (int i=0; i<n; i++) adj.add(new HashSet<>());
        //创建临接表, directed
        for (int[] edge:edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        // 声明叶子节点，即入度为1的节点
        List<Integer> leaves = new ArrayList<>();
        for (int i=0; i<n; i++)
            if (adj.get(i).size()==1)
                leaves.add(i);

        while (n > 2) {
            n -= leaves.size();//剩余节点个数 = 当前节点数 - 当前叶子节点数
            List<Integer> newLeaves = new ArrayList<>();
            for (int i=0; i<leaves.size(); i++){
                int j = adj.get(leaves.get(i)).iterator().next();
                adj.get(j).remove(i);
                if (adj.get(j).size()==1) newLeaves.add(j);
            }
            leaves = newLeaves;
        }

        return res;
    }
}
