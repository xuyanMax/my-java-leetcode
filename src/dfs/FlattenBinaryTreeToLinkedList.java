package dfs;

/**
 * Created by xu on 20/08/2017.
 */
/*
Given a binary tree, flatten it to a linked list IN-PLACE.

For example,
Given

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

Hints:
If you notice carefully in the flattened tree, each node's right child points to the
next node of a pre-order traversal.


*/
public class FlattenBinaryTreeToLinkedList {

    // use reversed preorder 反向的前序遍历，不是真正意义上的后序遍历
    // pre:指向某节点元素的子节点（左或者右节点）
    private TreeNode pre = null;

    public void flatten_to_right(TreeNode root) {
        if (root == null)
            return;
        flatten_to_right(root.right);
        flatten_to_right(root.left);

        root.right = pre;
        root.left = null;
        pre = root;
    }
    public void flatten_to_right_2(TreeNode root) {
         flatten_to_right_2(root, null);
    }
    public TreeNode flatten_to_right_2(TreeNode root, TreeNode pre) {
        if (root == null)
            return pre;

        //pre节点为右节点
        pre = flatten_to_right_2(root.right, pre);
        // 如果root.left 不为null,则pre节点更新为左节点
        pre = flatten_to_right_2(root.left, pre);

        root.right = pre;
        //左节点设为null
        root.left = null;
        //更新pre
        pre = root;
        //返回父节点，将该子节点作为父节点的pre节点
        return pre;

    }
    public void flatten_to_right_iter(TreeNode root) {

    }



    /*
    *                       1
    *                      /
    *                     2
    *                    /
    *                   3
    *                  /
    *                 4
    *                /
    *               5
    *
    * */
    // 形成左侧链表
//    private TreeNode pre_ = null;

    public void flatten_to_left(TreeNode root) {
        helper(root, root);

    }
    public TreeNode helper(TreeNode root, TreeNode pre) {
        if (root == null)
            return pre;

        pre = helper(root.left, root);
        pre = helper(root.right, root);

        pre.left = root;
        pre.right = null;
        return pre;
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
