package tree.easy;

import tree.AvlNode;

/**
 * 
 * @author xu
 *
 * http://www.geeksforgeeks.org/check-if-two-trees-are-mirror/
 */
public class AreMirror {

	public static void main(String[] args) {
	

	}
	static boolean areMirr(AvlNode rootA, AvlNode rootB) {
		if (rootA==null || rootB==null)
			return rootA==rootB;
		if (rootA.key == rootB.key)
			return areMirr(rootA.right, rootB.left) && areMirr(rootA.left, rootB.right);
		
		return false;
		
	}
}
