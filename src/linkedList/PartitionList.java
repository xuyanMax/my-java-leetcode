package linkedList;

/**
 * @Author: xyx
 * @Date: 2019-07-26 22:41
 * @Version 1.0
 *
 * 86. Partition List
 *
 * Given a linked list and a value x, partition it such that all nodes less than x
 * come before nodes greater than or equal to x.
 *
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 * Example:
 *
 * Input: head = 1->4->3->2->5->2, x = 3
 * Output: 1->2->2->4->3->5
 */
public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        // use two lists to store thoes less than x and the other one for those larger than or equal to x
        // and finally, attach these two lists and return.
        // at last, set pointer2.next = null to avoid a cyclic loop.
        //5->6->1->2->3->null x = 3;
        // pointer1: 1->2--->3->null, pointer2: 5->6->3->null;
        // pointer1.next = newHead2.next;

        ListNode newHead1 = new ListNode(918);
        ListNode newHead2 = new ListNode(919);
        ListNode pointer1 = newHead1, pointer2 = newHead2;
        while (head != null) {
            if (head.val < x) {
                pointer1.next = head;
                pointer1 = head;
            } else {// those <= x
                pointer2.next = head;
                pointer2 = head;
            }

            head = head.next;
        }

        pointer1.next = newHead2.next;
        pointer2.next = null;
        return newHead1.next;

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
