package nowcoder;

/**
 * [编程题]树的子结构
 * 热度指数：335804时间限制：1秒空间限制：32768K
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 */
public class IsSubTreeStructure {
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        boolean result = false;

        //当Tree1与Tree2都不为0，才进行比较，否则直接返回false
        if (root1 != null && root2 != null) {
            //如果找到对应Tree2的根节点
            if (root1.value == root2.value)
                // 以该节点为根判断root1是否包含Tree2
                result = doesTree1HaveTree2(root1, root2);

            //如果找不到，那么再去用root1的left作根，判断是否包含Tree2
            if (!result)
                result = doesTree1HaveTree2(root1.left, root2);

            //如果还找不到，那么再去root1的right作根，判断是否包含Tree2
            if (!result)
                result = doesTree1HaveTree2(root1.right, root2);

        }
        return result;

    }

    public boolean doesTree1HaveTree2(TreeNode node1, TreeNode node2) {
        //如果Tree2已经遍历完，都能对上，返回true
        if (node2 == null)
            return true;
        //如果Tree1遍历完，Tree2没有遍历完，返回false
        if (node1 == null)
            return false;
        // 如果其中一个节点对不上，返回false
        if (node1.value != node2.value)
            return false;
        // 如果根节点对上后，分别去子节点匹配
        return doesTree1HaveTree2(node1.left, node2.left) && doesTree1HaveTree2(node1.right, node2.right);
    }

    class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
    }
}
