package linkedList;

public class ReverseList {

    class listNode {
        int val;
        listNode next;

        public listNode(int key) {
            val = key;
            next = null;
        }
    }

    public listNode reverse(listNode head) {

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
        listNode pre = null;
        while (head != null) {
            listNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;

    }

    public listNode reverseRecur(listNode head) {
        if (head == null || head.next == null)
            return head;
        listNode next = head.next;

        listNode newHead = reverseRecur(next);
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
    public listNode reverserRecur2(listNode head, listNode pre) {
        if (head == null || head.next == null)
            return head;
        else {
            listNode next = head.next;
            head.next = pre;

            listNode newHead = reverserRecur2(next, head);

            return newHead;
        }
    }

}
