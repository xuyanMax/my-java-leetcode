package bst;

import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Created by xu on 2017/6/8.
 */
public class MinimumAbsoluteDifference {
    class WrapInt{
        public int diff = Integer.MAX_VALUE;
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode (int value){
            this.val = value;
            left = null;
            right = null;

        }
    }
    //========================================================================
    // 相邻节点的比较
    private WrapInt Wrap = new WrapInt();

    // 缺少全局变量，导致比较的对象只能为相邻的节点，而不是按inorder排序后的节点
    public int getMinimumDifferenceBTW_Two_adjacent_nods(TreeNode root) {
        TreeNode pre = null;
        return helper(root, pre);

    }
    public int  helper (TreeNode root, TreeNode pre){

        if (root == null)
            return Wrap.diff;

        helper(root.left, pre);

        // compute the min difference
        if (pre != null) {
            Wrap.diff = Math.min(Wrap.diff, root.val - pre.val);
        }
        // update the parent Node

        pre = root;
        helper(root.right, pre);


        return Wrap.diff;
    }


    // ==========================================================================================
    // accepted solution on LeetCode
    // inorder traversal
    // 递归法，需要用全局变量来定位pre
    // curr: 中序遍历中的当前节点
    // pre:中序遍历中curr节点的前一节点
    private TreeNode pre = null;//必须为全局变量

    public int getMinimumDifference(TreeNode root) {
        TreeNode pre = null;
        return getMin(root);

    }
    public int getMin(TreeNode root) {
        if (root == null) return Wrap.diff;

        getMin(root.left);

        if (pre != null) {
            Wrap.diff = Math.min(Wrap.diff, root.val - pre.val);
        }
        pre = root;

        getMin(root.right);

        return Wrap.diff;
    }

    // ==========================================================================================
    // inorder-iterative solution 中序遍历从小到达从stack中弹出
    // 这次可以在方法内声明pre节点
    // curr: 中序遍历中的当前节点
    // pre:中序遍历中curr节点的前一节点
    // 因此可以不用Math.abs()
    public int inordeItr(TreeNode root) {
        TreeNode pre = null, curr = root;
        LinkedList<TreeNode> stack = new LinkedList<>();
        int min = Integer.MAX_VALUE;


        while (true) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }else {
                if (!stack.isEmpty()) {
                    curr = stack.pop();

                    if (pre != null) {
                        min = Math.min(min, root.val - pre.val);
                    }
                    pre = curr;
                    curr = curr.right;

                } else
                    break;
            }
        }
        return min;

    }
    // ==========================================================================================
    // use TreeSet + preorder
    private TreeSet<Integer> set = new TreeSet<>();
    //Wrap.diff
    public int AnyOrder_TreeSet(TreeNode root) {
        if (root == null)
            return Wrap.diff;
        if (!set.isEmpty()) {
            if (set.floor(root.val) != null) {
                Wrap.diff = Math.min(Wrap.diff, root.val - set.floor(root.val));
            }
            if (set.ceiling(root.val) != null) {//返回此 set 中大于等于给定元素的最小元素；如果不存在这样的元素，则返回 null
                Wrap.diff = Math.min(Wrap.diff, set.ceiling(root.val) - root.val);
            }
        }
        set.add(root.val);
        AnyOrder_TreeSet(root.left);
        AnyOrder_TreeSet(root.right);
        return Wrap.diff;

    }



}
