package linkedList;

// Sort a linked list in O(n log n) time using constant space complexity.
public class MergeSortList {

    public ListNode sortList(ListNode head) {

        if (head == null || head.next == null)
            return head;
        // step 1. split the list into two sub-lists
        ListNode fast = head, slow = head;

        // used to split the former list by setting the middle list's next as null
        ListNode pre = null;

        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        pre.next = null;

        // step 2: sort each half
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);

        // step 3: merge the returned sub-lists l1 and l2
        return merge(l1, l2);

    }

    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode newHead = new ListNode(918);
        ListNode p = newHead;

        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                p.next = l2;
                l2 = l2.next;
            } else {
                p.next = l1;
                l1 = l1.next;
            }
            p = p.next;
        }
        if (l1 != null)
            p.next = l1;

        if (l2 != null)
            p.next = l2;

        return newHead.next;
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
