package dfs.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by xu on 05/08/2017.
 */
/*
You need to find the largest value in each row of a binary tree.

Example:
Input:

          1
         / \
        3   2
       / \   \
      5   3   9

Output: [1, 3, 9]

*/
public class FindLargestInEachRow {

    /// visit pre-order idea
    // use depth to expand the result list size and update the max value in every level
    public List<Integer> largestValues_dfs(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null)
            return ret;
        helper(ret, 0, root);
        return ret;

    }
    public void helper(List<Integer> ret, int depth, TreeNode root){
        if (root == null)
            return;
        //首次到达 depth 层
        if (depth == ret.size()) {
            ret.add(root.val);
        }else {
            ret.set(depth, Math.max(ret.get(depth), root.val));
        }

        helper(ret, depth + 1, root.left);
        helper(ret, depth + 1, root.right);

    }
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }


}
