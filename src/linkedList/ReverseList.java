package linkedList;

public class ReverseList {

    class ListNode {
        int val;
        ListNode next;

        public ListNode(int key) {
            val = key;
            next = null;
        }
    }

    public ListNode reverse(ListNode head) {

        // head->1->2->3->null
        // 1 head -> null
        //	  h	     pre
        // 2 1->head->null
        //	  h	 pre
        // 3 2->1->head->null
        //   h  pre
        // null 3->2->1->head->null
        // head pre
        // return pre
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;

    }

    public ListNode reverseRecur(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode next = head.next;

        ListNode newHead = reverseRecur(next);
        next.next = head;
        head.next = null;

        return newHead; // always return the last element as the reversed list new head

    }

    /**
     * @param head
     * @param pre
     * @return
     * @input head, null
     */
    public ListNode reverserRecur2(ListNode head, ListNode pre) {
        if (head == null || head.next == null)
            return head;
        else {
            ListNode next = head.next;
            head.next = pre;

            ListNode newHead = reverserRecur2(next, head);

            return newHead;
        }
    }

}
