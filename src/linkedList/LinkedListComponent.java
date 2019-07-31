package linkedList;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: xyx
 * @Date: 2019-07-27 12:42
 * @Version 1.0
 *
 * Complexity Analysis
 *
 * Time Complexity: O(N + G\text{.length})O(N+G.length),
 * where NN is the length of the linked list with root node head.
 *
 * Space Complexity: O(G\text{.length})O(G.length), to store Gset.
 */
public class LinkedListComponent {
    public int numComponents(ListNode head, int[] G) {
        Set<Integer> Gset = new HashSet();
        for (int x : G) Gset.add(x);

        ListNode cur = head;
        int ans = 0;

        while (cur != null) {
            if (Gset.contains(cur.val) &&
                    (cur.next == null || !Gset.contains(cur.next.val)))
                ans++;
            cur = cur.next;
        }

        return ans;
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
