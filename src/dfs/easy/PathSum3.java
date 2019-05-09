package dfs.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xu on 28/12/2017.
 * 437. Path Sum III
 * You are given a binary tree in which each node contains an integer value.
 * <p>
 * Find the number of paths that sum to a given value.
 * <p>
 * The path does not need to start or end at the root or a leaf,
 * but it must go downwards (traveling only FROM parent nodes TO child nodes).
 * <p>
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 */
public class PathSum3 {

    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        return dfs(root, 0, sum, map);
    }

    // pre-order
    // 自顶向下dp in HashMap
    public int dfs(TreeNode root, int presum, int target, Map<Integer, Integer> map) {

        if (root == null) return 0;

        // updateHighestHeightBtwLR the prefix sum by adding the current node value;
        presum += root.val;
        //get the number of paths, ENDED by this node
        int count = map.getOrDefault(presum - target, 0);
        // updateHighestHeightBtwLR the map with current prefix sum, so the map is good to pass to next recursion
        map.put(presum, map.getOrDefault(presum, 0) + 1);
        // calculate the total number of paths
        // 1. ended by this node
        // 2. rooted by this node's left child
        // 3. rooted by this node's right child
        int total = count + dfs(root.left, presum, target, map) + dfs(root.right, presum, target, map);

        // unmake the map, as the recursion goes from bottom to top
        map.put(presum, map.get(presum) - 1);

        //向bt上层返回
        //返回当前节点可能差生的所有paths
        return total;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
