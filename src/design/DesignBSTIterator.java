package design;

import java.util.LinkedList;

// https://leetcode.com/problems/binary-search-tree-iterator/#/description
/*
 * Question:
 * Implement an iterator over a binary search tree (BST). 
 * Your iterator will be initialized with the root node of a BST.
 * 
 * Calling next() will return the next smallest number in the BST.
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 * 
 * Idea:
 * The idea is to store root's left child and child's left child all the way to the end to the stack
 * when next() called, pop the top Node and process its right child as new-root
 * 
 * This can meet O(h) space but Next() is O(h) rather than O(1)
 * 
 */
public class DesignBSTIterator {
/*
	I use Stack to store directed left children from root.
	**When next() be called, I just pop one element and process its right child as new root.
	The code is pretty straightforward.
    So this can satisfy O(h) memory, hasNext() in O(1) time,
	But next() is O(h) time.

	I can’t find a solution that can satisfy both next() in O(1) time, space in O(h).

*/

	LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
	    public DesignBSTIterator(TreeNode root) {
	        pushAll(root);
	    }

	    /** @return whether we have a next smallest number */
	    public boolean hasNext() {
	        return !stack.isEmpty();
	    }

	    /** @return the next smallest number */
	    public int next() {
	        TreeNode top = stack.pop();
	        pushAll(top.right);
	        return top.val;
	    }
	    /** push */
	    public void pushAll(TreeNode root){
	        while(root != null) {
	            stack.push(root);
	            root  = root.left;
	        }
	        
	    }
	class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x ) {val = x;}
	}
}
