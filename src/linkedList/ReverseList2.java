package linkedList;

/**
 * Created by xu on 2017/6/1.
 * <p>
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
 * <p>
 * For example:
 * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
 * <p>
 * return 1->4->3->2->5->NULL.
 */

public class ReverseList2 {

    public static void main(String[] args) {
        ReverseList2 inst = new ReverseList2();

        ListNode head = inst.new ListNode(1);
        ListNode p1 = inst.new ListNode(2);
        ListNode p2 = inst.new ListNode(3);
        ListNode p3 = inst.new ListNode(4);
        ListNode p4 = inst.new ListNode(5);
        ListNode p5 = inst.new ListNode(6);
        ListNode p6 = inst.new ListNode(7);
        ListNode p7 = inst.new ListNode(8);
        ListNode p8 = inst.new ListNode(9);
        head.next = p1;
        p1.next = p2;
        p2.next = p3;
        p3.next = p4;
        p4.next = p5;
        p5.next = p6;
        p6.next = p7;
        p7.next = p8;
        p8.next = null;
        ListNode newHead = inst.reverse(head, 2, 4);
        inst.displayList(newHead);
    }

    public ListNode reverse(ListNode head, int m, int n) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0); // create a dummy node to mark the head of this list
        dummy.next = head;
        ListNode pre = dummy; // make a pointer pre as a marker for the node before reversing

        for (int i = 0; i < m - 1; i++) {
            pre = pre.next;
        }

        ListNode start = pre.next; // a pointer to the beginning of a sub-list that will be reversed
        ListNode then = start.next; // a pointer to a node that will be reversed

        // 1 - 2 -3 - 4 - 5 ; m=2; n =4 ---> pre = 1, start = 2, then = 3
        // dummy-> 1 -> 2 -> 3 -> 4 -> 5

        for (int i = 0; i < n - m; i++) {
            start.next = then.next;

//            displayList(pre); // 1->2->4->5->6->7->8->9->null; 1->3->2->5->6->7->8->9->null
//
//            displayList(start);// 2->4->5->6->7->8->9->null; 2->5->6->7->8->9->null
//
//            displayList(then);// 3->4->5->6->7->8->9->null;4->5->6->7->8->9->null
            then.next = pre.next;

//            displayList(pre);//1->2->4->5->6->7->8->9->null;1->3->2->5->6->7->8->9->null

//            displayList(start);//2->4->5->6->7->8->9->null; 2->5->6->7->8->9->null

//            displayList(then);//3->2->4->5->6->7->8->9->null;4->3->2->5->6->7->8->9->null

            pre.next = then;

//            displayList(pre);// 1->3->2->4->5->6->7->8->9->null; 1->4->3->2->5->6->7->8->9->null
//
//            displayList(start);// 2->4->5->6->7->8->9->null; 2->5->6->7->8->9->null
//
//            displayList(then);// 3->2->4->5->6->7->8->9->null; 4->3->2->5->6->7->8->9->null
            then = start.next;

//            displayList(pre);//1->3->2->4->5->6->7->8->9->null; 1->4->3->2->5->6->7->8->9->null
//
//            displayList(start);//2->4->5->6->7->8->9->null; 2->5->6->7->8->9->null
//
//            displayList(then);//4->5->6->7->8->9->null;5->6->7->8->9->null
        }

        // first reversing : dummy->1 - 3 - 2 - 4 - 5; pre = 1, start = 2, then = 4
        // second reversing: dummy->1 - 4 - 3 - 2 - 5; pre = 1, start = 2, then = 5 (finish)

        return dummy.next;
    }

    class ListNode {
        int val;
        ListNode next;

        public ListNode(int x) {
            this.val = x;
            this.next = null;
        }
    }

    public void displayList(ListNode head) {
        StringBuilder str = new StringBuilder();
        while (head != null) {
            str.append(head.val + "->");
            head = head.next;
        }
        str.append("null");
        System.out.println(str.toString());
    }
}
