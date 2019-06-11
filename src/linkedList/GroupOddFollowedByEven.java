package linkedList;

public class GroupOddFollowedByEven {
    public static void main(String[] args) {
        GroupOddFollowedByEven inst = new GroupOddFollowedByEven();

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

        // in-place sort

        head = inst.oddEvenList(head);

        inst.displayList(head);

    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode even = head.next;
        ListNode odd = head;
        ListNode evenHead = even;

        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        odd.next = evenHead;

        //显示以evenhead为首的序列
        System.out.println(displayList(evenHead));

        return head;

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

    public ListNode oddEvenList_TLE(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode even = head.next;
        ListNode odd = head;
        ListNode newHead = new ListNode(918);
        ListNode p = newHead;

        while (odd != null && odd.next.next != null) {
            p.next = odd;
            odd = odd.next.next;
            p = p.next;
        }
        while (even != null && even.next.next != null) {
            p.next = even;
            even = even.next.next;
            p = p.next;
        }
        return newHead.next;
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
