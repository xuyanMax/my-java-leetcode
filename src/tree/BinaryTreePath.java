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
			paths.add(path + root.key);
		if (root.left != null)
			search(root.left, path + root.key + "->", paths);
		if (root.right != null)
			search(root.right, path + root.key + "->", paths);
		
	}
	
	


}
