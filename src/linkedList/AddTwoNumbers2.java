package linkedList;

import java.util.LinkedList;

/**
 * @author xu
 * <p>
 * You are given two non-empty linked lists representing two non-negative integers.
 * <p>
 * The most significant digit comes first and each of their nodes contain a single digit.
 * <p>
 * Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 * <p>
 * Example:
 * <p>
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 * <p>
 * <p>
 * https://leetcode.com/problems/add-two-numbers-ii/#/description
 */

public class AddTwoNumbers2 {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int v) {
            val = v;
            next = null;
        }
    }

    public ListNode addTwo(ListNode l1, ListNode l2) {

        LinkedList<Integer> stack1 = new LinkedList<>();
        LinkedList<Integer> stack2 = new LinkedList<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }

        int sum = 0;
        ListNode list = new ListNode(918);
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            if (!stack1.isEmpty()) {
                sum += stack1.pop();
            }
            if (!stack2.isEmpty()) {
                sum += stack2.pop();
            }
            list.val = sum % 10;
            ListNode newHead = new ListNode(sum / 10);
            newHead.next = list;
            list = newHead;
            sum /= 10;
        }
        if (list.val != 0) {
            return list;
        } else {
            return list.next;
        }
    }


}
