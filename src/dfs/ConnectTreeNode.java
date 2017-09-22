package dfs;

/**
 * Created by xu on 09/08/2017.
 */
/*
Populate each next pointer to point to its next right node. If there is no next right node,
the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
You may assume that it is a perfect binary tree (ie, all leaves are at the same level,
and every parent has two children).

For example,
Given the following perfect binary tree,
         1
       /  \
      2    3
     / \  / \
    4  5  6  7

After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL
*/
public class ConnectTreeNode {

    //注意利用这是一个"完美树"的条件，即有左子树，就一定有右子树
    public void connect(TreeLinkNode root) {
        // 递归结束条件：root空，或者root无子节点
        if (root == null || root.left == null)
            return;
        helper(root.left, root.right);
    }
    public void helper(TreeLinkNode a, TreeLinkNode b){

        // 默认所有节点.next == null
        a.next = b;

        // a == null 没有必要检测
        if ( a.left == null)
            return;

        helper(a.left, a.right);
        helper(a.right, b.left);
        helper(b.left, b.right);
    }

    // 层序遍历
    public void connect_iter(TreeLinkNode root) {
        while (root != null) {

            //提取每一层的最左侧节点
            TreeLinkNode curr = root;
            //只要该节点不是空，且存在子节点
            while (curr != null && curr.left != null) {
                //左子树的next为右子树
                curr.left.next = curr.right;
                //右子树的next分两种情况
                // 1. 如果当前节点无兄弟，或者说当前节点的next是null：
                // 2. 如果当前节点有兄弟，或者说当前节点的next不是null：
                curr.right.next = curr.next == null ? null : curr.next.left;
                curr = curr.next;
            }
            //进入下一层
            root = root.left;
        }
    }
    class TreeLinkNode{
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int v) {
            val = v;
            left = null;
            right = null;
            next = null;
        }
    }
}

