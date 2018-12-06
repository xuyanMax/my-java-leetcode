package dfs.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xu on 20/08/2017.
 */
public class ConstructBinaryTreeFromPostorderAndInorder {
    public Map<Integer, Integer> map;
    PostIndex postIndex;

    // memoize hashmap <preOrder value, index>
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length == 0 || postorder.length == 0)
            return null;
        postIndex = new PostIndex(inorder.length - 1);

        // preOrder[] value -> index
        map = new HashMap();
        for (int i = 0; i < inorder.length; i++)
            map.put(inorder[i], i);
        return helper(postorder, inorder, 0, inorder.length - 1);

    }

    public TreeNode helper(int[] post, int[] in, int inLeft, int inRight) {
        if (inLeft > inRight)
            return null;
        TreeNode node = new TreeNode(post[postIndex.index--]);
        int pos = map.get(node.val);

        node.right = helper(post, in, pos + 1, inRight);
        node.left = helper(post, in, inLeft, pos - 1);
        return node;
    }
    //


    class PostIndex {
        int index;

        public PostIndex(int index) {
            this.index = index;
        }
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
