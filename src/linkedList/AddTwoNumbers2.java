package linkedList;
import java.util.LinkedList;

/**
 * 
 * @author xu
 * 
 * You are given two non-empty linked lists representing two non-negative integers. 
 * 
 * The most significant digit comes first and each of their nodes contain a single digit. 
 * 
 * Add the two numbers and return it as a linked list.

	You may assume the two numbers do not contain any leading zero, except the number 0 itself.
	
	Follow up:
	What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
	
	Example:
	
	Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
	Output: 7 -> 8 -> 0 -> 7
	
 * 
 * https://leetcode.com/problems/add-two-numbers-ii/#/description
 * 
 */

public class AddTwoNumbers2 {

	public static void main(String[] args) {
	
	}
	class listNode{
		int val;
		listNode next;
		listNode(int v) {
			val = v;
			next = null;
		}
	}
	public listNode addTwo(listNode l1, listNode l2) {
		
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
        listNode list = new listNode(918);
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            if (!stack1.isEmpty()){
                sum += stack1.pop();
            }
            if (!stack2.isEmpty()){
                sum += stack2.pop();
            }
            list.val = sum % 10; 
            listNode newHead = new listNode(sum / 10);
            newHead.next = list;
            list = newHead;
            sum /= 10;
        }
        if (list.val != 0)
        	return list;
        else 
        	return list.next;
	}
		

}
