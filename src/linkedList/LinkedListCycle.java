package linkedList;

/**
 * @Author: xyx
 * @Date: 2019-07-27 12:11
 * @Version 1.0
 *
 * 141. Linked List Cycle
 *
 * Given a linked list, determine if it has a cycle in it.
 *
 * To represent a cycle in the given linked list,
 * we use an integer pos which represents the position (0-indexed) in the linked list
 * where tail connects to. If pos is -1, then there is no cycle in the linked list.
 *
 *
 *
 * Example 1:
 *
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 */
public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        // increment fast pointer by two steps each and slow pointer by one step each
        // if then fast == slow, it is cyclic; otherwise, it is not.
        // because, if the LinkList has a cycle, fast and slow will meet at some point
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;// if fast points to null, then there must be no loop
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
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
