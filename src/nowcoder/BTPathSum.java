import java.util.ArrayList;

/**
 * 二叉树中和为某一值的路径
 * 时间限制：1秒 空间限制：32768K 热度指数：257913
 * 算法知识视频讲解
 * 题目描述
 * 输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。(注意: 在返回值的list中，数组长度大的数组靠前)
 */
public class BTPathSum {
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();

        if (root == null) return results;

        dfs(root, target, results, new ArrayList<>());

        return results;
    }

    public void dfs(TreeNode root, int target, ArrayList<ArrayList<Integer>> results, ArrayList<Integer> result) {

        if (root.left == null && root.right == null && root.val == target) {
            result.add(root.val);
            results.add(new ArrayList<>(result));
            return;
        }

        if (root.left != null)
            dfs(root.left, target - root.val, results, result);

        if (root.right != null)
            dfs(root.right, target - root.val, results, result);

        result.remove(result.size() - 1);
    }
}

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }
}
