package tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.DelayQueue;

/**
 * Youtube link - https://youtu.be/nzmtCFNae9k
 * Youtube link - https://youtu.be/elQcrJrfObg
 * Youtube link - https://youtu.be/qT65HltK2uE
 * Youtube link - https://youtu.be/ZM-sV9zQPEs
 * <p>
 * code reference:
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/TreeTraversals.java#L40
 * <p>
 * http://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion/
 * http://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion-and-without-stack/
 * http://www.geeksforgeeks.org/iterative-preorder-traversal/
 */
public class TreeTraversals {


    public void EX_INORDER_ITER(Node root) {
        Deque<Node> queue = new LinkedList<>();
        while (true) {
            if (root != null) {
                queue.addFirst(root);
                root = root.left;
            } else {
                if (queue.isEmpty()) break;
                ;
                root = queue.pollFirst();
                System.out.println(root.data);
                root = root.right;
            }
        }
    }

    public void EX_PREORDER_ITER(Node root) {
        Deque<Node> queue = new LinkedList<>();
        Node curr = root;
        queue.addFirst(curr);
        while (!queue.isEmpty()) {
            curr = queue.pollFirst();
            if (curr.right != null) queue.addFirst(curr.left);
            if (curr.left != null) queue.addFirst(curr.right);
        }
    }

    public void EX_POSTORDER_ITER(Node root) {
        Deque<Node> queue = new LinkedList<>();
        Deque<Node> queue2 = new LinkedList<>();

        queue.addFirst(root);
        while (!queue.isEmpty()) {
            if (root.right != null) queue.addFirst(root.right);
            if (root.left != null) queue.addFirst(root.left);

            root = queue.pollFirst();
            queue2.addFirst(root);
        }

        while (!queue2.isEmpty()) System.out.println(queue2.pollFirst().data);

    }


    public void inOrder(Node root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }

    /**
     * Iterative method
     */
    public void inOrderItr(Node root) {
        Deque<Node> stack = new LinkedList<Node>();
        Node node = root;
        while (true) {
            if (node != null) {
                stack.addFirst(node);
                node = node.left;
            } else {
                // exit while loop
                if (stack.isEmpty()) break;

                node = stack.pollFirst();
                System.out.println(node.data);
                node = node.right;
            }
        }
    }


    public void preOrder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public void preOrderItr(Node root) {
        Deque<Node> stack = new LinkedList<Node>();
        //List<Node> aa = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.poll();
            System.out.print(root.data + " ");

            //right then left
            if (root.right != null) stack.push(root.right);
            if (root.left != null) stack.push(root.left);

        }
    }

    public void postOrder(Node root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + " ");
    }

    public void postOrderItr(Node root) {
        Deque<Node> stack1 = new LinkedList<Node>();
        Deque<Node> stack2 = new LinkedList<Node>();
        stack1.addFirst(root);
        while (!stack1.isEmpty()) {
            root = stack1.pollFirst();
            if (root.left != null) {
                stack1.addFirst(root.left);
            }
            if (root.right != null) {
                stack1.addFirst(root.right);
            }
            stack2.addFirst(root);
        }
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pollFirst().data + " ");
        }
    }

    public void postOrderItrOneStack(Node root) {
        Node current = root;
        Deque<Node> stack = new LinkedList<>();
        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                stack.addFirst(current);
                current = current.left;
            } else {
                Node temp = stack.peek().right;
                if (temp == null) {
                    temp = stack.poll();
                    System.out.print(temp.data + " ");
                    while (!stack.isEmpty() && temp == stack.peek().right) {
                        temp = stack.poll();
                        System.out.print(temp.data + " ");
                    }
                } else {
                    current = temp;
                }
            }
        }
    }

    public static void main(String args[]) {
        BinaryTree bt = new BinaryTree();
        Node head = null;
        head = bt.addNode(10, head);
        head = bt.addNode(15, head);
        head = bt.addNode(19, head);
        head = bt.addNode(17, head);
        head = bt.addNode(11, head);

        head = bt.addNode(-11, head);


        TreeTraversals tt = new TreeTraversals();
//        tt.inOrder(head);
//        System.out.println();
//        tt.postOrderItr(head);
//        System.out.println();
        tt.postOrderItrOneStack(head);
    }

}