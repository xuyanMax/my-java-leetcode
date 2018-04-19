package tree;

import tree.AvlNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 
 * @author xu
 * 
 */
public class MaxTreeWidth {

	public static void main(String[] args) {
		
		/* test method 1*/
		AvlNode root = new AvlNode(100);
		root.left = new AvlNode(99);
		root.right = new AvlNode(98);
		root.left.left = new AvlNode(97);
		root.left.right = new AvlNode(96);
		root.right.left = new AvlNode(95);
		root.right.right = new AvlNode(94);
		System.out.println(getMaxWidth(root));
	}
	
	
	
	/**
	 * method 1
	 * getMaxWidth(Node root)
	 * getWidth(Node root, int level) - get width of each level 
	 * 
	 * time complexity: O(n^2) in the worst case
	 */

	/* dfs function*/
	static int height (AvlNode root) {

		if (root == null) return 0;
		if (root.left != null) return height(root.left) + 1;
		if (root.right != null) return height(root.right) + 1;

		return Math.max(height(root.left), height(root.right)) + 1;

	}
	/* root: level 1, ... 
	 * level is larger than or equal to 1
	 * */
	static int getMaxWidth(AvlNode root) {
		int level = height(root);
		int max = Integer.MIN_VALUE;
		
		while (level >=1 ) {
			int tmpWidth = getWidth(root, level);
			level--;
			if (tmpWidth > max)
				max = tmpWidth;
		}
		return max;
	}
	static int getWidth(AvlNode root, int level) {
		
		if (root == null) return 0;
		if (level == 1)
			return 1;
		
		if (level > 1)
			return getWidth(root.left, level - 1) + getWidth(root.right, level - 1);
		
		return 0;
		
		
	}
	
	/* method 2
	 * use level by level traversal and count the number of eles in a level
	 * */
	
	static int maxWidth(AvlNode root) {
		
		Deque<AvlNode> queue = new ArrayDeque<>();
		queue.offer(root);
		int maxWidth = Integer.MIN_VALUE;
		
		while (!queue.isEmpty()) {
			int levelCount = queue.size();// number of elements for a certain level
			////			////
			if (levelCount > maxWidth) 
				maxWidth = levelCount;
			////			////
			while (levelCount > 0) { 
				root = queue.poll();
				if (root.left != null)
					queue.offer(root.left);
				if (root.right != null)
					queue.offer(root.right);
				levelCount--;
			}
		}
		return maxWidth;
	}
	
	
}
