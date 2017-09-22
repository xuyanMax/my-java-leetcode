package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author xu
 * 
 * https://leetcode.com/submissions/detail/86918339/
 */
public class BinaryTreePath {

	public static void main(String[] args) {
		

	}
	static List<String> solution(AvlNode root){
		List<String> paths = new ArrayList<>();
		search(root, new String(), paths);
		return paths;
	}
	static void search (AvlNode root, String path, List<String> paths) {
		if (root.left == null && root.right == null)
			paths.add(path + root.data);
		if (root.left != null)
			search(root.left, path + root.data + "->", paths);
		if (root.right != null)
			search(root.right, path + root.data + "->", paths);
		
	}
	
	
	 class TreeNode{
		   int val;
		   TreeNode left;
		   TreeNode right;
		   TreeNode(int x) { val = x; }
		
	}

}
