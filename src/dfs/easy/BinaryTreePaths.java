package dfs.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xu on 02/08/2017.
 * <p>
 * Given a binary tree, return all root-to-leaf paths.
 * <p>
 * For example, given the following binary tree:
 * <p>
 *   1
 * /   \
 * 2     3
 * \
 * 5
 * All root-to-leaf paths are:
 * <p>
 * ["1->2->5", "1->3"]
 */
public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ret = new ArrayList<>();
        if (root != null)
            helper(root, ret, "");
        return ret;
    }

    public void helper(TreeNode root, List<String> paths, String path) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            paths.add(path + root.val);
            return;
        }
        if (root.left != null)
            helper(root.left, paths, path + root.val + "->");
        if (root.right != null)
            helper(root.right, paths, path + root.val + "->");
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
