package stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InorderTraversal {

	public static void main(String[] args) {


	}
	public List<Integer> solution(TreeNode root){
		List<Integer> ret = new ArrayList<>();
		if (root == null)
			return ret;
		LinkedList<TreeNode> stack = new LinkedList<>();
		TreeNode node = root;
		
		while(true) {
			if (node != null) { // dive all the way to the left child of root
				stack.push(node);
				node =  node.left;
			}else { // if no left child; pop u out of stack and dive to the right child of u
				if (stack.isEmpty())
					break;// last rightmost node
				node = stack.pop();
				ret.add(node.val);
				node = node.right;
				
			}
		}
		return ret;
	}
	
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
