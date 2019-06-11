package linkedList;


/**
 * @author xu
 * <p>
 * Given a singly linked list, determine if it is a palindrome.
 * <p>
 * https://leetcode.com/problems/palindrome-linked-list/#/description
 * <p>
 * two pointers fast and slow
 * when fast reaches the end of list, slow reaches the middle.
 * <p>
 * then reverse the remaining list starting at slow pointer and compare each step with the head pointer.
 * <p>
 * slow will finally reach the null pointer
 */
public class PalindromeLinkedList {

    class ListNode {
        int val;
        ListNode next;

        public ListNode(int key) {
            val = key;
            next = null;
        }
    }

    // fast
    public boolean solution(ListNode head) {

        ListNode fast = head, slow = head;

        while (fast != null && fast.next != null) { //fast must reach the end or null; 2nd last is NOT allowed
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

    public ListNode reverse(ListNode head) {

        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;

            head.next = pre;
            pre = head;

            head = next;

        }
        return pre;
    }


}
