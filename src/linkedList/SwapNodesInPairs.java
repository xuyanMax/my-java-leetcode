package linkedList;


/**
 * @author xu
 * <p>
 * Given a linked list, swap every two adjacent nodes and return its head.
 * <p>
 * For example,
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * <p>
 * https://leetcode.com/problems/swap-nodes-in-pairs/#/description
 */
public class SwapNodesInPairs {

    public static void main(String[] args) {
        SwapNodesInPairs inst = new SwapNodesInPairs();

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
        ListNode newHead = inst.swap(head);
        System.out.println(inst.displayList(newHead));

    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int key) {
            val = key;
            next = null;
        }
    }

    public ListNode swap(ListNode head) {

        SwapNodesInPairs instance = new SwapNodesInPairs();
        ListNode newHead = new ListNode(918);
        newHead.next = head;
        ListNode pointer = newHead;

        while (pointer.next != null && pointer.next.next != null) {

            ListNode first = pointer.next;
            ListNode second = pointer.next.next;

            first.next = second.next;

            System.out.println(displayList(pointer));
            System.out.println(displayList(first));
            System.out.println(displayList(second));

            pointer.next = second;
            System.out.println(displayList(pointer));
            pointer.next.next = first; //second.next = first
            System.out.println(displayList(pointer));
            pointer = pointer.next.next;// first;
            System.out.println(displayList(pointer));
        }
        return newHead.next;
    }

    public String displayList(ListNode head) {
        StringBuilder str = new StringBuilder();
        while (head != null) {
            str.append(head.val + "->");
            head = head.next;
        }
        str.append("null");
        return str.toString();
    }

}
