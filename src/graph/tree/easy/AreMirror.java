package graph.tree.easy;

import graph.tree.AvlNode;

/**
 * @author xu
 * <p>
 * http://www.geeksforgeeks.org/check-if-two-trees-are-mirror/
 */
public class AreMirror {

    static boolean areMirror(AvlNode rootA, AvlNode rootB) {
        if (rootA == null || rootB == null)
            return rootA == rootB;
        if (rootA.key == rootB.key)
            return areMirror(rootA.right, rootB.left) && areMirror(rootA.left, rootB.right);

        return false;

    }
}
