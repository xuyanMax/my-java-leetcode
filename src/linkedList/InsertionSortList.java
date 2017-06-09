package linkedList;

public class InsertionSortList {
	public ListNode insertionSortList (ListNode head){
		if( head == null ){
			return head;
		}
		
		ListNode helper = new ListNode(0); //new starter of the sorted list
		ListNode cur = head; //the node will be inserted
		ListNode pre = helper; //insert node between pre and pre.next
		ListNode next = null; //the next node will be inserted
		//not the end of input list
		while( cur != null ){
			next = cur.next;
			//find the right place to insert
			while( pre.next != null && pre.next.val < cur.val ){
				pre = pre.next;
			}
			//insert between pre and pre.next
			cur.next = pre.next;
			pre.next = cur;
			pre = helper;
			cur = next;
		}
		
		return helper.next;
	}

	public static void main(String[] args) {
		
		InsertionSortList inst = new InsertionSortList();
		
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
