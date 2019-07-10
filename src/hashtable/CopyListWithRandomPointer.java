package hashtable;

import sortAlgorithms.RadixSort;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

/**
 * Created by xu on 25/08/2017.
 * <p>
 * A linked list is given such that each node contains an additional random pointer
 * which could point to any node in the list or null.
 * <p>
 * Return a deep copy of the list.
 */
public class CopyListWithRandomPointer {

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null)
            return head;

        Map<Integer, RandomListNode> map = new HashMap<>();
        LinkedList<RandomListNode> queue = new LinkedList<>();

        RandomListNode newHead = new RandomListNode(head.label);
        map.put(head.label, newHead);

        queue.add(head);

        while (!queue.isEmpty()) {
            RandomListNode curr = queue.poll();
            if (curr.next != null) {
                if (!map.containsKey(curr.next.label)) {
                    queue.add(curr.next);
                    map.put(curr.next.label, new RandomListNode(curr.next.label));

                }
                map.get(curr.label).next = map.get(curr.next.label);
            }
            if (curr.random != null) {
                if (!map.containsKey(curr.random.label)) {
                    queue.add(curr.random);
                    map.put(curr.random.label, new RandomListNode(curr.random.label));
                }
                map.get(curr.label).random = map.get(curr.random.label);

            }
        }
        return newHead;

    }
    // O(N)

    public RandomListNode copyRandomList_easy(RandomListNode head) {
        if (head == null)
            return head;
        // <old node, new node>
        Map<RandomListNode, RandomListNode> map = new HashMap<>();

        RandomListNode node = head;

        // loop 1 copy all nodes
        while (node != null) {
            map.put(node, new RandomListNode(node.label));
            node = node.next;
        }
        // 或者用for循环
//        for (RandomListNode ptr = head; ptr != null; ptr = ptr.next) {
//            map.put(ptr, new RandomListNode(ptr.label));
//        }

        // loop 2 assign next and random pointes
        node = head;
        while (node != null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }
        return node;

    }

    //优化的算法，不需要额外的存储空间
    public RandomListNode copyRandomList_opt(RandomListNode head) {

        if (head == null)
            return head;
        // loop 1  copy node
        // 1--2--3--4
        RandomListNode pointer = head;
        while (pointer != null) {
            RandomListNode next = pointer.next;
            RandomListNode copy = new RandomListNode(pointer.label);
            pointer.next = copy;
            copy.next = next;
            // pointer = pointer.next.next
            pointer = next;

        }
        // after 1 loop
        // 1--1'--2--2'--3--3'--4--4'

        // loop 2 assign random pointers for each node
        pointer = head;
        while (pointer != null) {
            if (pointer.random != null) {
                pointer.next.random = pointer.random.next;
            }
            pointer = pointer.next.next;
        }

        // loop 3 restore original list and extract copy list

        pointer = head;
        RandomListNode newHead = head.next;
        RandomListNode copy_pointer = newHead;
        // 加入copy_p.next
        // copy_p.next首先触碰到null
        // 最后还有处理pointer.next = null
        while (copy_pointer.next != null) {
            pointer.next = pointer.next.next;
            pointer = pointer.next;// pointer 指针交替重新链接原链表和新建的copy链表
            //第一次迭代 pointer 1--2--2'--3--3'--4--4'
            //         copy_p  1'--2'--3--3'--4--4'
            //第二次迭代 1--2--3--3'--4--4'
            //         1'--2'--3'--4--4'
            //第三次迭代 1--2--3--4--4'（需要多一次去掉4'）
            //         1'--2'--3'--4'

            copy_pointer.next = copy_pointer.next.next;
            copy_pointer = copy_pointer.next;
        }
        //处理
        pointer.next = pointer.next.next;//null
        return newHead;
    }


    class RandomListNode {
        int label;
        RandomListNode next, random;

        public RandomListNode(int label) {
            this.label = label;
        }
    }
}
