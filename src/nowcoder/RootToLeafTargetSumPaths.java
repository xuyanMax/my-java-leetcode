package nowcoder;

import java.util.ArrayList;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/b736e784e3e34731af99065031301bca?orderByHotValue=1&questionTypes=000100&page=3&onlyReference=false
 * 来源：牛客网
 * <p>
 * [编程题]二叉树中和为某一值的路径
 * 热度指数：262860时间限制：1秒空间限制：32768K
 * 算法知识视频讲解
 * 输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。(注意: 在返回值的list中，数组长度大的数组靠前)
 */
public class RootToLeafTargetSumPaths {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.val = value;
        }
    }

    ArrayList<ArrayList<Integer>> results = new ArrayList<>();
    ArrayList<Integer> result = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null) return results;
        result.add(root.val);
        if (root.left == null && root.right == null && root.val == target)
            results.add(new ArrayList<>(result));
        FindPath(root.left, target-root.val);
        FindPath(root.right, target-root.val);

        result.remove(result.size() - 1);
        return results;
    }
}
