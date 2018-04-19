package dfs.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xu on 10/08/2017.

129. Sum Root to Leaf Numbers
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.

*/
public class SumRootLeafNumbers {
    //也可以选择不用wrapper, 直接修改int值也是可以通过测试的
    public  IntWrapper wrapper = new IntWrapper(0);
    public int sumNumbers(TreeNode root) {
        if (root == null)
            return 0;
        List<Integer> list = new ArrayList<>();
        helper(root, list, wrapper.val);
        return list.stream().reduce(0, Integer::sum);
    }

    public void helper(TreeNode root, List<Integer> list, int num) {

        if (root.left == null && root.right == null) {
            list.add(num * 10 + root.val);
            return;
        }

        wrapper.val = num * 10 + root.val;
        //必须加入条件判断
        if (root.left != null)
            helper(root.left, list, wrapper.val);
        if (root.right != null)
            helper(root.right, list, wrapper.val);

        wrapper.val = (wrapper.val - root.val)/10;
    }

    //第二种方法
    public int sumNumbers_2(TreeNode root){
        if (root == null)
            return 0;
        return sum_helper(root, 0);
    }

    public int sum_helper(TreeNode root, int currSum){
        // 空节点推出条件
        if (root == null)
            return 0;
        // 叶子节点返回值
         if (root.left == null && root.right == null)
             return currSum * 10 + root.val;

         return sum_helper(root.left, currSum * 10 + root.val)
                 + sum_helper(root.right, currSum * 10 + root.val);

    }
    public int sumNumbers3(TreeNode root) {
        if(root==null)
            return 0;
        dfs(root, 0);
        return root.val;
    }
    public void dfs(TreeNode root, int sum){
        if (root == null) return;
        if (root.left == null && root.right == null) {
            root.val = sum*10+root.val;
            return;
        }
        if (root.left!=null)
            dfs(root.left, sum*10+root.val);
        if (root.right!=null)
            dfs(root.right, sum*10+root.val);

        if (root.left!=null && root.right!=null)
            root.val = root.left.val + root.right.val;
        else if (root.left!=null)
            root.val = root.left.val;
        else root.val = root.right.val;

    }


    class IntWrapper{
        int val;
        public IntWrapper(int val) {
            this.val = val;
        }
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
