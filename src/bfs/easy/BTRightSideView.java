package bfs.easy;

import java.util.*;

public class BTRightSideView {
    // 采用层序遍历的方法
    public List<Integer> rightSideView_bfs(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null)
            return ret;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {

            level = queue.size();
            int fix = level;

            while (level > 0) {
                TreeNode node = queue.poll();
                //只添加每一层的第一个节点（bfs从右向左扫描）
                if (fix == level)
                    ret.add(node.val);

                if (node.right != null) {
                    queue.add(node.right);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                level--;
            }

        }

        return ret;
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
