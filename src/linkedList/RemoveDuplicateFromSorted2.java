package linkedList;

/**
 * @Author: xyx
 * @Date: 2019-07-26 15:47
 * @Version 1.0
 *
 * 83. Remove Duplicates from Sorted List
 *
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 *
 * Example 1:
 *
 * Input: 1->1->2
 * Output: 1->2
 * Example 2:
 *
 * Input: 1->1->2->3->3
 * Output: 1->2->3
 */
public class RemoveDuplicateFromSorted2 {
    public class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            if(head == null) return null;
            // ListNode startHead = new ListNode(1918);
            // startHead.next = head;
            ListNode point = head;
            while(point.next!=null) {
                if(point.val == point.next.val) {
                    point.next = point.next.next;

                } else point = point.next;
            }
            return head;
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
