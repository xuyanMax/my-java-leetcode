package tree.medium;

import java.util.*;

/*
* 652. Find Duplicate Subtrees

Given a binary tree, return all duplicate subtrees.
For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with same node values.

Example 1:
        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
The following are two duplicate subtrees:
      2
     /
    4
and
    4
Therefore, you need to return above trees' root in the form of a list.
*/
public class FindDuplicateSubtrees {
    class TreeNode{
        int val;
        TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new LinkedList<>();
        map = new HashMap<>();
        preOrder(root, res);
        Collections.sort(res, Collections.reverseOrder());
        return res;
    }
    static HashMap<String, Integer> map;

    public String preOrder(TreeNode cur, List<TreeNode> res) {
        if (cur == null) return "#";

        String serial = cur.val + "," + preOrder(cur.left, res) + "," + preOrder(cur.right, res);

        if (map.getOrDefault(serial, 0) == 1)
            res.add(cur);

        map.put(serial, map.getOrDefault(serial, 0) + 1);

        return serial;
    }

}
