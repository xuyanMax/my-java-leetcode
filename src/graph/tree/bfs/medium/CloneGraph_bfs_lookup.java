package graph.tree.bfs.medium;

import java.util.*;

/**
 * Created by xu on 25/08/2017.
 133. Clone Graph
Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
类似的题目查看 hashtable ->  Copy List with Random Pointer
*/
public class CloneGraph_bfs_lookup {



    // graph.tree.bfs 容易理解
    // 哈希表记录已经访问过的节点
    // 队列记录bfs层序遍历的节点
    public UndirectedGraphNode cloneGraph_bfs(UndirectedGraphNode node) {

        if (node == null)
            return node;
        // <Node Value, New Node>
        Map<Integer, UndirectedGraphNode> map = new HashMap<>();
        Deque<UndirectedGraphNode> queue = new ArrayDeque<>();

        UndirectedGraphNode root = new UndirectedGraphNode(node.label);
        map.put(node.label, root);// 添加第一个节点到哈希表

        //将"老"需要拷贝的node加入queue，而不能加入新的root，因为新root不带有相邻节点
        queue.add(node);
        while (!queue.isEmpty()) {

            UndirectedGraphNode curr = queue.poll();

            for (UndirectedGraphNode adj:curr.neighbors) {

                if (!map.containsKey(adj.label)) {//如果已经访问过adj节点，那么不需要更新map和queue，
                                                  // 直接将adj加入curr相邻链表即可
                    //更新queue和map
                    map.put(adj.label, new UndirectedGraphNode(adj.label));
                    queue.add(adj);
                }
                // 取得map中copy的curr节点的neighbors链表，加入从map中可以获得的copy的相邻节点
                map.get(curr.label).neighbors.add(map.get(adj.label) );
            }
        }
        return root;

    }



    class UndirectedGraphNode{
        int label;
        List<UndirectedGraphNode> neighbors;

        public UndirectedGraphNode(int val) {
            this.label = val;
            neighbors = new ArrayList<>();
        }
    }
}
