package stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PreorderTraversal {

	public static void main(String[] args) {
	

	}

	public List<Integer> solution(TreeNode root){
		
		List<Integer> ret = new ArrayList<>();
		if (root == null)
			return ret;
		LinkedList<TreeNode> stack = new LinkedList<>();
		
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			ret.add(node.val);
			
			if (node.right != null)
				stack.push(node.right);
			if (node.left != null)
				stack.push(node.left);
		}
		
		return ret;
	}

}
 

