package a_OA;


import tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 2022-06-28
 */
public class traversals {
    public void inorder_iter(TreeNode root) {
        if (root == null) return;
        Deque<TreeNode> queue = new LinkedList<>();
        TreeNode curr = root;
        while (true) {
            queue.addFirst(curr);
            if (curr.left != null) {
                curr = curr.left;
            } else {
                if (queue.isEmpty()) break;
                curr = queue.pollFirst();
                System.out.println(curr.val);
                curr = curr.right;
            }
        }
    }

    public void preorder_iter(TreeNode root) {
        if (root == null) return;
        TreeNode curr = root;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(curr);

        while (!queue.isEmpty()) {
            curr = queue.pollFirst();
            System.out.println(curr.val);

            if (curr.left != null)
                queue.add(curr.left);
            if (curr.right != null)
                queue.add(curr.right);
            curr = curr.left;
        }
    }

    public void postorder_iter(TreeNode root) {
        if (root == null) return;
        Deque<TreeNode> queue1 = new LinkedList<>();
        Deque<TreeNode> queue2 = new LinkedList<>();
        TreeNode curr = root;
        queue1.add(curr);
        while (!queue1.isEmpty()) {
            curr = queue1.pollFirst();
            if (curr.right != null)
                queue1.add(curr.right);
            if (curr.left != null)
                queue2.add(curr.left);
            queue2.add(curr);
        }
        while (!queue2.isEmpty()) {
            curr = queue2.pollFirst();
            System.out.println(curr.val);
        }
    }
}
