package dfs.medium;

/**
 * Created by xu on 28/12/2017.
 */
/*
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?

* */
public class RecoverBinaryTree {
    //DFS中序遍历
    // [4,2,6,1,3,5,7] ->经过错误的交换后[4,2,3,1,6,5,7]
    //错误交换后，中序遍历{1,2,6,4,5,3,7}
    //规律是，第一个需要交换的节点为6，因此6>4，第二个需要交换的节点是3，因为3比5要小
    //-通过全局变量记录，第一个要交换节点和第二个要交换节点，
    //-交换两个节点的对应数据即可
    public void recoverTree(TreeNode root) {
        inorder(root);
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }

    private TreeNode first = null;
    private TreeNode second = null;
    private TreeNode pre = new TreeNode(Integer.MIN_VALUE);//中序遍历第-1个元素，保证比第零个元素数值小

    public void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);

        //只有在第一个要交换元素确定后，才可以确定第二个要交换的元素
        //确定第一个:1,2,6,4，发现当前节点4比pre节点6小，因此pre节点为第一个元素
        if (first == null && root.val <= pre.val)
            first = pre;
        //5,3,7当前节点3比pre节点5小，因此当前节点为第二个元素
        if (first != null && root.val <= pre.val)
            second = root;
        //更新pre为当前节点
        pre = root;

        inorder(root.right);

    }

    class TreeNode {
        int val;
        TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
