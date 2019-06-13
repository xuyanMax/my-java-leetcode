package linkedList;

public class RotateList {

    public static void main(String[] args) {
        RotateList inst = new RotateList();
        ListNode head = inst.new ListNode(1);
        ListNode p1 = inst.new ListNode(2);
        ListNode p2 = inst.new ListNode(3);
        ListNode p3 = inst.new ListNode(4);
        ListNode p4 = inst.new ListNode(5);
        head.next = p1;
        p1.next = p2;
        p2.next = p3;
        p3.next = p4;
        p4.next = null;
        System.out.println(inst.displayList(inst.sol1(head, 2)));
    }

    /*
     * left and right pointers
     */
    public ListNode sol1(ListNode head, int n) {
        if (head == null || head.next == null || n == 0) {
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        ListNode newHead;
        for (int i = 0; i < n; i++) {
            if (fast.next == null) {
                fast = head;
            } else {
                fast = fast.next;
            }
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        fast.next = head; // will force loop
//	    newHead = slow.next;
//	    slow.next = null;
//	    return newHead;
        return head;
    }

    public ListNode sol2(ListNode head, int k) {
        if (head == null || k == 0)
            return head;
        ListNode right = head;
        ListNode left = head;

        int size = 0;
        while (right.next != null) {
            right = right.next;
            size++;
        }
        int n = k % size;

        right = head;
        while (n > 0) {
            right = right.next;
            n--;
        }
        while (right != null && right.next != null) {
            right = right.next;
            left = left.next;
        }
        right.next = head; // link to head
        ListNode newHead = left.next;
        left.next = null;// 这句不影响newHead...
        return newHead;

    }

    class ListNode {
        int val;
        ListNode next;

        public ListNode(int key) {
            val = key;
            next = null;
        }
    }

    public String displayList(ListNode head) {
        StringBuilder str = new StringBuilder();
//		int i=0;
        while (head != null) {
            str.append(head.val + "->");
//			System.out.println(i++);
            head = head.next;
        }
        str.append("null");
        return str.toString();
    }
}
