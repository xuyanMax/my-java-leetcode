package dfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by xu on 03/08/2017.
 */
/*
Given a binary tree, find the leftmost value in the last row of the tree.

Example 1:
Input:

    2
   / \
  1   3

Output:
1
Example 2:
Input:

        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7

Output:
7
Note: You may assume the tree (i.e., the given root node) is not NULL.

*/
public class FindBottomLeftTreeNode_bfs {
    // 使用层序遍历解决该问题，每层从右向左扫描
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode curr = null;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            if (curr.right != null)
                queue.add(curr.right);

            if (curr.left != null)
                queue.add(curr.left);
        }
        return curr.val;

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
