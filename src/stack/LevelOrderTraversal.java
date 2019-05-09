package stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {

    /*
     * One queue with count
     */
    public List<List<Integer>> LevelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();

        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            ArrayList<Integer> alevel = new ArrayList<>();
            while (levelSize-- > 0) {
                TreeNode head = queue.poll();
                alevel.add(head.val);
                if (head.left != null) {
                    queue.add(head.left);
                }
                if (head.right != null)
                    queue.add(head.right);
            }
            ret.add(alevel);
        }
        return ret;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> ret = new ArrayList<List<Integer>>();

        if (root == null) return ret;

        LinkedList<TreeNode> doubleLinkedList = new LinkedList<>();
        boolean backward = false;

        doubleLinkedList.add(root);

        while (!doubleLinkedList.isEmpty()) {
            int levelSize = doubleLinkedList.size();
            ArrayList<Integer> alevel = new ArrayList<>();
            if (backward) {
                while (levelSize-- > 0) {
                    TreeNode head = doubleLinkedList.pollFirst();
                    alevel.add(head.val);
                    if (head.right != null) {
                        doubleLinkedList.add(head.right);
                    }
                    if (head.left != null)
                        doubleLinkedList.add(head.left);
                }
            } else {
                while (levelSize-- > 0) {
                    TreeNode head = doubleLinkedList.pollLast();
                    alevel.add(head.val);
                    if (head.left != null) {
                        doubleLinkedList.addFirst(head.left);
                    }
                    if (head.right != null) {
                        doubleLinkedList.addFirst(head.right);
                    }
                }
            }
            ret.add(alevel);
            backward = !backward;
        }
        return ret;
    }

    public List<List<Integer>> zigzagLevelOrderImproved(TreeNode root) {

        List<List<Integer>> ret = new ArrayList<List<Integer>>();

        if (root == null) return ret;

        LinkedList<TreeNode> doubleLinkedList = new LinkedList<>();
        boolean backward = false;

        doubleLinkedList.add(root);

        while (!doubleLinkedList.isEmpty()) {
            int levelSize = doubleLinkedList.size();
            ArrayList<Integer> alevel = new ArrayList<>();
            while (levelSize-- > 0) {
                TreeNode tmp = doubleLinkedList.poll();//from head
                if (backward) {
                    alevel.add(0, tmp.val);
                } else {
                    alevel.add(tmp.val);
                }
                if (tmp.left != null) {
                    doubleLinkedList.add(tmp.left);
                }
                if (tmp.right != null) {
                    doubleLinkedList.add(tmp.right);
                }
            }
            ret.add(alevel);
            backward = !backward;
        }
        return ret;
    }

    public List<List<Integer>> zigzagLevelOrderDFS(TreeNode root) {

        List<List<Integer>> ret = new ArrayList<List<Integer>>();

        if (root == null) return ret;

        LinkedList<TreeNode> doubleLinkedList = new LinkedList<>();

        return ret;

    }

    private void dfs() {

    }
}
