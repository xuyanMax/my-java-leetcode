package dfs;

/**
 * Created by xu on 19/08/2017.
 */
/*
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
Example 1:
    2
   / \
  1   3
Binary tree [2,1,3], return true.
Example 2:
    1
   / \
  2   3
Binary tree [1,2,3], return false.
*/
public class ValidateBST {

    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;
        return dfs(root);
    }
    // visit 失败与下列二叉树
    // [10,5,15,null,null,6,20]
    //[3,3]
    //[1,null,2]
    public boolean dfs(TreeNode root) {
        if (root == null || (root.left == null && root.right == null))
            return true;
        boolean left=false, right=false;
        if (root.left != null)
            if (root.val <= root.left.val)
                return false;
            else
                left = dfs(root.left);
        else
            left = true;

        if (root.right != null )
            if (root.val >= root.right.val)
                return false;
            else
                right = dfs(root.right);
        else
            right = true;

        return left && right;
    }

    // Basically, recursively iterating the tree while defining the interval <min, max> for each node value
    // that must fits in.
    // 由于TreeNode val变量为int值，因此我们对于root的初始值设为 LONG.MIN, LONG.MAX
    public boolean isValidateBST_pass(TreeNode root) {
        if (root == null)
            return true;
        return isValidateBST_helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // helper 函数  可以通过 OJ 测试
    public boolean isValidateBST_helper(TreeNode root, long min, long max) {
        if (root == null)
            return true;

        if (root.val <= min || root.val >= max)
            return false;

        return isValidateBST_helper(root.left, min, root.val) &&
                isValidateBST_helper(root.right, root.val, max);
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
