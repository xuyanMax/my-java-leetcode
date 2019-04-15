package dfs.medium;
/*
687. Longest Univalue Path

Given a binary tree, find the length of the longest path where each node in the path has the same value.
This path may or may not pass through the root.

Note: The length of path between two nodes is represented by the number of edges between them.

Example 1:

Input:

5
/ \
4   5
/ \   \
1   1   5
Output:

2
Example 2:

Input:

1
/ \
4   5
/ \   \
4   4   5
Output:
2

*/

public class LongestUnivaluePath {
    public int longestUnivaluePath(TreeNode root) {
        int[] res = new int[1];
        dfs(root, res);
        return res[0];
    }

    public int dfs(TreeNode root, int[] res) {
        if (root == null) return 0;
        //递归到叶子节点
        int left = root.left != null ? dfs(root.left, res) : 0;
        int right = root.right != null ? dfs(root.right, res) : 0;

        //从下向上对节点整合
        int resLeft = (root.left != null && root.val == root.left.val) ? 1 + left : 0;
        int resRight = (root.right != null && root.val == root.right.val) ? 1 + right : 0;
        res[0] = Math.max(res[0], resLeft + resRight);
        return Math.max(resLeft, resRight);
    }

    class TreeNode {
        TreeNode left, right;
        int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
