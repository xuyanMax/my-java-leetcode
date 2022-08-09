package dp.knapsack;

/**
 * Created by xu on 13/12/2017.
 * <p>
 * 题目
 * 混合三种背包问题
 * 问题
 * 如果将P01、P02、P03混合起来。也就是说，有的物品只可以取一次（01背包），有的物品可以取无限次（完全背包），有的物品可以取的次数有一个上限（多重背包
 *
 * 有N种物品和一个容量为V的背包。第i种物品最多有n[i]件可用，每件费用是c[i]，价值是w[i]。
 * 求解将哪些物品装入背包可使这些物品的费用总和不超过背包容量，且价值总和最大。
 */
public class KnapsackMultiple {
    // 时间复杂度 O(N*sum(lgn(count[i]))
    // 空间 O(W)
    public int algorithm(int[] wgt, int[] val, int[] count, int W, int n) {
        int[] dp = new int[W + 1];
        for (int i = 1; i <= n; i++) {
            if (wgt[i - 1] * count[i - 1] <= W)//完全背包
                knapsackComplete(wgt, val, count, W, n, i);
            else {
                //  0/1背包
                int k = 1;
                // "二进制拆分物品i，满足1+2+4...2^k + x = count[i]"
                // 下列for 循环计算1:2^k
                while (k < count[i - 1]) {//选择item i 2^k个
                    for (int j = W; j >= k * wgt[i - 1]; j--)
                        dp[j] = Math.max(dp[j], dp[j - k * wgt[i - 1]] + k * val[i - 1]);
                    count[i - 1] -= k;
                    k <<= 1;
                }
                // 0/1背包
                // 选取剩余的item i x 件，凑够count[i]个
                for (int j = W; j >= count[i - 1] * wgt[i - 1]; j--)
                    dp[j] = Math.max(dp[j], dp[j - count[i - 1] * wgt[i - 1]] + count[i - 1] * val[i - 1]);
            }
        }
        return dp[W];

    }

//    public void knapsackMultiPack(int[] wgt, int[]val, int[] count, int W, int n, int i, int[] dp){
//        if (wgt[i-1] * count[i-1] <= W)//完全背包
//            knapsackComplete(wgt, val, count, W, n, i);
//        else {
//            //0/1背包
//            int k = 1;
//            // "二进制拆分物品i，满足1+2+4...2^k + x = count[i]"
//            // 下列for 循环计算1:2^k
//            while (k < count[i-1]) {
//                for (int j=W; j>=k*wgt[i-1]; j--)
//                    dp[j] = Math.max(dp[j], dp[j - k*wgt[i-1]] + k*val[i-1]);
//                count[i-1] -= k;
//                k <<= 1;
//            }
//            // 0/1背包
//            // 计算剩余的 x 件i物品
//            for (int j = W; j>=count[i-1]*wgt[i-1]; j--)
//                dp[j] = Math.max(dp[j], dp[j - count[i-1]*wgt[i-1]] + count[i-1]*val[i-1]);
//        }
//    }

    public void knapsackZeroOne(int[] wgt, int[] val, int[] count, int W, int n, int i) {

    }

    public void knapsackComplete(int[] wgt, int[] val, int[] count, int W, int n, int i) {

    }

}
