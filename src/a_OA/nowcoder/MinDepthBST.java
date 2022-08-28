package a_OA.nowcoder;

import java.util.ArrayDeque;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/e08819cfdeb34985a8de9c4e6562e724?orderByHotValue=1&questionTypes=000100&difficulty=00010&page=2&onlyReference=false
 * 来源：牛客网
 * <p>
 * [编程题]minimum-depth-of-binary-graph.tree
 * 热度指数：97418时间限制：1秒空间限制：32768K
 * Given a binary graph.tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path
 * from the root node down to the nearest leaf node.
 */
public class MinDepthBST {
    public int run(TreeNode root) {
        if (root == null)
            return 0;
        int l = run(root.left);
        int r = run(root.right);
        if (l == 0 || r == 0)
            return 1 + l + r;
        return Math.min(l, r) + 1;
    }

    /**
     * @param root
     * @return
     */
    public int run2(TreeNode root) {
        if (root == null)
            return 0;

        // 若左子树为空，则返回右子树的最小深度+1
        if (root.left == null)
            return run2(root.right) + 1;

        // 若右子树为空，则返回左子树的最小深度+1
        if (root.right == null)
            return run2(root.left) + 1;

        return Math.min(run2(root.left), run2(root.right)) + 1;
    }

    public int bfs(TreeNode root) {
        if (root == null)
            return 0;
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        deque.add(root);
        int depth = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            depth++;
            while (size-- > 0) {
                TreeNode curr = deque.pollFirst();
                if (curr.left != null)
                    deque.add(curr.left);
                if (curr.right != null)
                    deque.add(curr.right);
                if (curr.left == null && curr.right == null)
                    return depth;
            }
        }
        return 1;
    }

    class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
    }
}
