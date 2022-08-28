package linkedList;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author: xyx
 * @Date: 2019-08-04 22:55
 * @Version 1.0
 *
 * Given a binary graph.tree, determine if it is a complete binary graph.tree.
 *
 * Definition of a complete binary graph.tree from Wikipedia:
 * In a complete binary graph.tree every level, except possibly the last,
 * is completely filled, and all nodes in the last level are as far left as possible.
 * It can have between 1 and 2h nodes inclusive at the last level h.
 */
public class CheckCompletenessOfABinaryTree {
    public boolean isCompleteTree(TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();

        queue.add(root);
        boolean seenNull = false;

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr == null) {
                if (!seenNull) seenNull = true;
                continue;

            } else if (seenNull) {
                return false;
            }
            queue.add(root.left);
            queue.add(root.right);
        }
        return true;
    }

    public boolean isCompleteTree2(TreeNode root) {
        Deque<TreeNode> bfs = new ArrayDeque<>();
        bfs.offer(root);
        while (bfs.peek() != null) {
            TreeNode node = bfs.poll();
            bfs.offer(node.left);
            bfs.offer(node.right);
        }
        while (!bfs.isEmpty() && bfs.peek() == null)
            bfs.poll();
        return bfs.isEmpty();
    }

    class TreeNode {
        TreeNode left, right;
        int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }

}
