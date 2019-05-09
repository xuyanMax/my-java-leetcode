package tree;

/**
 * @author xu
 * <p>
 * * Youtube link - https://youtu.be/zm83jPHZ-jA
 * <p>
 * Given a binary search tree and a key, return node which has key as this key or return
 * null if no node has key as key.
 * <p>
 * Solution
 * Since its BST for every node check if root.key is key and if not go either left or
 * right depending on if root.key is greater or less than key
 * <p>
 * Time complexity is O(n) for non balanced BST
 * Time complexity is O(logn) for balanced BST
 * <p>
 * Test cases:
 * 1) null tree
 * 2) Tree with one node and key is that node
 * 3) Tree with many nodes and key does not exist
 * 4) Tree with many nodes and key exists
 */

public class BinarySearchTree {

    /**
     * @param args
     */
    /**
     * @param args
     */
    /**
     * @param args
     */
    /**
     * @param args
     */
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        Node root = null;
        root = bt.addNode(10, root);
        root = bt.addNode(20, root);
        root = bt.addNode(-10, root);
        root = bt.addNode(15, root);
        root = bt.addNode(0, root);
        root = bt.addNode(21, root);
        root = bt.addNode(-1, root);
        BinarySearchTree bstSearch = new BinarySearchTree();

        Node result = bstSearch.search(root, 21);
        System.out.println(result.data == 21);

        result = bstSearch.search(root, 10);
        System.out.println(result.data == 10);

        result = bstSearch.searchItr(root, 10);
        System.out.println(result.data == 10);

        result = bstSearch.search(root, 11);
        System.out.println(result == null);

    }

    // recursive
    public Node search(Node root, int val) {

        if (root == null)
            return null;
        else {
            if (root.data < val)
                return search(root.right, val);
            else if (root.data > val)
                return search(root.left, val);
            else return root;
        }
    }

    // iterative

    public Node searchItr(Node root, int val) {

        if (root == null)
            return null;
        while (root != null) {
            if (root.data == val)
                return root;
            else if (root.data < val)
                root = root.right;
            else root = root.left;
        }
        return root;
    }
}
