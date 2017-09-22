package dfs;

/**
 * Created by xu on 02/08/2017.
 */
public class MaxDepthBinaryTree {


//    public int maxDepthIter(TreeNode root) {
//
//    }

    // visit
    public int maxDepth(TreeNode root) {
        if (root==null)
            return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
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
