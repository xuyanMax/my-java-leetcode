package arr.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/*653.
    Two Sum IV - Input is a BST

    Given a Binary Search Tree and a target number,
    return true if there exist two elements in the BST such that their sum is equal to the given target.

    Example 1:
    Input:
    5
    / \
    3   6
    / \   \
    2   4   7

    Target = 9

    Output: True
    Example 2:
    Input:
    5
    / \
    3   6
    / \   \
    2   4   7

    Target = 28

    Output: False*/
public class TwoSum4InputBST {
    class TreeNode {
        TreeNode left, right;
        int val;

        public TreeNode(int val) {
            this.val = val;
            left = null;
            right = null;
        }
    }
    // 1. hashmap
    // O(n) time
    // O(n) space
    static HashSet<Integer> set;
    public boolean findTarget(TreeNode root, int k) {
        set = new HashSet<>();
        return dfs(root, k);
    }
    public boolean dfs(TreeNode root, int k){
        if (root == null) return false;
        if (set.contains(k - root.val)) return true;
        set.add(root.val);
        return dfs(root.left, k) || dfs(root.right, k);
    }

    // 2. convert BST by in-order to a sorted array
    // and use 2 pointers to find
    List<Integer> list;
    public boolean findTarget2(TreeNode root, int k){

        list = new ArrayList<>();
        inOrder(root);
        for (int i=0, j=list.size()-1; i<j;){
            if (list.get(i) + list.get(j) == k) return true;
            else if (list.get(i) + list.get(j) < k) i++;
            else j--;
        }
        return false;

    }
    public void inOrder(TreeNode root){
        if (root==null) return;
        inOrder(root.left);
        list.add(root.val);
        inOrder(root.right);
    }
    // 3. binary search on BST
    //遍历dsf每一个元素，然后从root search每一个(k-curr.val)
    public boolean findTarget3(TreeNode root, int k) {
        return dfs(root, root, k);
    }
    // dfs遍历每一个节点curr
    public boolean dfs(TreeNode root, TreeNode curr, int k){
        if (curr==null) return false;
        return search(root, curr, k - curr.val)// curr.val
                ||dfs (root, curr.left, k)
                ||dfs (root, curr.right, k);
    }
    // 对应每一个curr节点，从root遍历search curr+root==target的节点是否存在
    public boolean search(TreeNode root, TreeNode curr, int target){
        if (curr == null) return false;
        return (target == root.val && root!=curr)
                ||(root.val<target) && search(root.right,curr, target)//root.right
                ||(root.val>target) && search(root.left, curr, target);
    }
}
