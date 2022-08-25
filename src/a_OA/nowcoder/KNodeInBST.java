package a_OA.nowcoder;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/ef068f602dde4d28aab2b210e859150a?orderByHotValue=1&questionTypes=000100&difficulty=00010&page=1&onlyReference=false
 * 来源：牛客网
 * <p>
 * [编程题]二叉搜索树的大小为第kth的结点
 * 热度指数：141950时间限制：1秒空间限制：32768K
 * 算法知识视频讲解
 * 给定一棵二叉搜索树，请找出其中的第k小的结点。例如， （5，3，7，2，4，6，8）中，按结点数值大小顺序第三小结点的值为4。
 */
public class KNodeInBST {
    class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    int index = 0;

    //亦可以设置一个全局变量
    TreeNode target = null;

    public TreeNode KthNode(TreeNode pRoot, int k) {
        if (pRoot == null)
            return null;
        //先一路到底
        TreeNode node = KthNode(pRoot.left, k);

        // 一旦返回了pRoot, 那么node不会为null,会逐层递归出去，返回Kth TreeNode
        // 如果没有这句，那么将会被下方的语句覆盖掉返回的节点
        if (node != null)
            return node;
        if (k == ++index)
            return pRoot;

        node = KthNode(pRoot.right, k);
        if (node != null)
            return node;
        return null;
    }
}
