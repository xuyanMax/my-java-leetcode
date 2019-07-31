package tree;

/**
 * @author xu
 * <p>
 * http://www.geeksforgeeks.org/boundary-traversal-of-binary-tree/
 */
public class BoundaryTraversalAntiClockwise {

    public static void main(String[] args) {
        AvlNode root = new AvlNode(20);
        root.left = new AvlNode(8);
        root.left.left = new AvlNode(4);
        root.left.left.right = new AvlNode(41);
        root.left.left.right.left = new AvlNode(42);
        root.left.left.right.right = new AvlNode(43);
        root.left.right = new AvlNode(12);
        root.left.right.left = new AvlNode(10);
        root.left.right.right = new AvlNode(14);
        root.right = new AvlNode(22);
        root.right.right = new AvlNode(25);
        printBoundary(root);

    }

    static void printLeaves(AvlNode root) {
        if (root == null)
            return;
        else {
            printLeaves(root.left);
            printLeaves(root.right);
            if (root.left == null && root.right == null)
                System.out.println(root.key);
        }

    }

    // A function to print all left boundary nodes, except a leaf node.
    // Print the nodes in TOP DOWN manner
    static void printBoundaryLeft(AvlNode root) {

        if (root == null)
            return;
        else {

            if (root.left != null) { //[20,8,22,4,12,null,25,null,41,null,14,null,null,null,null,null,null,42,43]
                // left boundary: 8-4-41; right boundary: 22
                System.out.println(root.key);
                printBoundaryLeft(root.left);

            } else if (root.right != null) { // (root.left==null && root.right != null)
                System.out.println(root.key);
                printBoundaryLeft(root.right);
            }
            // root.left == null && root.right == null
//			if (root.left == null && root.right == null) // do nothing to leaves
        }
    }

    // A function to print all right boundary nodes, except a leaf node.
    // Print the nodes in TOP DOWN manner
    static void printBoundaryRight(AvlNode root) {
        if (root == null)
            return;
        else {

            if (root.right != null) {
                System.out.println(root.key);
                printBoundaryRight(root.right);
            } else if (root.left != null) { // (root.left==null && root.right != null)
                System.out.println(root.key);
                printBoundaryRight(root.right);
            }
        }
    }

    static void printBoundary(AvlNode root) {
        if (root == null) return;
        System.out.println(root.key);

        printBoundaryLeft(root.left);

        printLeaves(root);

        printBoundaryRight(root.right);
    }

}
