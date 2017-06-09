package linkedList;


public class ConvertSortedListToBST {
	public static void main(String[] args){
		ConvertSortedListToBST inst = new ConvertSortedListToBST();
		
		ListNode head = inst.new ListNode(1);
		ListNode p1 = inst.new ListNode(2);
		ListNode p2 = inst.new ListNode(3);
		ListNode p3 = inst.new ListNode(4);
		ListNode p4 = inst.new ListNode(5);
		ListNode p5 = inst.new ListNode(6);
		ListNode p6 = inst.new ListNode(7);
		ListNode p7 = inst.new ListNode(8);
		ListNode p8 = inst.new ListNode(9);
		head.next = p1;
		p1.next = p2;
		p2.next = p3;
		p3.next = p4;
		p4.next = p5;
		p5.next = p6;
		p6.next = p7;
		p7.next = p8;
		p8.next = null;
	}
	private ListNode node;
	/**
	 * 利用中序遍历
	 * 1、先确定链表长度
	 * 2、
	 */
	public TreeNode sortedListToBST(ListNode head) {
		if(head == null){
			return null;
		}
		
		int size = 0;
		ListNode runner = head;
		node = head;
		
		while(runner != null){
			runner = runner.next;
			size ++;
		}
		
		return inorderHelper(0, size - 1);
	}

	public TreeNode inorderHelper(int start, int end){
		if(start > end){
			return null;
		}
		
		int mid = start + (end - start) / 2;
		TreeNode left = inorderHelper(start, mid - 1);
		
		TreeNode treenode = new TreeNode(node.val);
		treenode.left = left;
		node = node.next;

		TreeNode right = inorderHelper(mid + 1, end);
		treenode.right = right;
		
		return treenode;
	}
	class ListNode {
		int val;
		ListNode next;
		public ListNode(int key) {
			val = key;
			next = null;
		}
	}
	class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){
			val = x;		
		}
	}

}
