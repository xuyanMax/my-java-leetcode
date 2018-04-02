package dfs;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xu on 28/12/2017.
 */
public class PathSum3 {


    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        return helper(root, 0, sum, map);
    }
    public int helper(TreeNode root, int presum, int target, Map<Integer, Integer> map) {
        if (root == null) return 0;

        // update the prefix sum by adding the current node value;
        presum += root.val;
        //get the number of paths, ENDED by this node
        int count = map.getOrDefault(presum - target, 0);
        // update the map with current prefix sum, so the map is good to pass to next recursion
        map.put(presum, map.getOrDefault(presum, 0) + 1);
        // calculate the total number of paths
        // 1. ended by this node
        // 2. rooted by this node's left child
        // 3. rooted by this node's right child
        int total = count + helper(root.left, presum, target, map) + helper(root.right, presum, target, map);

        // unmake the map, as the recursion goes from bottom to top
        map.put(presum, map.getOrDefault(presum, 0) - 1);

        //返回当前节点可能差生的所有paths
        return total;
    }
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
