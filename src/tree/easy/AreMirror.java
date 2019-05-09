package tree.easy;

import tree.AvlNode;

/**
 * @author xu
 * <p>
 * http://www.geeksforgeeks.org/check-if-two-trees-are-mirror/
 */
public class AreMirror {

    static boolean areMirr(AvlNode rootA, AvlNode rootB) {
        if (rootA == null || rootB == null)
            return rootA == rootB;
        if (rootA.key == rootB.key)
            return areMirr(rootA.right, rootB.left) && areMirr(rootA.left, rootB.right);

        return false;

    }
}
