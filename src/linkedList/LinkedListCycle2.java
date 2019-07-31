package linkedList;

/**
 * @Author: xyx
 * @Date: 2019-07-27 12:19
 * @Version 1.0
 *
 * 142. Linked List Cycle II
 * Medium
 *
 * 1563
 *
 * 123
 *
 * Favorite
 *
 * Share
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 *
 * To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
 *
 * Note: Do not modify the linked list.
 */
public class LinkedListCycle2 {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                ListNode slow2 = head;
                while (slow2 != slow) {
                    slow = slow.next;
                    slow2 = slow2.next;
                }
                return slow;
            }
        }
        return null;
    }
    class ListNode {
        int val;
        ListNode next;

        public ListNode(int key) {
            val = key;
            next = null;
        }
    }
}
