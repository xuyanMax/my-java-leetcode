package tree.easy;

/**
 * Created by xu on 03/01/2018.
 */
public class BuildBSTWithSortedArray {//build min depth binarysearchtree
    public TreeNode buildTree(int[] arr){
        // 取有序数组的中间值作为root，这样可以得到高度最小的树
        if (arr == null || arr.length == 0) return null;
        return helper(arr, 0, arr.length-1);
    }
    public TreeNode helper(int[] arr, int left, int right) {
        if (left>right) return null;
        int mid = left + (right-left)/2;
        TreeNode root = new TreeNode(arr[mid]);
        root.left = helper(arr, left, mid-1);
        root.right = helper(arr, mid+1, right);

        return root;
    }
    public int height(TreeNode root){
        if (root == null) return 0;
        return 1+Math.max(height(root.left), height(root.right));
    }

    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }

    }
}
