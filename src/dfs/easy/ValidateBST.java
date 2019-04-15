package dfs.easy;

/**
 * Created by xu on 19/08/2017.
 * <p>
 * 98. Validate Binary Search Tree
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * <p>
 * Assume a BST is defined as follows:
 * <p>
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * Example 1:
 * 2
 * / \
 * 1   3
 * Binary tree [2,1,3], return true.
 * Example 2:
 * 1
 * / \
 * 2   3
 * Binary tree [1,2,3], return false.
 */
public class ValidateBST {


    // Basically, recursively iterating the tree while defining the interval <min, max> for each node value
    // that must fits in.
    // 由于TreeNode val变量为int值，因此我们对于root的初始值设为 LONG.MIN, LONG.MAX
    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // dfs 函数  可以通过 OJ 测试
    public boolean dfs(TreeNode root, long min, long max) {
        if (root == null)
            return true;

        if (root.val <= min || root.val >= max)
            return false;

        return dfs(root.left, min, root.val) &&
                dfs(root.right, root.val, max);
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
