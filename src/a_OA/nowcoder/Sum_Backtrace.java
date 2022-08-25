package a_OA.nowcoder;

import java.util.ArrayList;
import java.util.List;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/11cc498832db489786f8a03c3b67d02c?orderByHotValue=1&questionTypes=000100&difficulty=00001&page=1&onlyReference=false
 * 来源：牛客网
 * <p>
 * [编程题]求和
 * 热度指数：15376时间限制：1秒空间限制：32768K
 * 算法知识视频讲解
 * 输入两个整数 n 和 m，从数列1，2，3.......n 中随意取几个数,使其和等于 m ,要求将其中所有的可能组合列出来
 * 输入描述:
 * 每个测试输入包含2个整数,n和m
 * <p>
 * <p>
 * 输出描述:
 * 按每个组合的字典序排列输出,每行输出一种组合
 */
public class Sum_Backtrace {
    /**
     * 效率极低
     *
     * @param n
     * @param m
     * @return
     */
    public List<List<Integer>> sum(int n, int m) {
        List<List<Integer>> res = new ArrayList<>();
        if (n == 0 || m == 0) return res;
        helper(n, m, 1, res, new ArrayList<>());
        return res;
    }

    public void helper(int n, int m, int num, List<List<Integer>> res, List<Integer> result) {
        if (m == 0) {
            res.add(new ArrayList<>(result));
        }
        for (int i = num; i <= n && i <= m; i++) {
            result.add(i);
            helper(n, m - i, i + 1, res, result);
            result.remove(result.size() - 1);
        }
    }

}
