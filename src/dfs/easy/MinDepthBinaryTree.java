package dfs.easy;

import tree.AvlNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by xu on 02/08/2017.
 * <p>
 * 111. Minimum Depth of Binary Tre
 * Given a binary tree, find its minimum depth.
 * <p>
 * The minimum depth is the number of nodes along the shortest path
 * from the root node down to the nearest leaf node.
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

    static int searchItr(AvlNode root) {
        if (root == null) return 0;
        int depth = 1;
        AvlNode tmp, magic = new AvlNode(119);

        Deque<AvlNode> queue = new ArrayDeque<>();
        queue.offer(root); // push 入“栈”，先进后出；offer：先进先出
        queue.offer(magic);

        while (!queue.isEmpty()) {
            tmp = queue.poll();
            if (tmp.equals(magic)) {
                if (!queue.isEmpty()) {
                    depth++;
                    queue.offer(magic);
                }
                continue;
            }
            if (tmp.left != null)
                queue.offer(tmp.left);
            if (tmp.right != null)
                queue.offer(tmp.right);
        }
        return depth;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }


}
