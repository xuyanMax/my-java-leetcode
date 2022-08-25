package a_OA.nowcoder;

/**
 * [编程题]从尾到头打印链表
 * 热度指数：693619时间限制：1秒空间限制：32768K
 * 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
 */
public class OutputLinkedListFromTail {

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 堆栈
     *
     * @param head
     * @return
     */
    public ListNode printListFromTailToHeadSol1(ListNode head) {
        if (head == null)
            return head;
        ListNode pre = null;

        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;

        }
        return pre;
    }

    /**
     * 递归算法，本质也是堆栈
     *
     * @param head
     * @return
     */
    public ListNode printListFromTailToHeadSol2(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode next = head.next;
        ListNode newHead = printListFromTailToHeadSol2(next);
        next.next = head;
        head.next = null;
        return newHead;
    }
}
