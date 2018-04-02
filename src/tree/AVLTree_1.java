package tree;
/**
 * 
 * @author xu
 * Time Complexity: The rotation operations (left and right rotate) take constant time as only 
 * few pointers are being changed there. Updating the height and getting the balance factor 
 * also take constant time. So the time complexity of AVL insert remains same as BST insert 
 * which is O(h) where h is height of the tree. Since AVL tree is balanced, the height is O(Logn). 
 * So time complexity of AVL insert is O(Logn).
 * 
 * 
 * Comparison with Red Black Tree
 * The AVL tree and other self balancing search trees like Red Black are useful to get all basic 
 * operations done in O(Log n) time. The AVL trees are more balanced compared to Red Black Trees, 
 * but they may cause more rotations during insertion and deletion. So if your application involves 
 * many frequent insertions and deletions, then Red Black trees should be preferred. And if the 
 * insertions and deletions are less frequent and search is more frequent operation, 
 * then AVL tree should be preferred over Red Black Tree.
 *
 *
 * Tushar:
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/AVLTree.java
 * http://www.geeksforgeeks.org/avl-tree-set-1-insertion/
 * http://www.geeksforgeeks.org/avl-tree-set-2-deletion/
 */

public class AVLTree_1 {
	
	protected AvlNode root;
	public static void main(String[] args) {
		AVLTree_1 aTree = new AVLTree_1();
		
		aTree.insert(10);
		aTree.insert(15);
		aTree.insert(19);
		aTree.insert(17);
		aTree.insert(26);
		aTree.insert(35);
		aTree.insert(9);

	

	}
	public void insert(int data) {
		AvlNode toBeInsert = new AvlNode(data);
		insertAVL(this.root, toBeInsert);
		
	}
	private void insertAVL (AvlNode root, AvlNode toBeInsert) {
		if (root == null)
			this.root = toBeInsert;
		
			if (root.key < toBeInsert.key)  {
				if (root.right == null) {
					root.right = toBeInsert;
					toBeInsert.parent = root;
					
					// toBeInsert is inserted and needs to be checked for balance
					recursiveBalance(toBeInsert);
				} else 
					 insertAVL(root.right, toBeInsert);
				}
			else if (root.key > toBeInsert.key) {
				if (root.left == null) {
					root.left = toBeInsert;
					toBeInsert.parent = root;
					
					// toBeInsert is inserted and needs to be checked for balance
					recursiveBalance(toBeInsert);
				} else 
					 insertAVL(root.left, toBeInsert);
			}
			else ;// root == toBeInserted
		
		
	}
	/**
	  * Check the balance for each node recursively and call required methods for balancing the tree until the root is reached.
	  * 
	  * @param cur : The node to check the balance for, usually you start with the parent of a leaf.
	  */
	public void recursiveBalance(AvlNode cur) {
	
		setBalance(cur);
		int curBalance = cur.balance;
		switch (curBalance) {
		case 2:
			if (height (cur.right.left) > height (cur.right.right) )
				cur = doubleRightLeftRotate(cur);
			else cur = leftRotate(cur);
			break;
		case -2:
			if (height(cur.left.right) > height(cur.left.left)) 
				cur = doubleLeftRightRotate(cur);
			else 
				cur = rightRotate(cur);
			break;
		default:
			break;
		}
		
		if (cur.parent != null)
			recursiveBalance(cur.parent);
		else {
			this.root = cur;
			System.out.println("Recursive balance done.");
		}
	} 
	
	/**
	  * Left rotation using the given node.
	  * 
	  * 
	  * @param root
	  *            The node for the rotation.
	  * 
	  * @return The root of the rotated tree.
	  */
	private AvlNode leftRotate (AvlNode root) {
		AvlNode newRoot = root.right;
		newRoot.parent = root.parent;
		
		// update root's right branch
		root.right = newRoot.left;
		// update root's right branch's parent as itself
		if(root.right != null)
			root.right.parent = root; 
		
		// perform rotate
		newRoot.left = root;
		root.parent = newRoot;
		
		// check is root is the leftChild/rightChild of root's parent
		if (newRoot.parent != null) {
			if (newRoot.parent.right == root) // right child
				newRoot.parent.right = newRoot;
			if (newRoot.parent.left == root)  //left child
				newRoot.parent.left = newRoot;
		}
		// updating balance will contain the calculation of height 
		root.height = height(root);
		newRoot.height = height(newRoot);
		
		// update balance
		setBalance(root);
		setBalance(newRoot);
		// return new root
		return newRoot;	
	}
	/**
	  * Right rotation using the given node.
	  * 
	  * @param root
	  *            The node for the rotation
	  * 
	  * @return The root of the new rotated tree.
	  */
	private AvlNode rightRotate (AvlNode root) {
		AvlNode newRoot = root.left;
		newRoot.parent = root.parent;
		
		root.left = newRoot.right;
		if (root.left != null)
			root.left.parent = root;
		
		newRoot.right = root;
		root.parent = newRoot;
		
		if (newRoot.parent != null) {
			if (newRoot.parent.left == root)
				newRoot.parent.left = root;
			if (newRoot.parent.right == root)
				newRoot.parent.right = newRoot;
		}
		
		root.height = height(root);
		newRoot.height = height(newRoot);
		
		setBalance(root);
		setBalance(newRoot);
		
		return newRoot;
		
	}
	/**
	  * 
	  * @param root The node for the rotation.
	  * @return The root after the double rotation.
	  */
	public AvlNode doubleLeftRightRotate(AvlNode root) {
		root.left = leftRotate(root.left);
		return rightRotate(root);
		
	}
	
	public AvlNode doubleRightLeftRotate(AvlNode root) {
		root.right = rightRotate(root.right);
		return leftRotate(root);
	}

	/**
	 * 
	 * Helper functions 
	 */
	private void setBalance (AvlNode root) {
		root.balance = height(root.right)-height(root.left);
	}
	
	private int height(AvlNode root) {
		if (root == null)
			return -1;
		if (root.right==null && root.left == null)
			return 0;
		else 
			return 1 + Math.max(root.left==null?height(root.right):0, root.right==null?height(root.left):0);
		
	}
}
