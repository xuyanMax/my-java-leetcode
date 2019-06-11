package dfs.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xu on 08/08/2017.
 * 337. House Robber III
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area,
 * called the "root." Besides the root, each house has one and only one parent house.
 * After a tour, the smart thief realized that "all houses in this place forms a binary tree".
 * It will automatically contact the police if two directly-linked houses were broken into on the same night.
 * <p>
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 * <p>
 * Example 1:
 * (3)
 * / \
 * 2   3
 * \   \
 * (3)   (1)
 * Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * <p>
 * Example 2:
 * 3
 * / \
 * (4)   (5)
 * / \   \
 * 1   3   1
 * Maximum amount of money the thief can rob = 4 + 5 = 9.
 */
// reference:https://discuss.leetcode.com/topic/39834/step-by-step-tackling-of-the-problem/67
public class HouseRobber3 {
    /**
     * 从题目可以看到一个，optimal substructure最优子结构存在，
     * 意思是：如果我们想知道从当前root开始能够获得的最大收益，那么我们也想知道从root的左、右节点能够获得的最大收益是多少。
     * 现在我们定义rob_naive(root)返回从root作为跟节点二叉树可以得到的最大收益
     * <p>
     * 想要构建root解，需要通过从root的的子问题的解开始。
     */

// 对于递归问题，两个要素如下
// 1-递归边界条件：空节点
// 2-递归关系：rob_naive(root) = max(root.label + rob_naive(root.left.left) + rob_naive(root.left.right)
//                                        + rob_naive(root.right.left) + rob_naive(root.right.right)
//                                        ,
//                                rob_naive(root.left) + rob_naive(root.right))
//
    public int rob_naive(TreeNode root) {
        if (root == null)
            return 0;
        // 用于计算包含root.val的收益
        int rootedVal = root.val;
        if (root.left != null)
            rootedVal += rob_naive(root.left.left) + rob_naive(root.left.right);

        if (root.right != null)
            rootedVal += rob_naive(root.right.left) + rob_naive(root.right.right);

        return Math.max(rootedVal, rob_naive(root.left) + rob_naive(root.right));

    }

    // 第一种方法使用了"最优子结构"的思想，但是没有过多考虑"子结构重叠"的问题
    // 我们用dp 的思想来解决，使用一个HashMap来记录当前节点对应的最大收益
    Map<TreeNode, Integer> maps = new HashMap<>();

    public int rob_dp(TreeNode root) {
        if (root == null)
            return 0;
        // added code
        if (maps.containsKey(root))
            return maps.get(root);

        int val = root.val;
        if (root.left != null)
            val += rob_dp(root.left.left) + rob_dp(root.left.right);

        if (root.right != null)
            val += rob_dp(root.right.left) + rob_dp(root.right.right);

        val = Math.max(val, rob_dp(root.left) + rob_dp(root.right));
        // added code
        maps.put(root, val);
        return val;

    }

    // GREEDY 贪婪算法
    // 这回我们重新定义rob(TreeNode root)，返回值为一维两个元素的数组，
    // 以此保留从该root开始，包含该节点与不包含该节点的最大收益分别是多少
    public int rob(TreeNode root) {
        if (root == null)
            return 0;
        return Math.max(rob_greedy(root)[0], rob_greedy(root)[1]);
    }

    public int[] rob_greedy(TreeNode root) {
        if (root == null)
            return new int[2];

        int[] left = rob_greedy(root.left);
        int[] right = rob_greedy(root.right);

        int[] ret = new int[2];
        // ret[1]:不包括root值
        // ret[0]:包括root值
        // 不包含root：包含左子节点／不包含的较大值 + 包含右子节点／不包含的较大值
        ret[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        ret[0] = root.val + left[1] + right[1];

        return ret;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int v) {
            this.val = v;
            left = null;
            right = null;
        }
    }
}
