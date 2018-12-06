package dp.knapsack;

/**
 * Created by xu on 13/12/2017.
 */
public class KnapsackMix {

    // 0/1背包与完全背包混合
    // 有些物品取1/0次，有些取无限次数
    public int sol(int[] val, int[]wgt, int[]count, int W, int n) {
        //针对不同物品，采取不同的背包策略
        int[] dp = new int[W+1];

        for (int i=1; i<=n; i++) {
            if (count[i-1]*wgt[i-1] <= W) //完全背包
                PacksackComplete();//顺序循环
            else
                PacksackZeroOne();//逆序循环
        }
        return dp[W];
    }

    // 0/1背包、完全背包+多重背包
    // 有些物品取0/1次，有些无限次数，有些有限次数
    public int sol2(int[]val, int[] wgt, int[] count, int W, int n) {

        int[] dp = new int[W+1];
        for (int i=1; i<=n; i++) {
//            if (item i 是0/1背包)
//                PacksackZeroOne();
//            else if (item i 是完全背包)
//                PacksackComplete();
//            else if (item i 是多重背包)
//                PacksackMultiple();
        }
        return dp[W];
    }
    public void PacksackComplete(){}
    public void PacksackZeroOne() {}
}
