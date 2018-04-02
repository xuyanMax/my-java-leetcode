package tree;

import java.util.LinkedList;
import java.util.SimpleTimeZone;

/**
 * Created by xu on 23/09/2017.
 */

/*
Given a binary tree, flatten it to a linked list in-place.

For example, Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:

   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
click to show hints.

Hints: If you notice carefully in the flattened tree,
each node’s right child points to the next node of a pre-order traversal.


http://www.ciaoshen.com/algorithm/leetcode/2017/05/12/leetcode-flatten-binary-tree-to-linked-list.html

*/
public class Flatten {

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
