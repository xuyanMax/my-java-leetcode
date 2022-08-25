package a_OA.nowcoder;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/d75c232a0405427098a8d1627930bea6?orderByHotValue=1&questionTypes=000100&difficulty=00010&page=4&onlyReference=false
 * 来源：牛客网
 * <p>
 * [编程题]sort-list
 * 热度指数：44697时间限制：1秒空间限制：32768K
 * 算法知识视频讲解
 * <p>
 * Sort a linked list in O(n log n) time using constant space complexity
 */
public class SortList {
    /**
     * 1）将待排序数组（链表）取中点并一分为二；
     * 2）递归地对左半部分进行归并排序；
     * 3）递归地对右半部分进行归并排序；
     * 4）将两个半部分进行合并（merge）,得到结果。
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode mid = findMiddle(head);
        mid.next = null;

        ListNode right = sortList(mid.next);
        ListNode left = sortList(head);

        return mergeTwoLists(left, right);

    }

    public ListNode findMiddle(ListNode head) {

        if (head == null) return null;

        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public ListNode mergeTwoLists(ListNode listNode1, ListNode listNode2) {
        if (listNode1 == null) return listNode2;
        if (listNode2 == null) return listNode1;

        ListNode dummy = new ListNode(918);
        ListNode curr = dummy;
        while (listNode1 != null && listNode2 != null) {
            if (listNode1.val < listNode2.val) {
                curr.next = new ListNode(listNode1.val);
                listNode1 = listNode1.next;

            } else {
                curr.next = new ListNode(listNode2.val);
                listNode2 = listNode2.next;
            }
            curr = curr.next;
        }

        if (listNode1.next != null)
            curr.next = listNode1;

        if (listNode2.next != null)
            curr.next = listNode2;

        return dummy.next;
    }

    class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
