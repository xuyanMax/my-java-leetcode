package graph.tree.easy;

/**
 * 606. Construct String from Binary Tree
 * <p>
 * You need to construct a string consists of parenthesis and integers from a binary graph.tree
 * with the preOrder traversing way.
 * <p>
 * The null node needs to be represented by empty parenthesis pair "()".
 * And you need to omit all the empty parenthesis pairs that don't affect the one-to-one mapping
 * relationship between the string and the original binary graph.tree.
 * <p>
 * Example 1:
 * Input: Binary graph.tree: [1,2,3,4]
 * 1
 * /   \
 * 2     3
 * /
 * 4
 * <p>
 * Output: "1(2(4))(3)"
 * <p>
 * Explanation: Originally it needs to be "1(2(4)())(3()())",
 * but you need to omit all the unnecessary empty parenthesis pairs.
 * And it will be "1(2(4))(3)".
 * Example 2:
 * Input: Binary graph.tree: [1,2,3,null,4]
 * 1
 * /   \
 * 2     3
 * \
 * 4
 * <p>
 * Output: "1(2()(4))(3)"
 * <p>
 * Explanation: Almost the same as the first example,
 * except we can't omit the first parenthesis pair to break the one-to-one
 * mapping relationship between the input and the output.
 */
public class ConstructStringFromBT {
    class TreeNode {
        int val;
        TreeNode right, left;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public String tree2str(TreeNode t) {
        if (t == null) return "";

        if (t.left == null && t.right == null)
            return t.val + "";

        if (t.right == null)
            return t.val + "(" + tree2str(t.left) + ")";

        if (t.left == null)
            return t.val + "()(" + tree2str(t.right) + ")";

        return t.val + "(" + tree2str(t.left) + ")(" + tree2str(t.right) + ")";

    }
}
