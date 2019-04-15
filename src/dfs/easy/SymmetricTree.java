package dfs.easy;

/**
 * Created by xu on 02/08/2017.
 */
public class SymmetricTree {

    public boolean symmetricTree(TreeNode root) {
        if (root == null)
            return true;
        return helper(root.left, root.right);
    }

    public boolean helper(TreeNode left, TreeNode right) {

        if (left == null || right == null)
            return left == right;
        return helper(left.left, right.right) &&
                helper(left.right, right.left);
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
