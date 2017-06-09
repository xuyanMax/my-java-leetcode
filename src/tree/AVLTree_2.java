package tree;
/**
 * 
 * @author xu
 * reference;
 * http://www.geeksforgeeks.org/avl-tree-set-2-deletion/
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/AVLTree.java
 * 
 */
public class AVLTree_2 {

	// class Node (N) avoiding duplicate classes
//	protected Node root;
	
	public static void main(String[] args) {
		AVLTree_2 avlTree_2 = new AVLTree_2();
		// TEST 1
		// using N class as node 
//		avlTree_2.root = avlTree_2.insert(avlTree_2.root, 5);
//		avlTree_2.root = avlTree_2.insert(avlTree_2.root, 2);
//		avlTree_2.root = avlTree_2.insert(avlTree_2.root, 7);
//		avlTree_2.root = avlTree_2.insert(avlTree_2.root, 1);
//		avlTree_2.root = avlTree_2.insert(avlTree_2.root, 4);
//		avlTree_2.root = avlTree_2.insert(avlTree_2.root, 3);
//		avlTree_2.root = avlTree_2.insert(avlTree_2.root, 6);
//		avlTree_2.root = avlTree_2.insert(avlTree_2.root, 9);
//		avlTree_2.root = avlTree_2.insert(avlTree_2.root, 16);
//		avlTree_2.root = avlTree_2.insert(avlTree_2.root, 15);
//		avlTree_2.inOrder(avlTree_2.root);
		// 涉及到代码重用问题 <T>
		// 未来参考 graph->Graph 范型类
		//TreeTraversals ttt = new TreeTraversals();
		// ttt.inOrder(Node curr) does not allow AvlNode
		// 并添加 in-order traversal 多态 methods
		
		// TEST 2
		// using AvlNode as node class
		AvlNode root = null;
		root = avlTree_2.insert(root, 5);
		root = avlTree_2.insert(root, 2);
		root = avlTree_2.insert(root, 7);
		root = avlTree_2.insert(root, 1);
		root = avlTree_2.insert(root, 4);
		root = avlTree_2.insert(root, 3);
		root = avlTree_2.insert(root, 6);
		root = avlTree_2.insert(root, 9);
		root = avlTree_2.insert(root, 16);
		root = avlTree_2.insert(root, 15);
		
		avlTree_2.inOrder(root);
		root = avlTree_2.delete(root, 15);
		System.out.println("\nafter delete 15");
		avlTree_2.inOrder(root);
		
	}
			
		
	/**
	 * 
	 * @param root Tree root
	 * @param data new data for the toBeInsertNode
	 * @return Tree root
	 * 
	 * Following is the implementation for AVL Tree Insertion. The following implementation uses the recursive BST insert to insert a new node. 
	 * In the recursive BST insert, after insertion, we get pointers to all ancestors one by one in bottom up manner. 
	 * So we don’t need parent pointer to travel up. The recursive code itself travels up and visits all the ancestors of the newly inserted node.
		1) Perform the normal BST insertion.
		2) The current node must be one of the ancestors of the newly inserted node. Update the height of the current node.
		3) Get the balance factor (left subtree height – right subtree height) of the current node.
		4) If balance factor is greater than 1, then the current node is unbalanced and we are either in Left Left case 
		or left Right case. To check whether it is left left case or not, compare the newly inserted key with the key in left subtree root.
		5) If balance factor is less than -1, then the current node is unbalanced and we are either in Right Right case or 
			Right Left case. To check whether it is Right Right case or not, 
			compare the newly inserted key with the key in right subtree root.
	 */
	AvlNode insert(AvlNode root, int data) {
		
		/* 1. perform the normal BST insertion.*/
		if (root==null)
			return new AvlNode(data);
		if (data < root.data)
			root.left = insert(root.left, data);
		else if (data > root.data)
			root.right = insert(root.right, data);
		else // Equal data not allowed
			return root;
		
		/* 2. Update height of this ancestor node */
		root.height = setHeight(root);
		
		/* 3. Get the balance factor of this ancestor
		node to check whether this node became
		unbalanced */
		int balance = getBalance(root);
		
		// If this node becomes unbalanced, then
		// there are 4 cases Left Left Case
		
		// Left case
		
		// Right case
		
		// Left Right case
		
		// Right Left case
		
		if (balance > 1) {// left > right
			if (height(root.left.left) >= height(root.left.right))
				root = rightRotate(root);
			else // left-right-rotate
				root = leftRightRotate(root);
		}
		else if (balance < -1) {
			if (height(root.right.right) >= height(root.right.left)) 
				root = leftRotate(root);
			else // right-left-rotate  
				root = rightLeftRotate(root);
		}
		else {
			root.height = setHeight(root);
		}
		return root;
		
	}
	/*
	 * time complexity O(h) = O(lgn), since AVL is balanced tree
	 */
	AvlNode delete(AvlNode root, int data) {
		
		/* 1. perform standard BST DELETE */
		if (root == null)
			return null;
		if (root.data > data) 
			root.left = delete(root.left, data);
		else if (root.data < data) 
			root.right = delete(root.right, data);
		else { // this is the node to be selected
			
			// node with only one child or no child
			if (root.left == null || root.right ==null) {
				AvlNode tmp = null;
				if (root.left == null)
					tmp = root.right;
				else tmp = root.left;
				
				// NO CHILD CASE
				if (tmp == null) {
					tmp = root;
					root = null;
					
				}
				// ONE CHILD CASE
				else 
					root = tmp;// copy the non-empty child to root
			
			} 
			else { // the node toBeDeleted has two children
				// find the next larger node in root.right subtree
				AvlNode nextLarger = minValueNode(root.right);
				
				// coppy the nextLarger data into root.data
				root.data = nextLarger.data;
				
				// DELETE the nextLarger node from the root.right subtree
				root.right = delete(root.right, root.data);
			}
			// if tree has only one node w/o children
			if (root == null)
				return root;
			/*2. UPDATE HEIGHT OF THE CURRENT NODE */
			root.height = setHeight(root);
			
			/*3. UPDATE BALANCE OF THE CURRENT NODE */
			int balance = getBalance(root);

			// FOUR CASES
			if (balance > 1) {// left > right
				if (getBalance(root.left.left)>=getBalance(root.left.right))
					root = rightRotate(root);
				else // left-right-rotate
					root = leftRightRotate(root);
			}
			else if (balance < -1) {
				if (height(root.right.right) >= height(root.right.left)) 
					root = leftRotate(root);
				else // right-left-rotate  
					root = rightLeftRotate(root);
			}
			else {
				root.height = setHeight(root);
			}
			
		}
		return root;
		
		
	}
	AvlNode minValueNode(AvlNode curr) {
		if (curr==null)
			return null;
		AvlNode tmp = curr;
		while (tmp.left != null) 
			tmp = tmp.left;
		
		return tmp;
	}
	
	
	AvlNode leftRotate(AvlNode curr) {
		
		AvlNode newHead = curr.right;
		AvlNode tmp =  newHead.left;
		
		// perform rotate
		newHead.left = curr;
		curr.right = tmp;
		// updates heights
		setHeight(curr);
		setHeight(newHead);
		
		return newHead;
	}
	AvlNode rightRotate(AvlNode curr) {
		
		AvlNode newHead = curr.left;
		AvlNode tmp =  newHead.right;
		
		// perform rotate
		newHead.right = curr;
		curr.left = tmp;
		// updates heights
		setHeight(curr);
		setHeight(newHead);
		
		return newHead;
	}
	AvlNode leftRightRotate(AvlNode curr) {
		curr.left = leftRotate(curr.left);
		return rightRotate(curr);
		
	}
	AvlNode rightLeftRotate(AvlNode curr) {
		curr.right = rightRotate(curr.right);
		return leftRotate(curr);
		
	}
	
	public int getBalance (AvlNode curr) {
		if (curr==null)
			return 0;
		else 
			return height(curr.left)-height(curr.right);
	}
	
	public int setHeight(AvlNode curr) {
		if (curr == null)
			return -1;
		if (curr.left==null && curr.right==null)
			return 0;
		else
		return 1+Math.max(curr.left==null?height(curr.right):0, curr.right==null?height(curr.left):0);
		
	}
	public int height(AvlNode curr) {
		if (curr==null)
			return 0;
		else 
			return curr.height;
	}
	class N {
		int data;
		N left, right, parent;
		int height, balance;
		
		N (int data){
			this.data = data;
			this.height = 0;
			this.balance = 0;
			left = right = parent = null;
		}
		
		@Override
		public String toString() {
	
			return Integer.toString(data);
		}
	}
	/**
	 * TreeTraversals 
	 */
	public void inOrder(AvlNode root){
		if(root == null){
            return;
        }
		
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
      
    }
	public void inOrder(N root){
		if(root == null){
            return;
        }
		
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
      
    }
	

}
