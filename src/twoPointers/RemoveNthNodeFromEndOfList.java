package twoPointers;

/**
 * @author xu
 * <p>
 * <p>
 * Given a linked list, remove the nth node from the end of list and return its head.
 * <p>
 * For example,
 * <p>
 * Given linked list: 1->2->3->4->5, and n = 2.
 * <p>
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * <p>
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/#/description
 */
public class RemoveNthNodeFromEndOfList {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static ListNode solution(ListNode head, int n) {

        RemoveNthNodeFromEndOfList instance = new RemoveNthNodeFromEndOfList();
        ListNode newHead = instance.new ListNode(918);
        newHead.next = head;
        ListNode fast = newHead, slow = newHead;

        while (fast.next != null) {
            if (n <= 0) {
                slow = slow.next;
            } else {
                n--;
            }
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return newHead.next;
    }

    public ListNode sol2(ListNode head, int n) {
        // no dummy node
        // two pointers h1 (fast) and h2 (slow)
        // if n happens to be the
        ListNode h1 = head, h2 = head;
        while (n-- > 0) {
            h1 = h1.next;
        }
        if (h1 == null) return head.next;

        while (h1 != null) {
            h1.next = h1;
            h2.next = h2;
        }
        h2.next = h2.next.next;
        return head;
    }

}
