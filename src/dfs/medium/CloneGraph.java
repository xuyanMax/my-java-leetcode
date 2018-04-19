package dfs.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloneGraph {
    //dfs
    //使用哈希表记录已经visited的节点
    // map <Integer, UndirectedGraphNode>无法处理具有相同value值的node节点
    // map<UndirectedGraphNode,UndirectedGraphNode>可行
    Map<Integer, UndirectedGraphNode> map = new HashMap<>();
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        return dfs(node);
    }
    public UndirectedGraphNode dfs(UndirectedGraphNode node) {
        if(node == null)
            return node;
        if (map.containsKey(node.label))
            return map.get(node.label);
        // 克隆节点
        UndirectedGraphNode cloned = new UndirectedGraphNode(node.label);

        map.put(cloned.label, cloned);
        //克隆NODE节点的相邻节点,而不是cloned.neighbors
        for (UndirectedGraphNode adj : node.neighbors)
            cloned.neighbors.add(dfs(adj));//不易理解

        return cloned;
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
