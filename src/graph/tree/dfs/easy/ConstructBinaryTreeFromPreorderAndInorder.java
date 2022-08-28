package graph.tree.dfs.easy;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by xu on 20/08/2017.
 */
public class ConstructBinaryTreeFromPreorderAndInorder {
    // 方法1: 参见 graph.tree->BuildTreeByTraversals

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        PreIndex index = new PreIndex(0);
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0)
            return null;
        return helper(preorder, inorder, 0, 0, index);

    }

    public TreeNode helper(int[] pre, int[] in, int left, int right, PreIndex index) {
        if (left > right)
            return null;
        //创建节点
        TreeNode node = new TreeNode(pre[index.index++]);

        int pos = 0;
        for (int i = left; i <= right; i++)
            if (in[i] == node.val)
                pos = i;

        node.left = helper(pre, in, left, pos - 1, index);
        node.right = helper(pre, in, pos + 1, right, index);

        return node;
    }


    class PreIndex {
        int index;

        public PreIndex(int index) {
            this.index = index;
        }
    }

    ////////////
    //方法2
    public TreeNode buildTree_2(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0)
            return null;
        return helper_2(preorder, inorder, 0, 0, inorder.length - 1);
    }

    public TreeNode helper_2(int[] pre, int[] in, int preIndex, int left, int right) {
        if (preIndex == pre.length || right == in.length)
            return null;

        TreeNode node = new TreeNode(pre[preIndex]);

        int pos = 0;
        for (int i = left; i <= right; i++)
            if (in[i] == node.val) {
                pos = i;
                break;
            }
        int diff = pos - left + 1;

        node.left = helper_2(pre, in, preIndex + 1, left, pos - 1);
        node.right = helper_2(pre, in, diff + preIndex, pos + 1, right);

        return node;

    }

    //优化，创建一个 look-up hashmap，记录inorder[]数组内的
    //http://articles.leetcode.com/construct-binary-tree-from-inorder-and-preorder-floorKey-traversal
    public TreeNode buildTree_with_memoize(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();

        for (int i = 0; i < inorder.length; i++)
            inMap.put(inorder[i], i);


        return null;
    }

    public TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        return null;
    }

    /**
     * @param pre
     * @param in
     * @return
     */
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || pre.length == 0) return null;
        return helper(pre, in, new int[1], 0, in.length - 1);
    }

    /**
     * @param pre
     * @param in
     * @param count:记录pre[]的index
     * @param left
     * @param right
     * @return
     */
    public TreeNode helper(int[] pre, int[] in, int[] count, int left, int right) {
        if (left > right) return null;
        TreeNode curr = new TreeNode(pre[count[0]++]);
        int pos = 0;
        for (int i = left; i <= right; i++) {
            if (in[i] == curr.val) {
                pos = i;
                break;
            }
        }
        curr.left = helper(pre, in, count, left, pos - 1);
        curr.right = helper(pre, in, count, pos + 1, right);
        return curr;

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
