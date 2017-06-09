package linkedList;


/**
 * 
 * @author xu
 * 
 * Given a linked list, swap every two adjacent nodes and return its head.

	For example,
	Given 1->2->3->4, you should return the list as 2->1->4->3.
	
 * https://leetcode.com/problems/swap-nodes-in-pairs/#/description
 */
public class SwapNodesInPairs {

	public static void main(String[] args) {
	    SwapNodesInPairs inst = new SwapNodesInPairs();
	    
        listNode head = inst.new listNode(1);
        listNode p1 = inst.new listNode(2);
        listNode p2 = inst.new listNode(3);
        listNode p3 = inst.new listNode(4);
        listNode p4 = inst.new listNode(5);
        listNode p5 = inst.new listNode(6);
        listNode p6 = inst.new listNode(7);
        listNode p7 = inst.new listNode(8);
        listNode p8 = inst.new listNode(9);
        head.next = p1;
        p1.next = p2;
        p2.next = p3;
        p3.next = p4;
        p4.next = p5;
        p5.next = p6;
        p6.next = p7;
        p7.next = p8;
        p8.next = null;
        listNode newHead = inst.swap(head);
        System.out.println(inst.displayList(newHead));

	}
	class listNode{
		int val;
		listNode next;
		listNode (int key) {
			val = key;
			next = null;
		}
	}
	public listNode swap (listNode head) {
	
		SwapNodesInPairs instance = new SwapNodesInPairs();
		listNode newHead = new listNode(918);
		newHead.next = head;
		listNode pointer = newHead;
		
		while (pointer.next != null && pointer.next.next != null) {
			
			listNode first = pointer.next;
			listNode second = pointer.next.next;
			
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
	public String displayList(listNode head){
		StringBuilder str = new StringBuilder();
		while (head != null) {
			str.append(head.val+"->");
			head = head.next;
		}
		str.append("null");
		return str.toString();
	}

}
