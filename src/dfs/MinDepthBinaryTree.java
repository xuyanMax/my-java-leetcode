package dfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by xu on 02/08/2017.
 */
/*
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.


*/
public class MinDepthBinaryTree {
    public int minDepth(TreeNode root) {
        //null 节点
        if (root == null)
            return 0;
        //只有左
        if (root.right == null)
            return minDepth(root.left) + 1;
        //只有右
        if (root.left == null)
            return minDepth(root.right) + 1;

        //如果有左右子树
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
    public int minDepthIter(TreeNode root) {

        if (root == null)
            return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode magic = new TreeNode(918);

        queue.add(root);
        queue.add(magic);
        int depth = 1;
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr == magic) {
                if (!queue.isEmpty()) {
                    depth++; //增加一层
                    queue.add(magic);
                }
                continue;
            }
            //如果无左右子树
            if (curr.left == null && curr.right == null)
                return depth;
            if (curr.left != null)
                queue.add(curr.left);
            if (curr.right != null)
                queue.add(curr.right);

        }
        return depth;
    }

}
