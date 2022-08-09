/**
 * 时间限制：1秒 空间限制：32768K 热度指数：180517
 * 题目描述
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
 */
package nowcoder;
public class BST_DoubleLinked {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    private TreeNode newRoot = null;
    private TreeNode pre = null;

    public TreeNode Convert(TreeNode root) {
        if (root == null) return root;
        helper(root);
        return newRoot;
    }

    /**
     * 中序遍历
     *
     * @param root
     */
    public void helper(TreeNode root) {
        if (root == null) return;

        helper(root.left);

        if (newRoot != null) {
            root.left = pre;
            pre.right = root;
            pre = root;
        } else { //定位最小值
            newRoot = root;
            pre = root;
        }

        helper(root.right);
    }
}


