package stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InorderTraversal {

	public List<Integer> sol(TreeNode root){
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
	public List<Integer> sol2(TreeNode root) {
		List < Integer > res = new ArrayList < > ();
		LinkedList <TreeNode> stack = new LinkedList <TreeNode> ();
		TreeNode curr = root;
		while (curr != null || !stack.isEmpty()) {
			while (curr != null) {
				stack.push(curr);
				curr = curr.left;
			}
			curr = stack.pop();
			res.add(curr.val);
			curr = curr.right;
		}
		return res;
	}
	
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
