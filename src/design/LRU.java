package design;

import java.util.HashMap;

public class LRU {

    HashMap<Integer, Node> map;
    DoubleList cache;
    int capacity;

    public LRU(int capacity) {
        this.capacity = capacity;
        cache = new DoubleList();
        map = new HashMap<>();
    }

    public void makeRecently(int key) {
        Node curr = map.get(key);
        //移除
        cache.remove(curr);
        //重新插入
        cache.addLast(curr);
    }

    public void addRecently(int key, int val) {
        Node curr = new Node(key, val);
        cache.addLast(curr);
        map.put(key, curr);
    }

    public void deleteKey(int key) {
        Node curr = map.get(key);
        cache.remove(curr);
        map.remove(key);
    }

    public void removeLeastRecently() {
        Node curr = cache.removeFirst();
        map.remove(curr.key);
    }

    public Node get(int key) {
        if (map.get(key) == null)
            return null;
        makeRecently(key);
        return map.get(key);
    }

    public void put(int key, int val) {
        if (map.containsKey(key)) {
            deleteKey(key);
            addRecently(key, val);
            return;
        }
        if (cache.size() >= capacity) {
            removeLeastRecently();
        }

        addRecently(key, val);
    }


    //双向链表, 只能从尾部插入，头部取出
    class DoubleList {
        private Node head, tail;
        private int size = 0;

        public DoubleList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.pre = head;
        }

        //在链表尾部添加节点
        public void addLast(Node n) {
            tail.pre.next = n;
            n.next = tail;
            n.pre = tail.pre;
            tail.pre = n;
            size++;
        }

        //移除某个节点
        public void remove(Node n) {
            n.pre.next = n.next;
            n.next.pre = n.pre;
            size--;
        }

        //移除第一个节点
        public Node removeFirst() {
            head.next = head.next.next;
            if (head.next == tail) return null;
            Node first = head.next;
            remove(first);
            size--;
            return first;
        }

        public int size() {
            return size;
        }
    }

    class Node {
        public int key, val;
        public Node next, pre;

        public Node(int k, int v) {
            key = k;
            val = v;
        }
    }
}