package tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 
 * @author xu
 * 
 * Given a binary tree, find its minimum depth. The minimum depth is the number of nodes along the shortest path 
 * from the root node down to the nearest leaf node.
 * 
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/#/description
 * 
 */
public class minDepthBinaryTree {

	public static void main(String[] args) {
		
		
	}
	static int search(AvlNode root) {
		
		if (root== null)
			return 0;
		if (root.left != null && root.right==null)
			return search(root.left) + 1;
		if (root.right != null && root.left==null)
			return search(root.right) + 1;
		
		return Math.min(search(root.left), search(root.right)) + 1;
		
	}

	static int searchItr(AvlNode root) {
		if (root == null) return 0;
		int depth = 1;
		AvlNode tmp, magic = new AvlNode(119);
		
		Deque<AvlNode> queue = new ArrayDeque<>();
		queue.offer(root); // push 入“栈”，先进后出；offer：先进先出
		queue.offer(magic);
		
		while (!queue.isEmpty()) {
			tmp = queue.poll();
			if (tmp.equals(magic)) {
				if (!queue.isEmpty()) {
					depth++;
					queue.offer(magic);
				}
				continue;
			}
			if (tmp.left!=null)
				queue.offer(tmp.left);
			if (tmp.right!=null)
				queue.offer(tmp.right);
		}
		return depth;
	}
}
