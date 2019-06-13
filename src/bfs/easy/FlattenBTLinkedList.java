package bfs.easy;

import java.util.LinkedList;

/**
 * Given a binary tree, flatten it to a linked list in-place.
 *
 * For example, given the following tree:
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * The flattened tree should look like:
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 *
 */
public class FlattenBTLinkedList {
    // stack
    public void flatten(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr.left != null || curr.right != null) {
                if (curr.left != null) {
                    if (curr.right != null) {
                        stack.push(curr.right); //curr.right was push to stack pointing to its child tree
                    }
                    curr.right = curr.left;
                    curr.left = null;
                }
                curr = curr.right;
            }
            curr.right = stack.poll();
            curr = curr.right;
        }
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
