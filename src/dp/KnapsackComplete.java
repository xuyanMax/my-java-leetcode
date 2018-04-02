package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xu on 12/12/2017.
 *
 *
 * http://blog.csdn.net/insistgogo/article/details/11081025
 * http://blog.csdn.net/netown_ethereal/article/details/24411327
 * http://blog.csdn.net/stack_queue/article/details/53544109
 */

public class KnapsackComplete {
    //每个物品数量不限，完全背包问题
    //扩展0/1背包解法

    // dp[i][j] = max(dp[i-1][j], dp[i-1][j-k*wgt[i]] + k*val[i]) k in [0:W/wgt[i]]
    // 需要求解N*W个状态，每个状态求解时间-非constant, j/wgt[i]
    // 复杂度 O(N*V*sum(W/wgt[i]))
    // 空间O(n*W)
    public int CompleteKnapsack(int[] val, int[]wgt, int[] count, int W, int n){
        int[][]dp = new int[n+1][W+1];
        for (int i=1; i<=n; i++) {//item i
            for (int j=0; j<=W; j++) {// weight/cost of item i
                int cnt = j/wgt[i-1];
                for (int k=0; k<=cnt; k++) //对一个item 最多取 W/wgt[i]向下取整个
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j - k* wgt[i-1]] + k*val[i-1]);// k = 0， = dp[i-1][j]
            }
        }
        return dp[n][W];
    }


    // 转换为0/1背包解:直接使用0/1背包求解
    // 拆分,利用"二进制思想"：把第i个item拆分为多个item，wgt为wgt[i]*2^k, val=val[i]*2^i
    // 拆分可靠性：不管最后选择了几件第i个item，其和总可以表达成若个2^k件物品之和。
    // 算法复杂度 O(Nnew*W), Nnew = sum(lg(W/wgt[i]))
    public int CompleteKnapsack2(Integer[] val, Integer[] wgt, int W, int n) {

        // 拆分item
        List<Integer> valList = Arrays.asList(val);
        List<Integer> wgtList = Arrays.asList(wgt);

        for (int i=0; i<n; i++) {
            int power = 1;
            while (power * wgt[i] <= W) {
                valList.add(val[i]*power);
                wgtList.add(wgt[i]*power);
                power <<= 1;
            }
        }

        // 拆分结束
        // 转为数组
        int[] valnew = new int[valList.size()];
        int[] wgtnew = new int[wgtList.size()];
        for (int i=0; i<valList.size(); i++){
            valnew[i] = valList.get(i);
            wgtnew[i] = wgtList.get(i);
        }

        // 0/1背包解法
        int[] dp = new int[W+1];
        for (int i=1; i<=valnew.length; i++)
            for (int j=W; j>=wgtnew[i-1]; j--)
                dp[j] = Math.max(dp[j], dp[j - wgtnew[i-1]] + valnew[i-1]);

        return dp[W];
    }


    //这是最优解 O(NW)
    // dp[i][j] = max(dp[i-1][j], dp[i][j-wgt[i]] + val[i]);
    // 循环方式：顺序循环（O(n)空间解）
    // 完全背包，不限次数，与0/1背包
    // dp[i][j] = max(dp[i-1][j], dp[i][j - wgt[i]] + val[i])
    // 这里的dp[i][j - wgt[i] + val[i]]而不是dp[i-1][...]
    // 原因是，0/1背包中，dp[i][j]的状态是由dp[i-1][j-wgt[i]]推出来的，即为了保证每个物品只选一次，保证在"考虑选择物品i"策略时，
    // 依据绝无可能选入物品i的子结果dp[i-1][j-wgt[i]]。然而，完全背包，不限制个数，则"考虑选择物品i"这种策略时，需要考虑"可能入选了物品i"的字结果
    // dp[i][j - wgt[i]]，因此必须采用j = wgt[i]:W的顺序循环

    // 时间复杂度O(N*W)
    // 空间O(W)
    public int optimalCompleteKnapsack(int[]val, int[]wgt, int W, int n) {
        int[][] dp = new int[n+1][W+1];

        for (int i=1; i<=n; i++)
            for (int j=wgt[i-1]; j<=W; j++)//优化从wgt[i]起
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j - wgt[i-1]] + val[i-1]);

        return dp[n][W];
    }

    // dd大牛模版

    public int algorithm(int[]val, int[]wgt, int W, int n) {
        int[] dp = new int[W+1];
        for (int i=1; i<=n; i++)
            CompletePack(val, wgt, W, n, dp, i);
        return dp[W];
    }
    public void CompletePack(int[]val, int[]wgt, int W, int n, int[]dp, int i){
        for (int j=wgt[i-1]; j<=W; j++)
            dp[j] = Math.max(dp[j], dp[j - wgt[i-1]] + val[i-1]);
    }

}
