package linkedList;
/**
 * 
 * @author xu
 * 
 *  For example,
	
	Given this linked list: 1->2->3->4->5
	
	For k = 2, you should return: 2->1->4->3->5
	
	For k = 3, you should return: 3->2->1->4->5
 *
 *	
 * https://leetcode.com/problems/reverse-nodes-in-k-group/#/description
 */
public class ReverseNodesInKGroups {

	public static void main(String[] args) {
		ReverseNodesInKGroups inst = new ReverseNodesInKGroups();

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
		inst.reverseKGroup2(head,4);

	}
	class listNode {
		int val;
		listNode next;
		public listNode(int key) {
			val = key;
			next = null;
		}
	}
	/**
	 * use two pointers pre and tail to locate the window of k listNodes
	 * when pre.next = tail a group of k reversed is done
	 * @param head
	 * @param k
	 * @return
	 */
	public listNode reverseKGroup(listNode head, int k) {
		if (head == null || head.next == null || k<2) 
			return head;
		listNode newHead = new listNode(918);
		newHead.next = head;
		listNode pre = newHead;
		listNode tail = newHead, temp;
		
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
	public listNode reverseKGroup2(listNode head, int k) {
		if (head == null || head.next == null || k < 2) 
			return head;
		// count the size of head list
		int count = 0;
		for (listNode pNode = head; pNode!=null;pNode = pNode.next) 
			count++;
		
		listNode newHead = new listNode(918);
		newHead.next = head;
		
		// do reverse for every k group 
		listNode pre = newHead, start;
		listNode tail = newHead;
		
		
		for (; count >= k; count -= k) {

		    int c = k;

		    while (c > 0 && tail!=null) {
		        tail = tail.next;
		        c--;
		    }
		    
		    head = pre.next;// head as a pointer for next cycle
		    
			while(pre.next != tail) {
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
	public listNode reverseKGroupRe(listNode head, int k) {
	    listNode curr = head;
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
	            listNode tmp = head.next; // tmp - next head in direct part
	            head.next = curr; // preappending "direct" head to the reversed list 
	            curr = head; // move head of reversed part to a new node
	            head = tmp; // move "direct" head to the next node in direct part
	        }
	        head = curr;
	    }
	    return head;
	}
	public void displayList(listNode head){
		StringBuilder str = new StringBuilder();
		while (head != null) {
			str.append(head.val+"->");
			head = head.next;
		}
		str.append("null");
		System.out.println(str.toString());
	}
}
