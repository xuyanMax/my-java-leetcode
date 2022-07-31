package linkedList;

import java.util.ArrayList;
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

        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.size(), Comparator.comparingInt(ListNode::getVal));

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

        public int getVal() {
            return val;
        }
    }

    //遍历合并
    public ListNode mergeKLists(ArrayList<ListNode> lists) {
        int n = lists.size();
        if (n == 0)
            return null;
        if (n == 1)
            return lists.get(0);
        ListNode node = lists.get(0);
        for (int i = 1; i < n; i++) {
            node = margeTwoList(lists.get(i), node);
        }
        return node;
    }

    public ListNode margeTwoList(ListNode node1, ListNode node2) {
        ListNode node = new ListNode(-1);
        ListNode tmp = node;
        while (node1 != null && node2 != null) {
            if (node1.val <= node2.val) {
                tmp.next = node1;
                node1 = node1.next;
            } else {
                tmp.next = node2;
                node2 = node2.next;
            }
            tmp = tmp.next;
        }
        tmp.next = node1 != null ? node1 : node2;
        return node.next;
    }
}
