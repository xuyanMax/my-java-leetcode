package linkedList;

/**
 * @author xu
 * <p>
 * You are given two non-empty linked lists representing two non-negative integers.
 * <p>
 * The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * <p>
 * https://leetcode.com/problems/add-two-numbers/#/description
 */

public class AddTwoNumbers {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int v) {
            val = v;
            next = null;
        }
    }

    public ListNode addTwo(ListNode l1, ListNode l2) {

        int sum = 0;
        int carry = 0;

        // two pointers for l1 and l2
        ListNode p1 = l1, p2 = l2;

        // a pointer for newHead
        ListNode newHead = new ListNode(918);
        ListNode pointer = newHead;
//		addTwoNumbers inst = new addTwoNumbers();

        while (p1 != null && p2 != null) {
            sum = p1.val + p2.val + carry;
            if (sum >= 10) {
                carry = 1;
                sum -= 10;
            } else {
                carry = 0;
            }
            pointer.next = new ListNode(sum);
            p1 = p1.next;
            p2 = p2.next;
            pointer = pointer.next;
            sum = 0;
        }

        while (p1 != null) {
            sum = carry + p1.val;
            if (sum >= 10) {
                carry = 1;
                sum -= 10;
            } else {
                carry = 0;
            }
            pointer.next = new ListNode(sum);
            pointer = pointer.next;
            p1 = p1.next;
        }
        while (p2 != null) {
            sum = carry + p2.val;
            if (sum >= 10) {
                carry = 1;
                sum -= 10;
            } else {
                carry = 0;
            }
            pointer.next = new ListNode(sum);
            pointer = pointer.next;
            p2 = p2.next;
        }
        if (carry == 1) {
            pointer.next = new ListNode(carry);
        }
        return newHead.next;

    }

    public ListNode addTwoSimplified(ListNode l1, ListNode l2) {
        int sum = 0;

        // two pointers for l1 and l2
        ListNode p1 = l1, p2 = l2;

        // a pointer for newHead
        ListNode newHead = new ListNode(918);
        ListNode pointer = newHead;
//		addTwoNumbers inst = new addTwoNumbers();

        while (p1 != null || p2 != null) {

            if (p1 != null) {
                sum += p1.val;
            }
            if (p2 != null) {
                sum += p2.val;
            }
            pointer.next = new ListNode(sum % 10);
            pointer = pointer.next;
            sum /= 10; // get carry only
        }
        return newHead.next;
    }

}
