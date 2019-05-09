package tree.easy;

/**
 * @Author: xyx
 * @Date: 2019-05-08 10:55
 * @Version 1.0
 */
public class LCA {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null)
            return right;
        else if (right == null) return left;//右空，左不空，返回左
        else return root; //左右返回节点都不是null，说明当前节点是LCA


    }

    class TreeNode {
        TreeNode left, right;

    }
}

