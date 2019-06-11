package linkedList;

/**
 * @author xu
 * <p>
 * For example,
 * <p>
 * Given this linked list: 1->2->3->4->5
 * <p>
 * For k = 2, you should return: 2->1->4->3->5
 * <p>
 * For k = 3, you should return: 3->2->1->4->5
 * <p>
 * <p>
 * https://leetcode.com/problems/reverse-nodes-in-k-group/#/description
 */
public class ReverseNodesInKGroups {

    public static void main(String[] args) {
        ReverseNodesInKGroups inst = new ReverseNodesInKGroups();

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
        inst.reverseKGroup2(head, 4);

    }

    class ListNode {
        int val;
        ListNode next;

        public ListNode(int key) {
            val = key;
            next = null;
        }
    }

    /**
     * use two pointers pre and tail to locate the window of k ListNodes
     * when pre.next = tail a group of k reversed is done
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k < 2)
            return head;
        ListNode newHead = new ListNode(918);
        newHead.next = head;
        ListNode pre = newHead;
        ListNode tail = newHead, temp;

        newHead.next = head;
        while (true) {
//			for (int i=1; i<k; i++) {
//				tail = tail.next;
//			}
            int count = k;
            while (count > 0 && tail != null) {
                tail = tail.next;
                count--;
            }

            if (tail == null) {
                break;// reached the end of list
            }

            head = pre.next; // head as a pointer for next cycle
            while (pre.next != tail) {
                temp = pre.next;
                pre.next = temp.next;
                temp.next = tail.next;
                tail.next = temp;
            }
            tail = head;
            pre = head;
        }

        return newHead.next;


    }

    public ListNode reverseKGroup2(ListNode head, int k) {
        if (head == null || head.next == null || k < 2)
            return head;
        // count the size of head list
        int count = 0;
        for (ListNode pNode = head; pNode != null; pNode = pNode.next)
            count++;

        ListNode newHead = new ListNode(918);
        newHead.next = head;

        // do reverse for every k group
        ListNode pre = newHead, start;
        ListNode tail = newHead;


        for (; count >= k; count -= k) {

            int c = k;

            while (c > 0 && tail != null) {
                tail = tail.next;
                c--;
            }

            head = pre.next;// head as a pointer for next cycle

            while (pre.next != tail) {
                // pre stays the same
                start = pre.next; //start NODE changes everytime; but sub-list chanegs
                pre.next = start.next;

                start.next = tail.next;
                displayList(head);
                tail.next = start;//tail node NOT change but sub-list changes

                displayList(pre);//918->2->3->1->4->5->6->7->8->9->null;918->3->2->1->4->5->6->7->8->9->null
                displayList(head);
            }

            tail = head;
            pre = head;
        }

        return newHead.next;
    }

    // recursive way
    // https://discuss.leetcode.com/topic/7126/short-but-recursive-java-code-with-comments
    public ListNode reverseKGroupRe(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;
        while (curr != null && count != k) { // find the k+1 node
            curr = curr.next;
            count++;
        }
        if (count == k) { // if k+1 node is found
            curr = reverseKGroupRe(curr, k); // reverse list with k+1 node as head
            // head - head-pointer to direct part,
            // curr - head-pointer to reversed part;
            while (count-- > 0) { // reverse current k-group:
                ListNode tmp = head.next; // tmp - next head in direct part
                head.next = curr; // preappending "direct" head to the reversed list
                curr = head; // move head of reversed part to a new node
                head = tmp; // move "direct" head to the next node in direct part
            }
            head = curr;
        }
        return head;
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
