package twoPointers;

/**
 * 
 * @author xu
 * 
 * 
 * Given a linked list, remove the nth node from the end of list and return its head.

   For example,

   Given linked list: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked list becomes 1->2->3->5.
 * 
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/#/description
 */
public class RemoveNthNodeFromEndOfList {

	public static void main(String[] args) {


	}
	class ListNode {
		    int val;
		    ListNode next;
		    ListNode(int x) { val = x; next = null;}
		  }
	public static ListNode solution (ListNode head, int n) {
		
		RemoveNthNodeFromEndOfList instance = new RemoveNthNodeFromEndOfList();
		ListNode newHead = instance.new ListNode(918);
		newHead.next = head;
		ListNode fast = newHead, slow = newHead;
		
		while (fast.next != null) {
			if (n <= 0) {
				slow = slow.next;
			}
			fast = fast.next;
			
		}
		slow.next = slow.next.next;
		return newHead.next;
	}

}
