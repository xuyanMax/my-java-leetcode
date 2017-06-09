package tree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 
 * @author xu
 *
 * Given two binary trees, write a function to check if they are equal or not.
 * Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
 * 
 * https://leetcode.com/problems/same-tree/#/description
 */
public class sameTree {

	public static void main(String[] args) {
		

	}
	static boolean isSameTree(AvlNode rootA, AvlNode rootB) {
	
		
		if (rootA==null || rootB==null ) return rootA == rootB;	
		
		return (rootA.data == rootB.data) && isSameTree(rootA.left, rootB.left) && isSameTree(rootA.right, rootB.right);
		
	}
	
	/* Iterative method */
	static boolean isSameTreeItr(AvlNode rootA, AvlNode rootB) {
		
		Deque<AvlNode> treeA = new ArrayDeque<>();
		Deque<AvlNode> treeB = new ArrayDeque<>();
		treeA.push(rootA);
		treeB.push(rootB);
		
		while (!treeA.isEmpty() && !treeB.isEmpty()) {
			AvlNode tmpA = treeA.pop();
			AvlNode tmpB = treeB.pop();
			
			if (tmpA == null && tmpB == null) continue;
			if (tmpA == null || tmpB ==null || tmpA.data != tmpB.data) return false;
			
			treeA.push(tmpA.left);
			treeB.push(tmpB.left);
			treeA.push(tmpA.right);
			treeB.push(tmpB.right);
		}
		return true;
	}
}
