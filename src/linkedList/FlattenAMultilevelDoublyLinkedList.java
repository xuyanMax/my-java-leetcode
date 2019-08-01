package linkedList;

/**
 * @Author: xyx
 * @Date: 2019-07-27 12:43
 * @Version 1.0
 *
 * 430. Flatten a Multilevel Doubly Linked List
 * Medium
 *
 * 392
 *
 * 67
 *
 * Favorite
 *
 * Share
 * You are given a doubly linked list which in addition to the next and previous pointers,
 * it could have a child pointer, which may or may not point to a separate doubly linked list.
 * These child lists may have one or more children of their own, and so on,
 * to produce a multilevel data structure, as shown in the example below.
 *
 * Flatten the list so that all the nodes appear in a single-level, doubly linked list.
 * You are given the head of the first level of the list.
 *
 *
 *
 * Example:
 *
 * Input:
 *  1---2---3---4---5---6--NULL
 *          |
 *          7---8---9---10--NULL
 *              |
 *              11--12--NULL
 *
 * Output:
 * 1-2-3-7-8-11-12-9-10-4-5-6-NULL
 */
public class FlattenAMultilevelDoublyLinkedList {
    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node() {
        }

        public Node(int _val, Node _prev, Node _next, Node _child) {
            val = _val;
            prev = _prev;
            next = _next;
            child = _child;
        }
    }

    public Node flatten(Node head) {
        if(head == null) return null;
        Node p = head;

        while (p != null) {
            if (p.child == null) {
//                p.next = head;
//                p.next.prev = p;
//                p = p.next;
//                head = head.next;
                p = p.next;
            } else {
                Node tmp = p.child;
                // find tail of child chain
                while (tmp.next != null)
                    tmp = tmp.next;
                // connect tail with p.next
                tmp.next = p.next;
                if (p.next != null)
                    p.next.prev = p;
                // connect p with child and reset child
                p.next = p.child;
                p.child = null;
                p.next.prev = p;
                p = p.next;
            }
        }
        return head;
    }
}
