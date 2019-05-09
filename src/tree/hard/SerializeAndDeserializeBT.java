package tree.hard;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 297. Serialize and Deserialize Binary Tree
 * <p>
 * Serialization is the process of converting a data structure or object into
 * a sequence of bits so that it can be stored in a file or memory buffer,
 * or transmitted across a network connection link to be reconstructed later
 * in the same or another computer environment.
 * <p>
 * Design an algorithm to serialize and deserialize a binary tree.
 * There is no restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and
 * this string can be deserialized to the original tree structure.
 * <p>
 * For example, you may serialize the following tree
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree.
 * You do not necessarily need to follow this format, so please be creative and
 * come up with different approaches yourself.
 * <p>
 * <p>
 * <p>
 * Note: Do not use class member/global/static variables to store states.
 * Your serialize and deserialize algorithms should be stateless.
 */
class TreeNode {
    int val;
    TreeNode right, left;

    public TreeNode(int val) {
        this.val = val;
    }
}

public class SerializeAndDeserializeBT {
    public String serialize(TreeNode root) {
        if (root == null) return "#";
        return root.val + "," + serialize(root.left) + "," + serialize(root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<String>();

        queue.addAll(Arrays.asList(data.split(",")));//分割后前序遍历，#代表遍历到空节点

        return helper(queue);
    }

    //前序遍历建Tree
    public TreeNode helper(Queue<String> queue) {
        String curr = queue.poll();//poll返回队列头部元素
        if (curr.equals("#")) return null;//返回空节点
        TreeNode root = new TreeNode(Integer.parseInt(curr));
        root.left = helper(queue);
        root.right = helper(queue);
        return root;
    }
}
