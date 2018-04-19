package dfs.easy;

/**
 * Created by xu on 02/08/2017.

 112. Path Sum
Given a binary tree and a sum, determine if the tree has a "root-to-leaf" path such that
adding up all the values along the path equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.

*/
public class PathSum1 {

    public boolean hasPathSum(TreeNode root, int sum) {
        //null 节点
        if (root == null)
            return false;

        //如果是叶节点
        if (root.left == null && root.right == null)
            return sum == root.val;
        //不是叶节点
        return hasPathSum(root.left, sum - root.val)
                || hasPathSum(root.right, sum-root.val);

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
