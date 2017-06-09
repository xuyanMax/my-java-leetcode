package linkedList;

public class DeleteNode {
	  /*
	   * Since we couldn't enter the preceding node, we can not delete the given node. 
	   * We can just copy the next node to the given node and delete the next one.
	   * 
	   */
	public void deleteNode(ListNode node) {
	        node.val = node.next.val;
	        node.next = node.next.next;
	  }
	
	class ListNode{
		int val;
		ListNode next;
		ListNode (int x) {
			val = x;
		}
	}
	
}
