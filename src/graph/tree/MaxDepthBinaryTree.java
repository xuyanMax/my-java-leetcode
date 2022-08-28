package graph.tree;

/**
 * @author xu
 * <p>
 * Given a binary graph.tree, find its maximum depth.
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * <p>
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/#/description
 */
public class MaxDepthBinaryTree {

    public int maxDepth(AvlNode root) {
        if (root == null) return 0;
//		if (root.left!=null && root.right==null) return maxDepth(root.left) + 1;
//		if (root.right!=null && root.left==null) return maxDepth(root.right) + 1;
//
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

}
