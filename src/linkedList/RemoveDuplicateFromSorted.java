package linkedList;

/**
 * @Author: xyx
 * @Date: 2019-07-26 15:47
 * @Version 1.0
 * <p>
 * 83. Remove Duplicates from Sorted List
 * <p>
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 * <p>
 * Example 1:
 * <p>
 * Input: 1->1->2
 * Output: 1->2
 * Example 2:
 * <p>
 * Input: 1->1->2->3->3
 * Output: 1->2->3
 */
public class RemoveDuplicateFromSorted {
    public class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null) {
                return null;
            }

            ListNode newHead = new ListNode(918);
            newHead.next = head;
            ListNode pre = newHead, fast = head;

            while (fast != null) {
                while (fast != null && fast.next != null && fast.val == fast.next.val) {
                    fast = fast.next;
                }
                if (pre.next != fast)
                    pre.next = fast.next;
                else // pre.next = fast说明pre-fast之间没有重复元素出现，
                    pre = pre.next;
                fast = fast.next;
            }
            return newHead.next;
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
