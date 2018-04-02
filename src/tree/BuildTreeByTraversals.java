package tree;

/**
 * 
 * @author xu
 * 
 * Given in-order and post-order traversal of a tree, construct the binary tree.
 * Note: You may assume that duplicates do not exist in the tree.
 * 
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/#/description
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/ContructTreeFromInorderPostOrder.java
 * 
 * http://www.geeksforgeeks.org/construct-a-binary-tree-from-postorder-and-inorder/
 * 
 */
public class BuildTreeByTraversals {

	public static void main(String[] args) {
		

	}
	// This function mainly initializes index of root
    // and calls buildUtil()
	// O(n^2)
	static AvlNode build(int[]post, int[]in){
		Index pIndex = new Index();
		pIndex.index = post.length-1;
		
		return buildUtil(post, in, 0, in.length-1, pIndex);
	}
	static AvlNode buildUtil(int[]post, int[]in, int left, int right, Index pIndex){
		
		if (left>right) return null; // base case;
		
		/* Pick current node from Post-order traversal using
        postIndex and decrement postIndex */
		AvlNode node = new AvlNode(post[pIndex.index]);
		pIndex.index--;
		
		/* If this node has no children then return */
		if (left==right) 
			return node;
		
	   /* Else find the index of this node in In-order
        traversal */
		int inPos = search(in, left, right, node.key);
		
	  /* Using index in In-order traversal, construct left and
        right sub-tress */
		node.right = buildUtil(post, in, inPos + 1, right, pIndex);
		node.left = buildUtil(post, in, left, inPos - 1, pIndex);
		
		
		return node;
	}
	
     /* Function to find index of value in arr[start...end] */
	static int search(int[] in, int left, int right, int target) {
		int pos;
		for (pos = left; pos <= right; pos++) {
			if (in[pos] == target)
				break;
		}
		return pos;
		
	}
	
	static class Index{
		int index;
	}
	
	/**
	 * construct tree from given pre-order and in-order traversals.
	 */
	
	static AvlNode buildTree2(int[]pre, int[]in) {
		Index preIndex = new Index();
		preIndex.index = 0;
		
		return buildTree2Util(pre, in, 0, pre.length-1, preIndex);
		
	} 
	static AvlNode buildTree2Util(int[]pre, int[]in, int left, int right, Index preIndex) {
		
		if (left > right) return null;
		AvlNode node = new AvlNode(pre[preIndex.index]);

		preIndex.index++;
		
		if (left==right) 
			return node;
		
		int inPos = search(in, left, right, node.key);
		
		node.left = buildTree2Util(pre, in, left, inPos - 1, preIndex);
		node.right = buildTree2Util(pre, in, inPos + 1 , right, preIndex);
		
		return node;
		
	}



}
