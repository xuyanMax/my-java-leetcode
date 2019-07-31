package linkedList;

/**
 * @Author: xyx
 * @Date: 2019-07-27 11:57
 * @Version 1.0
 */
public class RemoveNthNodeFromEndofList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // ListNode headNode = new ListNode(123);
        // headNode.next = head;
        // ListNode fastNode = headNode;
        // ListNode slowNode = headNode;

        // while (fastNode.next != null) {
        //     if (n<=0) {
        //         slowNode = slowNode.next;
        //     }
        //     else{
        //         n--;
        //     }
        //     fastNode = fastNode.next;
        // }
        // slowNode.next = slowNode.next.next;

        // return headNode.next;

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

    class ListNode {
        int val;
        ListNode next;

        public ListNode(int x) {
            this.val = x;
            this.next = null;
        }
    }

}
