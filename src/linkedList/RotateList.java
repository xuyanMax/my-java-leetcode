package linkedList;

public class RotateList {

     public static void main(String[] args){
         RotateList inst = new RotateList();
         listNode head = inst.new listNode(1);
         listNode p1 = inst.new listNode(2);
         listNode p2 = inst.new listNode(3);
         listNode p3 = inst.new listNode(4);
         listNode p4 = inst.new listNode(5);
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
	public listNode sol1(listNode head, int n){
		if (head == null || head.next == null || n == 0) {
	         return head;
	    }
	    listNode fast = head;
	    listNode slow = head;
	    listNode newHead;
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

	/*
	 *
	 */
	public listNode sol2(listNode head, int k){
		if (head == null || k == 0)
			return head;
		listNode right = head;
		listNode left = head;

		int size = 0;
		while (right.next != null){
			right = right.next;
			size++;
		}
		int n = k % size;

		right = head;
		while (n>0) {
			right = right.next;
			n--;
		}
		while (right != null && right.next != null) {
			right = right.next;
			left = left.next;
		}
		right.next = head; // link to head
		listNode newHead = left.next;
		left.next = null;// 这句不影响newHead...
		return newHead;

	}
	class listNode {
		int val;
		listNode next;
		public listNode(int key) {
			val = key;
			next = null;
		}
	}
	public String displayList(listNode head){
		StringBuilder str = new StringBuilder();
//		int i=0;
		while (head != null) {
			str.append(head.val+"->");
//			System.out.println(i++);
			head = head.next;
		}
		str.append("null");
		return str.toString();
	}
}
