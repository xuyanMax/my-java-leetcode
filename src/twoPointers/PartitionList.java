package twoPointers;

/**
 *
 * @author xu
 *
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

	You should preserve the original relative order of the nodes in each of the two partitions.

	For example,
	Given 1->4->3->2->5->2 and x = 3,
	return 1->2->2->4->3->5.

 * https://leetcode.com/problems/partition-list/#/description
 */
public class PartitionList {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int v) {
            val = v;
            next = null;
        }
    }

    /**
     * @return newHead1
     * @newHead1, newHead2 are two lists used to concatenate the given list.
     * the first list is used to store those less than k and second for those larger than or equal to k.
     * Finally, attach these two lists and set the second list's end's next as null to avoid cyclic
     */
    public ListNode solution(ListNode head, int k) {
        PartitionList pl = new PartitionList();
        ListNode newHead1 = pl.new ListNode(911);
        ListNode newHead2 = pl.new ListNode(918);

        ListNode pointer1 = newHead1, pointer2 = newHead2;

        while (head != null) {
            if (head.val < k) {
                pointer1.next = head;
                pointer1 = pointer1.next;

            } else {
                pointer2.next = head;
                pointer2 = pointer2.next;
            }
            head = head.next;
        }
        pointer2.next = null;
        pointer1.next = newHead2.next;
        return newHead1.next;
    }

}
