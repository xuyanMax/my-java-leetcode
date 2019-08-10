package linkedList;

/**
 * @Author: xyx
 * @Date: 2019-08-04 22:11
 * @Version 1.0
 *
 * 114. Flatten Binary Tree to Linked List
 * Medium
 *
 * 1625
 *
 * 211
 *
 * Favorite
 *
 * Share
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
 */
public class FlattenBinaryTreeToLinkedList {
    TreeNode pre;
    public void flatten(TreeNode root) {
        if (root == null) return;

        flatten(root.right);
        flatten(root.left);

        root.left = null;
        root.right = pre;
        pre = root;
    }

    class TreeNode {
        TreeNode left, right;
        int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
