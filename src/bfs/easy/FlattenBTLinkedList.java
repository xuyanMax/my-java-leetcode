package bfs.easy;

import java.util.LinkedList;

public class FlattenBTLinkedList {
    // stack
    public void flatten(TreeNode root){
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
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
