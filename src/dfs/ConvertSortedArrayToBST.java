package dfs;

/**
 * Created by xu on 03/08/2017.
 *
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 */
public class ConvertSortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {

        if (nums==null || nums.length==0)
            return null;

        TreeNode root = dfs(nums, 0, nums.length-1);

        return root;
    }

    public TreeNode dfs(int[] nums, int left, int right){
        if (left > right)// done
            return null;
        // 找到中间数nums[mid], 作为根节点或者父节点的左右子节点
        int mid = left + (right - left)/2;

        // 创建节点
        TreeNode node = new TreeNode(nums[mid]);

        // 递归找到该节点的左右子节点，
        node.left = dfs(nums, left, mid-1);
        node.right = dfs(nums, mid+1, right);

        return node;
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
