package binarysearch;

/**
 * Created by xu on 24/07/2017.
 * <p>
 * Given a complete binary tree, count the number of nodes.
 * <p>
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, is completely filled, and
 * all nodes in the last level are as far left as possible.
 * It can have between 1 and 2h nodes inclusive at the last level h.
 */
public class CountCompleteBinaryTreeNodes {

    // the height of a tree can be just found by going left.
    // let a single node tree have height 0, find the height h of the whole tree
    int height(TreeNode root) {
        if (root == null) // tree empty return -1
            return -1;
        return 1 + height(root.left);
    }

    public int countNodes(TreeNode root) {
        // find height of whole tree
        int h = height(root);

        if (h < 0) // height = -1
            return 0; // return 0 count
        else {
            // if the height of the right-subtree is just 1 less than that of the whole tree,
            // meaning left and right subtree have the same height
            // if yes:then the last node on the last tree row is in the right subtree and the left subtree
            // is a full tree of height h-1. So we take 2^(h) -1 nodes + 1 root node + RECURSIVELY the number of nodes on the
            // right subtree

            // if no (height(root.right) == h-2), then the last node on the last tree row is in the left subtree.
            // and the right subtree is a full tree of height h-2. So we take 2^(h-1) - 1 nodes + 1 root node +
            // RECURSIVELY the number of nodes on the left subtree.
            if (height(root.right) == h - 1) {
                return (1 << h) + countNodes(root.right);
            } else
                return (1 << h - 1) + countNodes(root.left);
        }

    }

    ////////////////////////////////////////////////////////////
    // ITERATIVE
    // No need to compute h in every step
    public int countNodes_Iter(TreeNode root) {
        int nodes = 0, h = height(root);
        while (root != null) {
            if (height(root.right) == h - 1) {
                nodes += 1 << h; // 右子树为满二叉树 高度h-1 (root是第0层 2^(h) - 1 + 1 root )
                root = root.left;
            } else {
                nodes += 1 << (h - 1);
                root = root.right;
            }
            h--;
        }
        return nodes;
    }

    ////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////
    public int countNodes_TLE(TreeNode root) {
        return helper_TLE(root);
    }

    public int helper_TLE(TreeNode root) {
        if (root == null)
            return 0;
        return helper_TLE(root.left) + helper_TLE(root.right) + 1;
    }
    ////////////////////////////////////////////////////////////

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
