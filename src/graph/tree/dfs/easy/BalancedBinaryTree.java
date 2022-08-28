package graph.tree.dfs.easy;

/**
 * Created by xu on 02/08/2017.
 * <p>
 * Given a binary graph.tree, determine if it is height-balanced.
 * <p>
 * For this problem, a height-balanced binary graph.tree is defined as a binary graph.tree in which
 * the depth of the two subtrees of every node never differ by more than 1.
 */
public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        if (Math.abs(leftDepth - rightDepth) > 1)
            return false;

        return isBalanced(root.left) && isBalanced(root.right);
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

}
