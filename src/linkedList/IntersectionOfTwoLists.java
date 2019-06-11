package linkedList;

public class IntersectionOfTwoLists {

    /*
     * 1、get length difference
     * 2、align two lists from the tail
     * 3、find out the intersection
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int len1 = 0, len2 = 0;
        ListNode point = headA;
        ListNode pointA = headA, pointB = headB;
        while (point != null) {
            point = point.next;
            len1++;
        }
        point = headB;
        while (point != null) {
            point = point.next;
            len2++;
        }
        if (len1 > len2) {
            while (len1 > len2) {
                pointA = pointA.next;
                len1--;
            }
        } else {
            while (len1 < len2) {
                pointB = pointB.next;
                len2--;
            }
        }
        while (pointA.val != pointB.val) {
            pointA = pointA.next;
            pointB = pointB.next;
        }
        return pointA;

    }

    /*
     * Really  smart
     * reference:
     * https://discuss.leetcode.com/topic/28067/java-solution-without-knowing-the-difference-in-len/73
     *
     * a+c+b+c = b+c+a+c
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        ListNode a = headA;
        ListNode b = headB;

        while (a != b) {// can't use a.val != b.val...
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
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
