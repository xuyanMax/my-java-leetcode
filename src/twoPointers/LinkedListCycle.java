package twoPointers;

/**
 * 
 * @author xu
 * 
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

   Note: Do not modify the linked list.

 * https://leetcode.com/problems/linked-list-cycle-ii/#/description
 */
public class LinkedListCycle {
	
	class listNode {
		int val;
		listNode next;
		listNode(int key) {
			val =  key;
			next = null;
		}
	}
	public static void main(String[] args) {
		

	}
	
    // two pointers 
    // fast pointer increases by 2 and slow increases by 1.
    // when fast catches slow, it runs two times of distance than that of slow.
    // a+b+c+b = 2(a+b) => a = c
    // thus, head pointer moves when it meets with slow pointer in the node where the cycle begins
	public static listNode solution (listNode head) { 
		listNode slow = head, fast = head;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			if (fast == slow) {
				listNode newHead = head;
				while (newHead != slow) {
					slow = slow.next;
					newHead = newHead.next;
				}
				return slow;
			}	
		}
		return null;
				
				
	}

}
