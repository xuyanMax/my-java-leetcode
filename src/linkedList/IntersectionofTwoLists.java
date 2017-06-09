package linkedList;

public class IntersectionofTwoLists {
	
	/*
	 * 1、get length difference
	 * 2、align two lists from the tail
	 * 3、find out the intersection
	 */
	public listNode getIntersectionNode(listNode headA, listNode headB){
		int len1=0, len2=0;
		listNode point = headA;
		listNode pointA = headA, pointB = headB;
		while (point != null){
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
		}else {
			while (len1 < len2) {
				pointB = pointB.next;
				len2--;
			}
		}
		while ( pointA.val != pointB.val){
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
	public listNode getIntersectionNode2(listNode headA, listNode headB){
		if(headA == null || headB == null)
			return null;
		listNode a = headA;
		listNode b = headB;
		
		while (a != b){// can't use a.val != b.val...
			a = a==null?headB:a.next;
			b = b==null?headA:b.next;
		}
		return a;
	}
	class listNode {
		int val;
		listNode next;
		public listNode(int key) {
			val = key;
			next = null;
		}
	}
}
