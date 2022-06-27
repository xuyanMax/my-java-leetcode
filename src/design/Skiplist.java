package design;

import java.util.Arrays;

//https://leetcode.com/problems/design-skiplist/
public class Skiplist {
    private ListNode head; // head works as a header not storing any information for searching
    private int length;
    private int listLevel; //max of levels.
    private final ListNode tail = new ListNode(Integer.MAX_VALUE, 0);//


    static class ListNode {
        int key;
        int dup;//store the number of duplicate elements
        // ListNode next;
        ListNode[] pointers;//nice way

        // List<SkipListLevel> level = new ArrayList<>();

        public ListNode(int key, int level) {
            this.key = key;
            pointers = new ListNode[level];
            dup = 1;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "key=" + key +
                    ", pointers=" + Arrays.toString(pointers) +
                    '}';
        }
    }

    public static void main(String[] args) {
        Skiplist skiplist = new Skiplist();
        for (ListNode node : skiplist.head.pointers) {
            System.out.println(node);
        }
    }
//    static class SkipListLevel {
//        ListNode next;
//        int span;
//    }

    public Skiplist() {
        head = new ListNode(-1, MAX_LEVEL);
        listLevel = 1;
        Arrays.fill(head.pointers, tail);
    }

    public void add(int num) {
        ListNode[] pred = getPredecesors(num);

        //判断是否存在该元素
        ListNode curr = pred[0].pointers[0];
        if (curr.key == num) {
            curr.dup++;
        } else {

            //update predecessor for new levels
            int level = random();
            if (level > listLevel) {
                for (int i = listLevel; i < level; i++) {
                    pred[i] = head;
                }
                listLevel = level;
            }
            //update pointers linked to the new ListNode
            curr = new ListNode(num, level);
            for (int i = 0; i < level; i++) {
                curr.pointers[i] = pred[i].pointers[i];
                pred[i].pointers[i] = curr;
            }

        }
    }

    private ListNode[] getPredecesors(int target) {

        ListNode curr = head;
        ListNode[] pred = new ListNode[MAX_LEVEL];

        for (int i = listLevel - 1; i >= 0; i--) {
            while (curr != null && curr.pointers[i] != null && curr.pointers[i].key < target) {
                curr = curr.pointers[i];
            }
            //exactly curr's next node's value > target
            pred[i] = curr;
        }
        return pred;
    }

    public boolean search(int target) {
        ListNode curr = head;

        // x, y axis nested for loops
        for (int i = listLevel - 1; i >= 0; i--) {
            while (curr.pointers[i].key < target) {
                curr = curr.pointers[i]; // points to next
            }
        }
        return curr.pointers[0].key == target;
    }

    public boolean erase(int num) {
        ListNode[] pred = getPredecesors(num);
        ListNode curr = pred[0].pointers[0]; //curr.key == num
        if (curr.key != num) {
            return false;
        } else if (curr.dup > 1) {
            curr.dup--;
        } else { // erase it from the linked lists
            //update linked lists to skip the ListNode(num, level);
            for (int i = 0; i < curr.pointers.length; i++) {
                pred[i].pointers[i] = curr.pointers[i];
            }

            //update listlevel
            if (curr.pointers.length == listLevel) {
                --listLevel;
            }
        }
        return true;
    }
//    public ListNode search(ListNode head, int target) {
//        if (head == null)
//            return null;
//
//        List<SkipListLevel> skiplevel = head.level;
//        ListNode pointer = head;
//        int level = skiplevel.size();
//
//        //iterative method of searching.
//        ListNode retNode = helper(pointer, target, level);
//        return retNode;
//
//    }

//    public ListNode helper(ListNode pointer, int target, int level) {
//        if (pointer == null || level < 0)
//            return pointer;
//
//        ListNode pre, next;
//        // duplicate elements
//        while (level > 0 && pointer != null && pointer.key <= target) {
//            pre = pointer;
//            pointer = pointer.level.get(level - 1).next;
//
//            if (pointer.key == target) {
//                return pointer;
//            }
//        }
//        return helper(pointer, target, level - 1);
//    }


    private static double PROBABILITY = 0.25;
    private static int MAX_LEVEL = 16;//why ?

    private int random() {
        int level = 1;
        //增加一层的概率是0.25
        //原listlevel = N,则该节点的level最大为N+1
        while (Math.random() < PROBABILITY && level < Math.min(MAX_LEVEL, listLevel + 1)) {
            level++;
        }
        return level;
    }
}
