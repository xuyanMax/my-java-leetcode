package linkedList;

/**
 * @Author: xyx
 * @Date: 2019-07-27 12:37
 * @Version 1.0
 *
 * 143. Reorder List
 * Medium
 *
 * 980
 *
 * 74
 *
 * Favorite
 *
 * Share
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 * Example 1:
 *
 * Given 1->2->3->4, reorder it to 1->4->2->3.
 * Example 2:
 *
 * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 */
public class ReorderList {
    public void reorderList(ListNode head) {

        if (head == null || head.next == null)
            return;
        ListNode fast = head;
        ListNode slow = head;
        // step 1:找到list的中间节点
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        //step 2: reverse the half list 1-2-3-4-5-6 -> 1-2-3-5-4-6 -> 1-2-3-6-5-4
        ListNode pm = slow;//3
        ListNode pc = slow.next;//4
        while (pc.next != null) {
            ListNode curr = pc.next; //5
            pc.next = curr.next; //4->6
            curr.next = pm.next; // 5->4-->6
            pm.next = curr; // 3->5--4--6

        }
        // step: link one by one 1-2-3-6-5-4 —> 1-6-2-3-5-4--> 1-6-2-5-3-4
        //
        ListNode p1 = head;
        ListNode p2 = pm.next; //指向要调整到前的节点
        while (p1 != pm) {
            pm.next = p2.next;
            p2.next = p1.next;
            p1.next = p2;
            p1 = p2.next;
            p2 = pm.next;

        }
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
