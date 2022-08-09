package nowcoder;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/fc533c45b73a41b0b44ccba763f866ef?orderByHotValue=1&questionTypes=000100&difficulty=00010&page=2&onlyReference=false
 * 来源：牛客网
 * <p>
 * [编程题]删除链表中重复的结点
 * 热度指数：224277时间限制：1秒空间限制：32768K
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
 * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 */
public class DeleteDuplicateInLinkedList {
    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null)
            return pHead;
        ListNode first = new ListNode(918);
        first.next = pHead;
        ListNode curr = pHead;
        ListNode last = first;

        while (curr != null && curr.next != null) {
            if (curr.val == curr.next.val) {
                int val = curr.val;

                while (curr != null && curr.val == val) curr = curr.next;

                last.next = curr;
            } else {

                last = curr;

                curr = curr.next;
            }
        }
        return first.next;
    }

    class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
