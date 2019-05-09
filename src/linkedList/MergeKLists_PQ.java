package linkedList;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by xu on 2017/5/31.
 * <p>
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 */
public class MergeKLists_PQ {
    /**
     * use priority queue to sort the lists based on their first nodes
     * <p>
     * pop out the first list with the smallest first node
     * <p>
     * add that list w/o the first node to the priority queue again (if its' next is not null)
     */
    public ListNode mergeKLists(List<ListNode> lists) {

        if (lists == null || lists.size() == 0) return null;

        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.size(), (a, b) -> (
                a.val - b.val)
        );

        ListNode dummy = new ListNode(0);
        ListNode point = dummy;

        for (ListNode node : lists)
            if (node != null)
                queue.add(node);

        while (!queue.isEmpty()) {
            point.next = queue.poll();
            point = point.next;

            if (point.next != null)
                queue.add(point.next);
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
}
