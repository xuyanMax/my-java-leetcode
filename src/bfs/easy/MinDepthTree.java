package bfs.easy;

import java.util.LinkedList;
import java.util.Queue;
/*
* Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path
from the root node down to the nearest leaf node.

* */
public class MinDepthTree {
    public int minDepthIter(TreeNode root) {

        if (root == null)
            return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode magic = null;

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
            //如果无左右子树,则返回高度
            if (curr.left == null && curr.right == null)
                return depth;
            if (curr.left != null)
                queue.add(curr.left);
            if (curr.right != null)
                queue.add(curr.right);

        }
        return depth;
    }
    class TreeNode{
        TreeNode left,right;
        int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
