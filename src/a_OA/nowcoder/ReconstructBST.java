package a_OA.nowcoder;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/8a19cbe657394eeaac2f6ea9b0f6fcf6?orderByHotValue=1&questionTypes=000100&difficulty=00010&page=1&onlyReference=false
 * 来源：牛客网
 * <p>
 * [编程题]重建二叉树
 * 热度指数：475860时间限制：1秒空间限制：32768K
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 */
public class ReconstructBST {
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        return helper(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    public TreeNode helper(int[] pre, int spre, int epre, int[] in, int sin, int ein) {
        if (spre >= epre || sin >= ein)
            return null;

        TreeNode curr = new TreeNode(pre[spre]);
        for (int pos = sin; pos < ein; pos++) {
            if (in[pos] == pre[spre]) {
                curr.left = helper(pre, spre + 1, spre + pos - sin, in, sin, pos - 1);
                curr.right = helper(pre, spre + pos - sin + 1, epre, in, pos + 1, ein);
                break;
            }
        }
        return curr;
    }

    public static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value, TreeNode left, TreeNode right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public TreeNode(int value) {
            this.value = value;
        }
    }
}
