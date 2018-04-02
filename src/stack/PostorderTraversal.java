package stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PostorderTraversal {

	public static void main(String[] args) {
		

	}
	public List<Integer> solution(TreeNode root){
	
		List<Integer> ret = new ArrayList<>();
		if (root == null)
			return ret;
		LinkedList<TreeNode> stack = new LinkedList<>();
		LinkedList<TreeNode> stack2 = new LinkedList<>();
		stack.push(root);
		
		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			
			if (node.left != null)
				stack.push(node.left);
			if (node.right != null)
				stack.push(node.right);
			
			stack2.push(node);
		}
		while (!stack2.isEmpty())
			ret.add(stack2.pop().val);


		return ret;
	}
	
	

}
 