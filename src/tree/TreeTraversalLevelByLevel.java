package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author xu
 * * Video link - https://youtu.be/7uG0gLDbhsI
 * <p>
 * Given a binary tree print each level on new line.
 * <p>
 * e.g           10
 * 5         15
 * 0   -1    2    6
 * Output :   10
 * 5 15
 * 0 -1 2 6
 * <p>
 * Solution
 * Technique 1:
 * Use 2 queue. Keep polling from q1 and offer to q2 till q1 is empty.
 * After that print a new line. Keep polling from q2 and offer to q1
 * till q2 is empty. Keep doing this still both q1 and q2 are empty.
 * <p>
 * Technique 2
 * Use one queue with delimiter. Add a delimiter null after every level.
 * As soon as you encounter a null print a new line and add null at end of queue
 * <p>
 * Technique 3
 * Use one queue with count. Keep count of nodes at every level. As soon as this
 * count is 0 print a new line.
 * <p>
 * Time space complexity for all above algorithm is O(n).
 */

public class TreeTraversalLevelByLevel {

    /**
     * use one queue and delimiter to print level by level
     */

    public void levelByLevelOneQueueUsingDelimiter(Node root) {

        if (root == null) return;

        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        queue.add(null);

        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root != null) {
                System.out.print(root.data + " ");
                if (root.left != null)
                    queue.add(root.left);
                if (root.right != null)
                    queue.add(root.right);
            } else {
                if (!queue.isEmpty()) {
                    queue.add(null);
                    System.out.println("");
                }
            }
        }
    }

    /**
     * use one queue and a count to print level by level
     */
    public void levelByLevelOneQueueUsingCount(Node root) {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        int levelCount = 1;
        int CurrentCount = 0;
        while (!queue.isEmpty()) {
            levelCount = queue.size();
            while (levelCount > 0) {
                root = queue.poll();
                System.out.print(root.data + " ");
                if (root.left != null) {
                    queue.add(root.left);
                }
                if (root.right != null) {
                    queue.add(root.right);
                }
                levelCount--;
            }
            System.out.println("");
        }
    }

    public void levelByLevelOneQueueUsingCountImproved(Node root) {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);

        while (!queue.isEmpty()) {

            int levelCount = queue.size();

            while (levelCount > 0) {
                root = queue.poll();
                System.out.print(root.data + " ");
                if (root.left != null)
                    queue.add(root.left);

                if (root.right != null)
                    queue.add(root.right);

                levelCount--;
            }
            System.out.println("");

        }

    }

    /**
     * use two queues to print level by level
     * 两个队列，分别交替存储 每一个level层
     */
    public void levelByLevelTwoQueues(Node root) {
        Queue<Node> queue1 = new LinkedList<Node>();
        Queue<Node> queue2 = new LinkedList<Node>();
        queue1.add(root);

        while (!queue1.isEmpty() || !queue2.isEmpty()) {

            while (!queue1.isEmpty()) {
                root = queue1.poll();
                System.out.print(root.data + " ");
                if (root.left != null)
                    queue2.add(root.left);

                if (root.right != null)
                    queue2.add(root.right);
            }
            System.out.println("");
            while (!queue2.isEmpty()) {
                root = queue2.poll();
                System.out.print(root.data + " ");
                if (root.left != null)
                    queue1.add(root.left);

                if (root.right != null)
                    queue1.add(root.right);
            }
            System.out.println("");

        }
    }

    public static void main(String[] args) {
        TreeTraversalLevelByLevel ttLevelByLevel = new TreeTraversalLevelByLevel();
        BinaryTree bt = new BinaryTree();
        Node head = null;
        head = bt.addNode(10, head);
        head = bt.addNode(20, head);
        head = bt.addNode(30, head);
        head = bt.addNode(15, head);
        head = bt.addNode(-10, head);
        head = bt.addNode(0, head);
        head = bt.addNode(5, head);
        head = bt.addNode(-5, head);
        head = bt.addNode(-15, head);
        head = bt.addNode(27, head);
        head = bt.addNode(35, head);

        System.out.println("One queue with delimiter: ");
        ttLevelByLevel.levelByLevelOneQueueUsingDelimiter(head);

        System.out.println("\n" + "One queue with count: ");
        ttLevelByLevel.levelByLevelOneQueueUsingCountImproved(head);

        System.out.println("\n" + "Two queues: ");
        ttLevelByLevel.levelByLevelTwoQueues(head);

    }
}
