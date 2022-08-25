package a_OA.nowcoder;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/a861533d45854474ac791d90e447bafd?orderByHotValue=1&questionTypes=000100&difficulty=00010&page=1&onlyReference=false
 * 来源：牛客网
 * <p>
 * [编程题]二叉搜索树的后序遍历序列
 * 热度指数：291854时间限制：1秒空间限制：32768K
 * <p>
 * 输入一个整数数组，判断该数组是不是'某二叉搜索树'的后序遍历的结果。如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 */
public class ValidPostOrderTraversal {

    /**
     * 二叉搜索树的后续遍历满足: 最后一个元素是根，去掉根，那么序列可分为前后两部分，且满足: 前一段小于根，后一段大于根
     * 且这两段序列也都是合法的后续遍历序列
     *
     * @param sequence
     * @return
     */
    public boolean VerifySequenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0)
            return false;
        return helper(sequence, 0, sequence.length - 1);
    }

    public boolean helper(int[] sequence, int l, int r) {
        if (l >= r) // l == r是叶子节点，l>r是空树，都满足合法序列
            return true;
        int index = r;
        while (index > l && sequence[index - 1] > sequence[r])
            index--;
        for (int j = l; j < index; j++)
            if (sequence[j] > sequence[r]) return false;
        //排除root r
        return helper(sequence, l, index) && helper(sequence, index + 1, r - 1);
    }
}
