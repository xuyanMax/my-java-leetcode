package tree;

/**
 * 
 * @author xu
 * 
 * https://leetcode.com/submissions/detail/87033382/
 */
public class symmetricTree {

	public static void main(String[] args) {
		

	}
	static boolean isSymmetric (AvlNode root) {
		
		if (root==null)
			return true;
		
		return isSymmetricUtil(root.left, root.right); 
	}
	static boolean isSymmetricUtil(AvlNode left, AvlNode right) {
		
		if (right==null || right==null)
			return right == left;
		
		if (left.data != right.data)
			return false;
		
//		if (root.left != null && root.right!=null && root.left.data == root.right.data)
//			return isSymmetricUtil(root.left) && isSymmetricUtil(root.right);
		 return isSymmetricUtil(left.left, right.right) && isSymmetricUtil(left.right, right.left);
	}
}
