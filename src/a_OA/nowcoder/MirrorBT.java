package a_OA.nowcoder;

import java.util.ArrayDeque;

/**
 * 镜像二叉树
 */
public class MirrorBT {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.val = value;
        }
    }

    /**
     * recursice
     *
     * @param root
     * @return
     */
    public void mirrorRec(TreeNode root) {
        if (root == null) return;
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        if (root.left != null)
            mirrorRec(root.left);
        if (root.right != null)
            mirrorRec(root.right);

    }

    public void mirrorIter(TreeNode root) {
        if (root == null) return;
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            TreeNode curr = deque.pollFirst();
            if (curr.left != null || curr.right != null) {
                TreeNode tmp = curr.left;
                curr.left = curr.right;
                curr.right = tmp;
            }
            if (curr.right != null)
                deque.add(curr.right);
            if (curr.left != null)
                deque.add(curr.left);
        }

    }
}
