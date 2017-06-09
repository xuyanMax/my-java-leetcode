package TwoPointers;

import TwoPointers.partitionList.listNode;

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
public class removeNthNodeFromEndOfList {

	public static void main(String[] args) {


	}
	class listNode {
		    int val;
		    listNode next;
		    listNode(int x) { val = x; next = null;}
		  }
	public static listNode solution (listNode head, int n) {
		
		removeNthNodeFromEndOfList instance = new removeNthNodeFromEndOfList();
		listNode newHead = instance.new listNode(918);
		newHead.next = head;
		listNode fast = newHead, slow = newHead;
		
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
