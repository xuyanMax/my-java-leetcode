package dfs;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by xu on 02/08/2017.
 */
public class SameTree {
    boolean isSameTree(TreeNode rootA, TreeNode rootB) {


        if (rootA==null || rootB==null ) return rootA == rootB;

        return (rootA.val == rootB.val)
                && isSameTree(rootA.left, rootB.left)
                && isSameTree(rootA.right, rootB.right);

    }
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    boolean isSameTreeIter(TreeNode rootA, TreeNode rootB) {

        Deque<TreeNode> treeA = new ArrayDeque<>();
        Deque<TreeNode> treeB = new ArrayDeque<>();

        treeA.addLast(rootA);
        treeB.addLast(rootB);

        while (!treeA.isEmpty() && !treeB.isEmpty()) {
            TreeNode a = treeA.poll();
            TreeNode b = treeB.poll();

            if (a==null && b==null)
                continue;
            //其中一个节点为null或者两个节点数值不等
            if (a == null || b == null || a.val != b.val)
                return false;

            treeA.addLast(a.left);
            treeA.addLast(a.right);
            treeB.addLast(b.left);
            treeB.addLast(b.right);
        }
        // same
        return true;

    }
}
