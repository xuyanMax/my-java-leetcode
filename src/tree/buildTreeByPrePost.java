package tree;

/**
 * 
 * @author xu
 *
 *
 *
 * A Full Binary Tree is a binary tree where every node has either 0 or 2 children
 * 
 * Detained explanation:
 * http://www.geeksforgeeks.org/full-and-complete-binary-tree-from-given-preorder-and-postorder-traversals/
 * 
 */
public class buildTreeByPrePost {

	public static void main(String[] args) {
	

	}
	static AvlNode buildByPrePost(int[] pre, int[] post){
		
		Index PreIndex = new Index();
		
		PreIndex.index = 0;
		
		return buildByPrePostUtil(pre, post, 0, post.length - 1, PreIndex);
		
	}
	static AvlNode buildByPrePostUtil(int[] pre, int[] post, int left, int right, Index PreIndex) {
		
		if (left < right) return null;

		// The first node in preorder traversal is root. So take the node at
		// preIndex from preorder and make it root, and increment preIndex
		AvlNode node = new AvlNode(PreIndex.index);
		PreIndex.index++;
		
		if (left == right) return node;

		// difference to buildTree by pre/post-order and in-order.
		int postIndex = search(post, left, right, pre[PreIndex.index]);
		
		// Use the index of element found in postorder to divide postorder array in
		// two parts. Left subtree and right subtree
		node.left = buildByPrePostUtil(pre, post, left, postIndex, PreIndex); // include post[postIndex] it self as the left tree
		node.right = buildByPrePostUtil(pre, post, postIndex + 1, right - 1, PreIndex); // exclude the post[right-1] as the right tree.
		
		return node;	
		
		
	}
  /* Function to find index of value in arr[start...end] */
	static int search(int[] post, int left, int right, int target) {
		int pos;
		for (pos = left; pos <= right; pos++) {
			if (post[pos] == target)
				break;
		}
		return pos;
		
	}
	
	static class Index{
		int index;
	}

}
