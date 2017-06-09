package linkedList;


/**
 * 
 * @author xu
 * 
 * Given a singly linked list, determine if it is a palindrome.
 * 
 * https://leetcode.com/problems/palindrome-linked-list/#/description
 * 
 * two pointers fast and slow 
 * when fast reaches the end of list, slow reaches the middle.
 * 
 *  then reverse the remaining list starting at slow pointer and compare each step with the head pointer.
 *  
 *  slow will finally reach the null pointer 
 * 
 * 
 */
public class PalindromeLinkedList {

	public static void main(String[] args) {
	

	}
	
	class listNode {
		int val;
		listNode next;
		public listNode(int key) {
			val = key;
			next = null;
		}
	}
	// fast 
	public boolean solution(listNode head) {
		
		listNode fast = head, slow = head;
		
		while (fast!=null && fast.next!=null) { //fast must reach the end or null; 2nd last is NOT allowed
			fast = fast.next.next;
			slow = slow.next;
		}
		slow = reverse(slow);
		while (slow != null && slow.val == head.val) {
			slow = slow.next;
			head = head.next;
		}
		return slow == null;
	}
	
	public listNode reverse(listNode head) {
		
		listNode pre = null;
		while (head != null) {
			listNode next = head.next;
			
			head.next = pre;
			pre = head;
			
			head = next;
			
		}
		return pre;
	}
	

}
