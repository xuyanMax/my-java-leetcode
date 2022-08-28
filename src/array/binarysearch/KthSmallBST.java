package array.binarysearch;

import java.util.LinkedList;

/**
 * Created by xu on 23/07/2017.
 * <p>
 * Given a binary search graph.tree, write a function kthSmallest to find the kth smallest element in it.
 * <p>
 * Note:
 * You may assume k is always valid, 1 ? k ? BST's total elements.
 * <p>
 * Follow up:
 * What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently?
 * How would you optimize the kthSmallest routine?
 *
 */
public class KthSmallBST {
    // visit
    public int kthSmallest(TreeNode root, int k) {
        int count = count(root.left);
        if (k <= count)
            return kthSmallest(root.left, k);
        else if (k > count + 1) {
            return kthSmallest(root.right, k - 1 - count);
        } else // else if (k == count + 1) 1 代表current node
            return root.val;
    }

    public int count(TreeNode n) {
        if (n == null)
            return 0;
        return 1 + count(n.left) + count(n.right);

    }

    static class Number {
        static int number = 0;
    }

    static class Count {
        static int count = 0;
    }

    public int kthSmallest_in(TreeNode root, int k) {
        Count.count = k;
        helper(root);
        return Number.number;
    }

    //中序遍历，逻辑控制部分在"中间"
    public void helper(TreeNode n) {
        if (n.left != null)
            helper(n.left);

        Count.count--;

        if (Count.count == 0) { // 第k最小节点
            Number.number = n.val;
            return; //返回
        }

        //如果count不等于0
        if (n.right != null) {
            helper(n.right);
        }
    }


    ////////////////////////////////////////////////////////////////////////////////
    // visit-preOrder iterative
    public int kthSmallest_dfs_iter(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        while (k != 0) {
            TreeNode curr = stack.pop();
            k--;
            if (k == 0)
                return curr.val;

            TreeNode currRight = curr.right;

            while (currRight != null) {
                stack.push(currRight);
                currRight = currRight.left;
            }

        }
        return -1;// never hit if k is valid
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int v) {
            this.val = v;
            left = null;
            right = null;
        }
    }

}
