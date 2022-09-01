package graph.tree.easy;

/**
 * @Author: xyx
 * @Date: 2019-05-08 10:55
 * @Version 1.0
 */
public class LowestCommonAncestor {

    //后序遍历
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        //左右返回节点都不是null，说明当前ROOT节点是LCA
        if (left != null && right != null)
            return root;
        //p, q 均不在以root为父节点的树中
        if (right == null && left == null)
            return null;
        // p or q 有一个在以root为父节点的树中
        return left == null ? right : left;
    }

    class TreeNode {
        TreeNode left, right;
    }
}

