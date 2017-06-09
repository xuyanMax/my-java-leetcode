package tree;

/**
 * 
 * @author xu
 *
 * http://www.geeksforgeeks.org/check-if-two-trees-are-mirror/
 */
public class areMirror {

	public static void main(String[] args) {
	

	}
	static boolean areMirr(AvlNode rootA, AvlNode rootB) {
		if (rootA==null || rootB==null)
			return rootA==rootB;
		if (rootA.data == rootB.data)
			return areMirr(rootA.right, rootB.left) && areMirr(rootA.left, rootB.right);
		
		return false;
		
	}
}
