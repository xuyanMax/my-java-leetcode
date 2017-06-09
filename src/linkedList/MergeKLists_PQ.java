package linkedList;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by xu on 2017/5/31.
 *
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 *
 */
public class MergeKLists_PQ {
    /* use priority queue to sort the lists based on their first nodes
    *
    * pop out the first list with the smallest first node
    *
    * add that list w/o the first node to the priority queue again (if its' next is not null)
    *
    * */
    public ListNode mergeKLists(List<ListNode> lists) {

        if (lists==null||lists.size()==0) return null;

        PriorityQueue<ListNode> queue= new PriorityQueue<ListNode>(lists.size(),new Comparator<ListNode>(){
            @Override
            public int compare(ListNode o1,ListNode o2){
               return Integer.compare(o1.val, o2.val);
            }
        });

        ListNode dummy = new ListNode(0);
        ListNode tail=dummy;

        for (ListNode node:lists)
            if (node!=null)
                queue.add(node);

        while (!queue.isEmpty()){
            tail.next=queue.poll();
            tail=tail.next;

            if (tail.next!=null)
                queue.add(tail.next);
        }
        return dummy.next;
    }
    class ListNode {
        int val;
        ListNode next;
        public ListNode(int key) {
            val = key;
            next = null;
        }
    }
    /*Bottom up and divide and conquer;
    *
    *
    * */
}
