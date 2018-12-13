package linkedList;


import java.util.ArrayList;
import java.util.List;

public class ConvertSortedListToBST {
    public static void main(String[] args) {
        ConvertSortedListToBST inst = new ConvertSortedListToBST();

        ListNode head = inst.new ListNode(1);
        ListNode p1 = inst.new ListNode(2);
        ListNode p2 = inst.new ListNode(3);
        ListNode p3 = inst.new ListNode(4);
        ListNode p4 = inst.new ListNode(5);
        ListNode p5 = inst.new ListNode(6);
        ListNode p6 = inst.new ListNode(7);
        ListNode p7 = inst.new ListNode(8);
        ListNode p8 = inst.new ListNode(9);
        head.next = p1;
        p1.next = p2;
        p2.next = p3;
        p3.next = p4;
        p4.next = p5;
        p5.next = p6;
        p6.next = p7;
        p7.next = p8;
        p8.next = null;
    }

    private ListNode node;

    /**
     * 利用中序遍历
     * 1. 先确定链表长度
     * 2. 中序遍历生成平衡二叉搜索树
     * 时间复杂度: nlgn
     * 主定理
     * F(N) = 2*F(n/2) + n/2 => F(n)=nlgn
     *
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        int size = 0;
        ListNode pointer = head;
        node = head;

        while (pointer != null) {
            pointer = pointer.next;
            size++;
        }

        return inorderHelper(0, size - 1);
    }

    public TreeNode inorderHelper(int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = start + (end - start) / 2;
        TreeNode left = inorderHelper(start, mid - 1);

        //此时的node即为有序链表的在【start，end】中的中间节点
        TreeNode treenode = new TreeNode(node.val);

        treenode.left = left;

        // 像 preOrderIndex++一样，从有序链表头向后推移
        node = node.next;

        TreeNode right = inorderHelper(mid + 1, end);
        treenode.right = right;

        return treenode;
    }

    /**
     * recursion + conversion to array
     */
    public List<ListNode> convertListNode2ArrayList(ListNode head) {
        List<ListNode> res = new ArrayList<>();
        ListNode pointer = head;
        while (pointer != null) {
            res.add(pointer);
            pointer = pointer.next;
        }
        return res;
    }

    class ListNode {
        int val;
        ListNode next;

        public ListNode(int key) {
            val = key;
            next = null;
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
