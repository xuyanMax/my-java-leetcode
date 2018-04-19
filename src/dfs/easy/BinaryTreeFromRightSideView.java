package dfs.easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by xu on 10/08/2017.

199.
Given a binary tree, imagine yourself standing on the right side of it,
return the values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,
   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
You should return [1, 3, 4].
*/
public class BinaryTreeFromRightSideView {


    //每一层都只添加一个
    // 层数 == result list.size()时，添加节点值
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null)
            return ret;
        helper_right(root, ret, 0);
        return ret;

    }
    public void helper_right(TreeNode root, List<Integer> result, int depth){
        //结束条件
        if (root == null)
            return;
        if (result.size() == depth) {
            result.add(root.val);
        }
        // 此前序遍历的变体
        // 从Tree右侧开始遍历，是前序遍历的对称方向
        helper_right(root.right, result, depth + 1);
        helper_right(root.left, result, depth + 1);

    }
    /*如果是从left side view，那么只需要用前序遍历的思想即可*/
    public void helper_left(TreeNode root, List<Integer> result, int depth) {
        //结束条件
        if (root == null)
            return;
        if (result.size() == depth) {
            result.add(root.val);
        }
        // 先左后右
        helper_right(root.left, result, depth + 1);
        helper_right(root.right, result, depth + 1);

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
