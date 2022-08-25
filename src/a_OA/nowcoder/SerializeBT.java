package a_OA.nowcoder;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

/**
 * @Author: xyx
 * @Date: 2018-11-30 22:10
 * @Version 1.0
 * 请实现两个函数，分别用来序列化和反序列化二叉树
 */
public class SerializeBT {
    // 前序递归
    String Serialize(TreeNode root) {
        if (root == null) return "#";
        return root.val + "," + Serialize(root.left) + "," + Serialize(root.right);
    }

    TreeNode Deserialize(String str) {
        String[] strs = str.split(",");
        Deque<String> queue = new ArrayDeque<>();
        queue.addAll(new ArrayList<String>(Arrays.asList(strs)));
        return buildTree(queue);
    }

    public TreeNode buildTree(Deque<String> queue) {
        if (queue.isEmpty()) return null;

        String val = queue.poll();

        if (val.equals("#")) return null;

        TreeNode root = new TreeNode(Integer.parseInt(val));

        root.left = buildTree(queue);
        root.right = buildTree(queue);

        return root;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
